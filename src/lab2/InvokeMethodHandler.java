package lab2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;

class InvokeMethodHandler {
    public void invokeAnnotatedMethods(Object object) throws InvocationTargetException, IllegalAccessException {
        Class<?> sampleClass = object.getClass();
        Method[] methods = sampleClass.getDeclaredMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(InvokeMethod.class)) {
                if (Modifier.isPublic(method.getModifiers())) {
                    continue;
                }
                method.setAccessible(true);
                for (int i = 0; i < method.getAnnotation(InvokeMethod.class).numberInvoking(); i++) {
                    Object[] parameters = getParameters(method);
                    method.invoke(object, parameters);
                }
                method.setAccessible(false);
            }
        }
    }

    private Object[] getParameters(Method method) {
        Object[] parameters = new Object[method.getParameterCount()];
        Random random = new Random();
        for (int i = 0; i < parameters.length; ++i) {
            if (method.getParameters()[i].getType().equals(int.class)) {
                parameters[i] = random.nextInt(0,200);
            }
            else if (method.getParameters()[i].getType().equals(double.class)) {
                parameters[i] = random.nextDouble(0,200);
            }
            else if (method.getParameters()[i].getType().equals(boolean.class)) {
                parameters[i] = random.nextBoolean();
            }
            else if (method.getParameters()[i].getType().equals(char.class)) {
                parameters[i] = (char) random.nextInt(0, 200);
            }
            else if (method.getParameters()[i].getType().equals(float.class)) {
                parameters[i] = random.nextFloat();
            }
            else if (method.getParameters()[i].getType().equals(byte.class)) {
                parameters[i] = (byte) random.nextInt(-128, 127);
            }
            else if (method.getParameters()[i].getType().equals(String.class)) {
                parameters[i] = "hello";
            }
        }
        return parameters;
    }
}
