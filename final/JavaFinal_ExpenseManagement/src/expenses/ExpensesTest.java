package expenses;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpensesTest {

	//define instance of expenses class for testing
	Expenses expenses;
	
	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//initialize expenses class
		this.expenses = new Expenses();
		
		//initialize individual expenses and add directly for testing
		Expense expense = new Expense(12, 2.34);
		this.expenses.getMonthlyExpenses().add(expense);
		
		expense = new Expense(10, 98.34);
		this.expenses.getMonthlyExpenses().add(expense);
		
		expense = new Expense(2, 44.00);
		this.expenses.getMonthlyExpenses().add(expense);
		
		expense = new Expense(12, 12.01);
		this.expenses.getMonthlyExpenses().add(expense);
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
    
	}
	
	@Test
	void testAddExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//initially confirm size of monthly expenses
		assertEquals(4, this.expenses.getMonthlyExpenses().size(), "The initial count of monthly expenses is incorrect. The expected answer is 4.");
		
		//add new individual expense
		Expense expense = new Expense(3, 4.34);
		this.expenses.addExpense(expense);
				
		//confirm new size of monthly expenses
		assertEquals(5, this.expenses.getMonthlyExpenses().size(), "After adding an expense, the count of monthly expenses is incorrect. The expected answer is 5.");
		//confirm added expense is correct
		//it should have the correct month and expense amount
		//this relies on equals method in expense class
		assertEquals(expense, this.expenses.getMonthlyExpenses().get(4), "New expense not added correctly to list of monthly expenses.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional expense objects and add to monthly expenses
		
	    // Additional test case 1:
	    // Adding an expense with a different month and amount, 
	    // then checking if the expense is added correctly and the list size is updated.
	    Expense additionalExpense1 = new Expense(1, 12.34);
	    this.expenses.addExpense(additionalExpense1);
	    assertEquals(6, this.expenses.getMonthlyExpenses().size(), "After adding an additional expense, the count of monthly expenses is incorrect. The expected answer is 6.");
	    assertEquals(additionalExpense1, this.expenses.getMonthlyExpenses().get(5), "New additional expense not added correctly to list of monthly expenses.");
	    
	    // Additional test case 2:
	    // Adding another expense with a different month and amount,
	    // then checking if the expense is added correctly and the list size is updated.
	    Expense additionalExpense2 = new Expense(7, 20.00);
	    this.expenses.addExpense(additionalExpense2);
	    assertEquals(7, this.expenses.getMonthlyExpenses().size(), "After adding another additional expense, the count of monthly expenses is incorrect. The expected answer is 7.");
	    assertEquals(additionalExpense2, this.expenses.getMonthlyExpenses().get(6), "Another new additional expense not added correctly to list of monthly expenses.");
		
	}
	
	@Test
	void testAddExpenses() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//initially confirm size of monthly expenses
		assertEquals(4, this.expenses.getMonthlyExpenses().size(), "The initial count of monthly expenses is incorrect. The expected answer is 4.");
				
		//manually create list of expense maps 
		List<Map<Integer, Double>> expenseList = new ArrayList<Map<Integer, Double>>();
		
		//create and add individual expense maps
		Map<Integer, Double> entry = new HashMap<Integer, Double>();
		entry.put(3, 4.34);
		expenseList.add(entry);
		
		entry = new HashMap<Integer, Double>();
		entry.put(6, 1000.00);
		expenseList.add(entry);
		
		entry = new HashMap<Integer, Double>();
		entry.put(6, 604.56);
		expenseList.add(entry);
		
		//add list of expense maps 
		this.expenses.addExpenses(expenseList);
		
		//confirm new size of monthly expenses
		assertEquals(7, this.expenses.getMonthlyExpenses().size(), "After adding an expense, the count of monthly expenses is incorrect. The expected answer is 7.");
		
		//confirm added expense is correct
		//it should have the correct month and expense amount
		//this relies on equals method in expense class
		assertEquals(new Expense(3, 4.34), this.expenses.getMonthlyExpenses().get(4), "An expense from the list of expenses was not added correctly to list of monthly expenses.");

		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////

		
		// TODO insert 2 additional test cases
        // Hint(s): Confirm other added expenses are correct	
		
	    // Additional test case 1:
	    // Confirming if the second expense from the list of expenses is added correctly to list of monthly expenses.
	    assertEquals(new Expense(6, 1000.00), this.expenses.getMonthlyExpenses().get(5), "Another expense from the list of expenses was not added correctly to list of monthly expenses.");

	    // Additional test case 2:
	    // Confirming if the third expense from the list of expenses is added correctly to list of monthly expenses.
	    assertEquals(new Expense(6, 604.56), this.expenses.getMonthlyExpenses().get(6), "One more expense from the list of expenses was not added correctly to list of monthly expenses.");
	}
	
}
