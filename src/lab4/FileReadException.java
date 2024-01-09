package lab4;

class FileReadException extends Exception {
    public FileReadException(String filePath) {
        super("Error reading file: " + filePath);
    }
}
