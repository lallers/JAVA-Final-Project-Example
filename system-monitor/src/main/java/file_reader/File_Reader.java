package file_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
//import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ameri
 */

/*
 * LEE: Changed File_reader class to File_Reader to follow my own folder
 * structure - also changed the constructors for the original 'FileReader'
 */
public class File_Reader {
    /*
     * LEE: GetDetails function makes the assumption that the lines like
     * "Details on lions" and "Details on penguin habitat" are always at the top of
     * the txt file
     */

    public static List<String> GetDetails(String filePath) throws FileNotFoundException, IOException {
        // System.out.println("getDetails::**********");
        return ReadFile(filePath, true, 0, null);
    }

    public static List<String> GetData(String filePath, Integer lineStart, String phrase)
            throws FileNotFoundException, IOException {
        // System.out.println("getData::**********");
        return ReadFile(filePath, false, lineStart, phrase);
    }

    public static List<String> ReadFile(String filePath, boolean isDetails, Integer lineStart, String phrase)
            throws FileNotFoundException, IOException {
        // System.out.println("ReadFile::**********");

        List<String> fileData = new ArrayList<String>(); // initializes as an array
        String fileLine;
        int i;
        // create file reader

        try (BufferedReader textReader = new BufferedReader(new FileReader(filePath))) {
            // System.out.println("ReadFile::textReader::" + textReader);
            /*
             * LEE: From the assumptions about the "Details", This if/else block chooses to
             * either read the file from the begining of the file, where the details options
             * are, or from the end of the options when returning the Animals or Habitat
             * details.
             */
            if (isDetails == true) {
                i = 0; // LEE: Starts the reader from the top of the file (assuming "Details" options
                       // are always at the top)
                // System.out.println("ReadFile::WhileLoop::isDetails:start");
                ;

                while (((fileLine = textReader.readLine()) != null) && !("".equals(fileLine))) {

                    fileData.add((i + 1) + "\t" + fileLine + '\n');
                    i++;
                    // System.out.println("ReadFile::WhileLoop::isDetails::" + fileLine);
                    ;
                }
                // System.out.println("ReadFile::WhileLoop::isDetails::end");
                ;
                fileData.add(Integer.toString(i + 1)); // LEE: Adds final line index to list array so we can read them
                                                       // file
                textReader.close();
            } else {
                // System.out.println("ReadFile::WhileLoop::isDetails=FALSE:start");
                // LEE: This loops through the first 'x' amount of lines which should be the
                // 'Details' options and starts to read from where animals/habitat details
                // should be
                for (int x = 0; x < lineStart; ++x) {
                    textReader.readLine();
                }
                // System.out.println("ReadFile::textReader::isDetails=false::" + textReader);
                Pattern pattern = Pattern.compile(phrase); // Sets the type of animal or habitat phrase to match with
                                                           // regex.
                // System.out.println("ReadFile::pattern::" + pattern);
                Matcher matched = null;
                while ((fileLine = textReader.readLine()) != null) {

                    matched = pattern.matcher(fileLine);

                    // System.out.println("&&&&&&&&&&&");
                    // System.out.println(fileLine);
                    // System.out.println(matched.matches());
                    // System.out.println("&&&&&&&&&&&");
                    if (matched.matches()) {
                        // System.out.println("ReadFile::fileLine::MATCHED::" + fileLine);
                        fileData.add(fileLine);
                        while (((fileLine = textReader.readLine()) != null) && !("".equals(fileLine))) {
                            fileData.add(fileLine); // LEE: Get the next 3 lines from the .txt file
                            // System.out.println("ReadFile::fileLine::MATCHED::EXTRA::" + fileLine);
                        }
                    }
                }
                // System.out.println("ReadFile::WhileLoop::isDetails=FALSE:end");
            }
            textReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("*** Exception occurred while viewing 'Reading File' ***");
            System.out.println(e);
            System.out.println("****************************************************");
        } catch (IOException e) {
            System.out.println("*** Exception occurred while viewing 'Reading File' ***");
            System.out.println(e);
            System.out.println("****************************************************");
        }
        return fileData;
    }

    public static void WriteFile(String fileName, String[] textData, String changeFrom, String changeTo, int tempNum) {
        try {
            // Assume default coding
            FileWriter fileWriter = new FileWriter(fileName);
            try ( // Always wrap FileWriter in BufferedWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                for (int i = 0; i < tempNum; i++) {
                    if (textData[i].contains(changeFrom)) {
                        textData[i] = textData[i].replace(changeFrom, changeTo);
                        bufferedWriter.write(textData[i]);
                        bufferedWriter.newLine();
                    } else {
                        bufferedWriter.write(textData[i]);
                        bufferedWriter.newLine();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while writing to file '" + fileName + "'");
        }

    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    // public static void main(String[] args) throws FileNotFoundException,
    // IOException {
    // String fileName = "temp.txt";
    // ReadFile(fileName, "modify", "change");
    // }

}