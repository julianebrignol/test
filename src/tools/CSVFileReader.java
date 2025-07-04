package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for reading CSV files
 */
public class CSVFileReader {
    
    /**
     * Reads a CSV file and returns its contents as a list of string arrays
     * @param filePath Path to the CSV file
     * @param hasHeader Whether the CSV file has a header row
     * @return List of string arrays, each representing a row in the CSV
     */
    public static List<String[]> readCSV(String filePath, boolean hasHeader) {
        List<String[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip header if present
            if (hasHeader && (line = br.readLine()) != null) {
                // Optionally process header here if needed
            }
            
            // Read data rows
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return data;
    }
    
    /**
     * Gets the header row from a CSV file
     * @param filePath Path to the CSV file
     * @return String array containing the header values, or null if error or empty file
     */
    public static String[] getCSVHeader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine != null) {
                return headerLine.split(",");
            }
        } catch (IOException e) {
            System.out.println("Error reading file header: " + e.getMessage());
        }
        
        return null;
    }
}
