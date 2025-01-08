package com.dilazehra.endless_night;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class GameLogic {
    static int currentstatus = 0; // to be used later
    public static final String endKey = "??END??";
    // Initialize the dictionary (Hashtable implementation)
    static Dictionary<String, ArrayList<String>> imTextDict = new Hashtable<>();

    public static String getText(String image, int no) {
        if (imTextDict.get(image) == null) {
            return "You are in trouble";
        }
        ArrayList<String> list = imTextDict.get(image);
        if (no >= list.size()) {
            return endKey;
        }
        return list.get(no);
    }

    // Static initialization of the dictionary
    static {
        // Initialize the dictionary from file
        File file = new File("src/main/resources/com/dilazehra/converseTexts/imageTexts.txt");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] words = line.split("\\|");
                String image = words[0];
                ArrayList<String> wordList = new ArrayList<>();
                for (int i = 1; i < words.length; i++) {
                    wordList.add(words[i]);
                }
                imTextDict.put(image, wordList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file 'imageTexts.txt' was not found.");
            e.printStackTrace(); // Print stack trace for more detailed debugging
        }
    }
}
