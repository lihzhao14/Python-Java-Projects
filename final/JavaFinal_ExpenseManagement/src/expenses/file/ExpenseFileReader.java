package expenses.file;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


/**
 * Manages the loading and parsing of expense files.
 * @author Lihong Zhao
 *
 */
public class ExpenseFileReader {

	/**
	 * Loads the given filename and adds each line to a list.
	 * Ignores lines with only whitespace.
	 * @param fileName to load
	 * @return list of lines from the file
	 */
	public static List<String> loadExpenses(String fileName) {
		
        List<String> expenseLines = null;

        try {
            // Read lines from the file using the filePath
            expenseLines = Files.lines(Paths.get(fileName))
                                // Strip leading and trailing whitespace from each line
                                .map(String::trim)
                                // Filter out lines that are empty after trimming
                                .filter(line -> !line.isEmpty())
                                // Collect the non-empty lines into a list
                                .collect(Collectors.toList());
        } catch (IOException e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }

        // Return the list of non-empty lines
        return expenseLines;
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses_sample.txt file and get list of expenses
		List<String> expensesListSample = ExpenseFileReader.loadExpenses("expenses_sample.txt");
		
		//print sample expenses
		System.out.println("SAMPLE EXPENSES\n");
		for (String line : expensesListSample) {
			System.out.println(line);
		}
		
		//blank line
		System.out.println();
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//print expenses 
		System.out.println("EXPENSES\n");
		for (String line : expensesList) {
			System.out.println(line);
		}
	}
}