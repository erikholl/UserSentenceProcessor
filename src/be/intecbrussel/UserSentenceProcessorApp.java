package be.intecbrussel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
            writeToFile(path, sentence);
        }
    }

    private static void createFile(Path path) {
        try {
            Files.createDirectories(path.getParent());
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(Path path, String input) {
        try (FileWriter fileWriter = new FileWriter(path.toFile(), true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private static void readFromFile() {
//        try (BufferedReader bufferedReader =
//                     new BufferedReader(path.toFile()) ) {
//
//        }
//
//    }


}
