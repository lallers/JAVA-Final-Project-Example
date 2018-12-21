package file_reader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class MonitorSystem {

      public static void main(String... args) {

            List<String> FileStrings = new ArrayList<String>();
            // FileStrings.add("C:\\Users\\LeeAllers\\Desktop\\NEW
            // JAVA\\system-monitor\\src\\test\\files\\animals.txt");
            // FileStrings.add("C:\\Users\\LeeAllers\\Desktop\\NEW
            // JAVA\\system-monitor\\src\\test\\files\\habitats.txt");
            FileStrings.add("c:\\files\\animals.txt");
            FileStrings.add("c:\\files\\habitats.txt");

            Scanner choice = new Scanner(System.in);

            System.out.println("What would you like to do?");
            System.out.println("1\tView Animals");
            System.out.println("2\tView Habitats");
            System.out.println("3\tExit");

            int option = choice.nextInt(); // System.out.println("main::option::" + option);

            if (option == 1) {
                  try {
                        String _FileStrings = FileStrings.get(option - 1); // LEE: For simplicity, I included this. Also
                                                                           // getting the correct file is called twice
                                                                           // for both animals and habitats so if you
                                                                           // remove this, make sure to change all 4(!)
                                                                           // calls to this variable

                        List<String> AnimalList = File_Reader.GetDetails(_FileStrings); // LEE: uses the "GetDetails
                                                                                        // method in the
                                                                                        // File_Reader.java file"
                        // System.out.println("main::AnimalList::" + AnimalList);

                        // LEE: Integer lineStart -- The "GetDetails" method returns an ArrayList type
                        // along with the line
                        // number it ended on. This gets the resulting line number. This is important
                        // because when we use a RegEx method later to find keywords -- like "lion" or
                        // "penguin", the file cannot be read from the top again, where the details are
                        // stored
                        Integer lineStart = Integer.parseInt(AnimalList.get(AnimalList.size() - 1)); // System.out.println("main::lineStart::"

                        // LEE: Scanner _AnimalChoice -- I actually have no idea if this is doing
                        // anything... but it didn't break anything. might want to test it? //
                        Scanner _AnimalChoice = new Scanner(System.in);

                        // LEE: for loop -- Self explanatory -- printing the Animal list that is
                        // returned from the GetDetails method //
                        for (int i = 0; i < AnimalList.size() - 1; i++) {
                              System.out.println(AnimalList.get(i));
                        }

                        // LEE: Adds the exit option to the options list by using a generic addExit
                        // method at the bottom of this file //
                        System.out.println(addExit(AnimalList.size()));
                        int AnimalOption = _AnimalChoice.nextInt();

                        // LEE: _AnimalChoice -- Again, I have no idea if this is doing anything...
                        _AnimalChoice.close();
                        if (checkExit(AnimalOption, AnimalList.size()) == true) {
                              return; // basically the same thing as "break" but for for loops
                        }

                        // LEE: String AnimalChoice -- Gets the selected animal/habitat choice
                        // System.out.println("main::AnimalOption::" + AnimalOption);
                        String AnimalChoice = AnimalList.get(AnimalOption - 1);

                        // LEE: String tmp -- trims the whitespace of the choice so that it's cleaner
                        // when passed to the RegEx function and then splits the chosen string -- for
                        // example 'Details on lions' into an array like ["Details","-","lions"]
                        // this allows us to choose the 3rd element in teh array everytime -- provided
                        // when writing to the file you follow the convention "Details - XXXXX"
                        // System.out.println("main::INITIAL::AnimalChoice::" + AnimalChoice);
                        String[] tmp = AnimalChoice.trim().split("\\s+");
                        // System.out.println("main::tmp::AnimalChoice::" + tmp);
                        AnimalChoice = tmp[3];
                        // System.out.println("main::xxxx::AnimalChoice::" + AnimalChoice);

                        // LEE: This Removes the 's' from animals -- like "lions" -- so that when passed
                        // to the RegEx function, it searches for 'lion' and not 'lions' -- definitely a
                        // SUPER no-no way of doing this in general but it works for simple plural
                        // words. This will still work for words that aren't plural like "Moose" -- it
                        // will search for "Moos" and will find all words with "Moos" in them, including
                        // "Moose"
                        AnimalChoice = AnimalChoice.substring(0, AnimalChoice.length() - 1);

                        // LEE: Buils the Regular Expression. You'll have to look this stuff up because
                        // it gets compicated. Also, it's different in Java than anywhere else so it
                        // took me about 2 hours to figure this out lol
                        String RegExAnimalChoice = "(?i)(Animal - " + AnimalChoice + "+?)";

                        // LEE: The GetData method in the File_Reader.java file returns another Array
                        // list. In this case of the animal you're searching for.
                        List<String> FinalList = File_Reader.GetData(_FileStrings, lineStart, RegExAnimalChoice);

                        // LEE: Implements the dialog when the string has ***** appended as well as
                        // displays the other list items
                        // System.out.println("main::FinalList::" + FinalList);
                        for (String str : FinalList) {
                              String checkAlert = "*****";
                              if (str.contains(checkAlert)) {
                                    JOptionPane.showMessageDialog(null, str.replace(checkAlert, ""));
                              }
                              System.out.println(str);
                        }

                  } catch (Exception e) {
                        System.out.println("*** Exception occurred while viewing 'Animals' ***");
                        System.out.println(e);
                        System.out.println("****************************************************");
                  } finally {
                        choice.close();
                  }
            } else if (option == 2) {// case 2:
                  try {

                        // LEE: I got lazy and just pasted everything from the "Animals" part to here.
                        // It's almost exactly the same except for the _Filestrings varibale, also we
                        // don't need to remove any "s" values from
                        // the habitat names like wed id for animals and the RegEx now has "Habitats"
                        // instead of "Animals".
                        // Ideally, you would implement this in it's own method or class and call it
                        // from main.

                        // ALSO, the else at the end of this provides the "please enter a valid option"
                        // but currently it doesn't loop back to the menu options, it just quits. I'm
                        // not sure if this would be required.

                        String _FileStrings = FileStrings.get(option - 1);

                        List<String> AnimalList = File_Reader.GetDetails(_FileStrings); // LEE: Gets habitat file
                        System.out.println("main::AnimalList::" + AnimalList);

                        Integer lineStart = Integer.parseInt(AnimalList.get(AnimalList.size() - 1));
                        System.out.println("main::lineStart::" + lineStart);

                        for (int i = 0; i < AnimalList.size() - 1; i++) {
                              System.out.println(AnimalList.get(i));
                        }

                        int AnimalOption = choice.nextInt();
                        // System.out.println("main::AnimalOption::" + AnimalOption);
                        String AnimalChoice = AnimalList.get(AnimalOption - 1);
                        // System.out.println("main::INITIAL::AnimalChoice::" + AnimalChoice);
                        String[] tmp = AnimalChoice.trim().split("\\s+");
                        // System.out.println("main::tmp::AnimalChoice::" + tmp);
                        AnimalChoice = tmp[3];
                        // System.out.println("main::xxxx::AnimalChoice::" + AnimalChoice);

                        // System.out.println("main::FINAL::AnimalChoice::" + AnimalChoice);
                        String RegExAnimalChoice = "(?i)(Habitat - " + AnimalChoice + "+?)";
                        List<String> FinalList = File_Reader.GetData(_FileStrings, lineStart, RegExAnimalChoice);
                        // System.out.println("main::FinalList::" + FinalList);
                        for (String str : FinalList) {
                              String checkAlert = "*****";
                              if (str.contains(checkAlert)) {
                                    JOptionPane.showMessageDialog(null, str.replace(checkAlert, ""));
                              }
                              System.out.println(str);
                        }

                  } catch (Exception e) {
                        System.out.println("*** Exception occurred while viewing 'Habitats' ***");
                        System.out.println(e);
                        System.out.println("****************************************************");
                  } finally {
                        choice.close();
                  }
            } else if (option == 3) {
                  choice.close();
                  return;
            } else {
                  System.out.println("Please enter a valid menu option");
            }
            choice.close();
      }

      private static String addExit(Integer arrayLength) {
            return (arrayLength) + "\tExit";
      }

      public static Boolean checkExit(Integer input, Integer arrayLength) {
            if (input == arrayLength) {
                  return true;
            } else {
                  return false;
            }
      }

}