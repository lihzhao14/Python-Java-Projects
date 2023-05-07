package expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import expenses.file.ExpenseFileParser;
import expenses.file.ExpenseFileReader;

/**
 * Creates and stores monthly expenses.
 * @author Lihong Zhao
 */
public class Expenses {

	/**
	 * List of monthly expenses.
	 */	
	private List<Expense> monthlyExpenses;
	
	/**
	 * Initializes list of monthly expenses.
	 */
	public Expenses() {
		
		//initialize list of expenses
		this.monthlyExpenses = new ArrayList<Expense>();
	}
	
	/**
	 * Gets list of monthly expenses.
	 * @return list of expenses
	 */
	public List<Expense> getMonthlyExpenses() {
		
		// TODO Implement method
		return this.monthlyExpenses;
	}
	
	/**
	 * Converts each expense in the given list to an Expense object,
	 * and adds it to the internal list of monthly expenses.
	 * @param expenses to add
	 */
	public void addExpenses(List<Map<Integer, Double>> expenses) {
		
		// TODO Implement method
		// Iterate over each month-to-expense map in the expenseList
        for (Map<Integer, Double> monthToExpense : expenses) {
            // Iterate over each month-expense pair in the monthToExpense map
            for (Map.Entry<Integer, Double> monthExpensePair : monthToExpense.entrySet()) {
                // Get the month number from the monthExpensePair
                int month = monthExpensePair.getKey();
                // Get the expense amount from the monthExpensePair
                double amount = monthExpensePair.getValue();
                // Create a new Expense object with the month and amount
                Expense expense = new Expense(month, amount);
                // Add the Expense object to the internal list of monthly expenses
                addExpense(expense);
            }
        }
	}
	
	/**
	 * Adds given Expense object to the internal list of monthly expense objects.
	 * @param expense to add
	 */
	public void addExpense(Expense expense) {
		
		// TODO Implement method
		this.monthlyExpenses.add(expense);
	}
	
	///// DO NOT CHANGE CODE IN MAIN METHOD! /////
	public static void main(String[] args) {
		
		//load expenses.txt file and get list of expenses
		List<String> expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		//parse list of expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpenses = ExpenseFileParser.parseExpenses(expensesList);
				
		//print list of expense maps for debugging purposes
		System.out.println(monthlyExpenses);
		
		//create instance of expenses class
		Expenses expenses = new Expenses();
				
		//add list of maps to internal list of expense objects
		expenses.addExpenses(monthlyExpenses);	
		
	}
}
