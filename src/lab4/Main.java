package lab4;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator(
                "D:\\���� ���������\\���������\\IdeaProjects\\lab4\\src\\lab4\\dictionary.txt",
                "D:\\���� ���������\\���������\\IdeaProjects\\lab4\\src\\lab4\\input.txt"
        );
        try {
            translator.loadDictionary();
            String ruText = translator.translate();
            System.out.println(ruText);
        } catch (FileReadException | InvalidFileFormatException e) {
            e.printStackTrace();
        }
    }
}