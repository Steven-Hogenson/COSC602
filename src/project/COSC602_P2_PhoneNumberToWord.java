package project;

import java.io.*;
import java.util.*;

/**
 * @author Steven Hogenson on 3/23/2022
 * @project StevenHogenson_Java
 */

public class COSC602_P2_PhoneNumberToWord {

    public static void test() {
        String wordFile = "../COSC602_P2_EnglishWordList.txt";
        ArrayList<String> wordList = sevenLetterWords(wordFile);
        ArrayList<String> numberList = convertWordsToNumbers(wordList);
        Map<String, List<String>> multiMap = new TreeMap<>();

        //Places key (phone number String) and values (words that can be made from that number) into a map.
        //Use a List to store values of keys for duplicates that can be assigned to the same phone number.
        for (int i = 0; i < numberList.size(); i++) {
            multiMap.computeIfAbsent(numberList.get(i), values -> new ArrayList<>()).add(wordList.get(i));
        }
        Scanner input = new Scanner(System.in);
        String inputString;
        int wordsFound = 0;

        System.out.println("*type EXIT to quit*");
        //main loop
        while (true) {
            System.out.print("\nEnter a 7-digit phone number containing 2 ~ 9: ");
            inputString = input.next();
            //exit case
            if (inputString.equalsIgnoreCase("EXIT")) {
                input.close();
                System.exit(0);
            }
            if (isValidNumber(inputString)) {
                //if the user's input number is valid and exists as a key in multiMap, then print all values of that key
                if (multiMap.containsKey(inputString)) {
                    for (String values : multiMap.get(inputString)) {
                        System.out.println(values);
                        wordsFound += 1;
                    }
                }
                if (wordsFound > 0) {
                    System.out.println("\nWords found: " + wordsFound);
                } else {
                    System.out.println("No words found.\n");
                }
                wordsFound = 0;
            } else {
                System.out.println("Invalid number. Number must be 7 digits, no letters, and cannot contain a 0 or 1. Try again.\n");
            }
        }
    }

    /**
     * Determines if the number the user has entered is valid
     *
     * @param s the user's String input
     * @return boolean if s is 7 digits and contains only numbers 2 ~ 9
     */
    private static boolean isValidNumber(String s) {
        return s.length() == 7 && s.matches("[2-9]+");
    }

    /**
     * Places all 7-letter words from fileName into an ArrayList of Strings
     *
     * @param fileName the name/directory of the input file
     * @return an ArrayList of Strings containing only 7-letter words
     */
    private static ArrayList<String> sevenLetterWords(String fileName) {
        ArrayList<String> sevenLetterWordsList = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 7) {
                    sevenLetterWordsList.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sevenLetterWordsList;
    }

    /**
     * Converts all words in sevenLetterWordList to a String of numbers and adds them to a List
     *
     * @param sevenLetterWordsList the list of 7-letter words created by sevenLetterWords()
     * @return an ArrayList of Strings made of 7-digit Strings
     */
    private static ArrayList<String> convertWordsToNumbers(ArrayList<String> sevenLetterWordsList) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder number;
        for (String word : sevenLetterWordsList) {
            number = new StringBuilder();
            for (char character : word.toLowerCase().toCharArray()) {
                switch (character) {
                    case 'a':
                    case 'b':
                    case 'c':
                        number.append("2");
                        break;
                    case 'd':
                    case 'e':
                    case 'f':
                        number.append("3");
                        break;
                    case 'g':
                    case 'h':
                    case 'i':
                        number.append("4");
                        break;
                    case 'j':
                    case 'k':
                    case 'l':
                        number.append("5");
                        break;
                    case 'm':
                    case 'n':
                    case 'o':
                        number.append("6");
                        break;
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                        number.append("7");
                        break;
                    case 't':
                    case 'u':
                    case 'v':
                        number.append("8");
                        break;
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':
                        number.append("9");
                        break;
                }
            }
            result.add(number.toString());
        }
        return result;
    }
}