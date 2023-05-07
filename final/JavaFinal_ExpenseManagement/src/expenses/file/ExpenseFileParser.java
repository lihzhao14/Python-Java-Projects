package expenses.file;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manages the loading and parsing of expense files.
 * @author lbrandon
 *
 */
public class ExpenseFileParser {

	/**
	 * Parses month and expense amount from given list of expenses (returned from ExpenseFileReader.loadExpenses).
	 * Each row has a month number, a delimiter, and an expense amount.
	 * Delimiters can include a comma (,), a colon (:), or multiple spaces ('    ').
	 * 
	 * Stores expenses in list of maps, each one consisting of a month number and an expense amount.
	 * For example:
	 *   [{1=57.38}, {5=5.06}, {10=456.99}, {5=3.99}, ...]
	 *   Where 1 is the month (for jan) and 57.38 is an expense for that month.  
	 *   5 is the month (for may) and 5.06 is an expense amount for that month.
	 *   3.99 is another expense amount for month 5.
	 * @param list of expenses to parse
	 * @return list of maps of monthly expenses
	 */
	public static List<Map<Integer, Double>> parseExpenses(List<String> expenseList) {
		
		// Create a regex pattern to match delimiters and capture month and amount
        Pattern pattern = Pattern.compile("(\\d+)[,:\\s]+(\\d+(\\.\\d{1,2})?)");

        List<Map<Integer, Double>> monthlyExpenses = new ArrayList<>();

        // Iterate over each expense line in the expense list
        for (String expenseLine : expenseList) {
            Matcher matcher = pattern.matcher(expenseLine);
            if (matcher.matches()) {
                // Extract the month and amount from the matched groups
                Integer month = Integer.parseInt(matcher.group(1));
                Double amount = Double.parseDouble(matcher.group(2));

                // Create a map with the month and amount
                Map<Integer, Double> expense = new HashMap<>();
                expense.put(month, amount);

                // Add the map to the list of monthly expenses
                monthlyExpenses.add(expense);
            }
        }

        // Return the list of monthly expenses
        return monthlyExpenses;
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses_sample.txt file and get list of expenses
		List<String> expensesListSample = ExpenseFileReader.loadExpenses("expenses_sample.txt");
		
		//parse list of sample expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpensesSample = ExpenseFileParser.parseExpenses(expensesListSample);
				
		//print list of sample expense maps for debugging purposes
		System.out.println("SAMPLE EXPENSES\n");
		for (Map<Integer, Double> monthlyExpense : monthlyExpensesSample) {
			System.out.println(monthlyExpense);
		}
		
		//blank line
		System.out.println();
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//parse list of expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpenses = ExpenseFileParser.parseExpenses(expensesList);
				
		//print list of expense maps for debugging purposes
		System.out.println("EXPENSES\n");
		for (Map<Integer, Double> monthlyExpense : monthlyExpenses) {
			System.out.println(monthlyExpense);
		}
		
	}
}