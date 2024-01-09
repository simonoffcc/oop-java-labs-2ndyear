package lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Translator {
    private final Map<String, String> eng2ruDictionary = new HashMap<>();
    private int maxTranslationLength;
    private final String dictPath;
    private final String inputPath;

    public Translator(String dictPath, String inputPath) {
        this.dictPath = dictPath;
        this.inputPath = inputPath;
    }

    private int calculateMaxTranslationLength() {
        int maxLength = 0;
        for (String key : eng2ruDictionary.keySet()) {
            maxLength = Math.max(maxLength, key.split(" ").length);
        }
        return maxLength;
    }

    public void loadDictionary() throws FileReadException, InvalidFileFormatException {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length != 2) {
                    throw new InvalidFileFormatException("Invalid format in the dictionary file.");
                }
                String engStr = parts[0].trim().toLowerCase();
                String ruStr = parts[1].trim().toLowerCase();
                this.eng2ruDictionary.put(engStr, ruStr);
            }
        } catch (IOException e) {
            throw new FileReadException("Error reading the dictionary file.");
        }
        this.maxTranslationLength = calculateMaxTranslationLength();
    }

    public String translate() throws FileReadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
            String[] sentences = text.toString().replace(", ", " ").toLowerCase().split("\\.");
            StringBuilder translatedText = new StringBuilder();
            for (String sentence : sentences) {
                String translatedSentence = translateSentence(sentence.trim());
                if (!translatedSentence.isEmpty()) {
                    translatedText.append(translatedSentence).append(". ");
                }
            }
            return translatedText.toString();
        } catch (IOException e) {
            throw new FileReadException(inputPath);
        }
    }

    public String translateSentence(String sentence) {
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(sentence.split(" ")));

        for (int i = 0; i < wordsList.size(); i++) {
            try {
                String word = wordsList.get(i);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            for (int length = Math.min(this.maxTranslationLength, wordsList.size() - i); length > 0; length--) {
                try {
                    String currentString = String.join(" ", wordsList.subList(i, i + length));
                    if (this.eng2ruDictionary.containsKey(currentString)) {
                        wordsList.set(i, this.eng2ruDictionary.get(currentString));
                        for (int k = 1; k < length; k++) {
                            wordsList.remove(i + 1);
                        }
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }

        if (wordsList.size() > 0 && !wordsList.get(0).isEmpty()) {
            wordsList.set(0, wordsList.get(0).substring(0, 1).toUpperCase() + wordsList.get(0).substring(1));
        }

        wordsList.removeIf(String::isEmpty);

        return String.join(" ", wordsList);
    }
}
