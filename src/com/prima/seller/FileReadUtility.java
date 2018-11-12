package com.prima.seller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for parsing the data of csv file
 */
public class FileReadUtility {

    public static List<String[]> readFile(String csvFile) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<String[]> list = new ArrayList<>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] items = line.split(cvsSplitBy);
                list.add(items);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the input file " + csvFile);
        } catch (IOException e) {
            System.out.println("Error occurred while parsing the file " + csvFile);
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
