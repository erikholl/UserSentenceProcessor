package be.intecbrussel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class UserSentenceProcessorApp {
    public static void main(String[] args) {
        System.out.println("provide sentences; type 'exit' to stop");
        Scanner userInput = new Scanner(System.in);
        Path path = Paths.get("files/Sentences.txt");

        String sentence = "";

        createFile(path);

        while (userInput.hasNextLine() && !((sentence = userInput.nextLine()).equals(
                "exit"))) {
            if (sentence.trim().isEmpty()) {
                continue;
            }
            writeToFile(path, sentence);
        }
    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            } else {
                emptyFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static  void emptyFile(Path path) {
        try (FileWriter fileWriter = new FileWriter(path.toFile())) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String conversionText(String input) {
        String lowerCase = input.toLowerCase();
        String firstLetterCapital =
                lowerCase.substring(0,1).toUpperCase().concat(lowerCase.substring(1));
        if ( !(firstLetterCapital.endsWith(".") || firstLetterCapital.endsWith(
                "?") || firstLetterCapital.endsWith("!")) ) {
            firstLetterCapital = firstLetterCapital.concat(".");
        }
        return firstLetterCapital;
    }

    private static void writeToFile(Path path, String input) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(), true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            input = conversionText(input);
            bufferedWriter.write(input + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
