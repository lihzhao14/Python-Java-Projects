import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import expenses.Expense;
import expenses.Expenses;

class ExpenseManagementTest {

	//define instance of expenses class for testing
	Expenses expenses;
	
	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		this.expenses = new Expenses();
		
		//initialize individual expenses and add for testing
		Expense expense = new Expense(12, 2.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(10, 98.34);
		this.expenses.addExpense(expense);
		
		expense = new Expense(2, 44.00);
		this.expenses.addExpense(expense);
		
		expense = new Expense(12, 12.01);
		this.expenses.addExpense(expense);
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
	}

	@Test
	void testGetMonthlyExpensesIntListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct (10)
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses(10, this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months
		
	}

	@Test
	void testGetMonthlyExpensesStringListOfExpense() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create list of expected monthly expense amounts
		List<Double> monthlyExpensesExpected = new ArrayList<Double>();
		monthlyExpensesExpected.add(98.34);
		
		//get actual monthly expense amounts for oct
		List<Double> monthlyExpenses = ExpenseManagement.getMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm expected is equal to actual
		assertEquals(monthlyExpensesExpected, monthlyExpenses, "The expected list of monthly expenses for Oct. do not match the actual list of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional lists of expected monthly expense amounts 
		// and compare to actual monthly expense amounts for particular months
	}

	@Test
	void testGetTotalMonthlyExpenses() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//get actual total monthly expense amount for oct
		double totalMonthlyExpenses = ExpenseManagement.getTotalMonthlyExpenses("oct", this.expenses.getMonthlyExpenses());
		
		//confirm it is 98.34
		assertEquals(98.34, totalMonthlyExpenses, "The expected total of monthly expenses for Oct. does not match the actual total of monthly expenses for Oct.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Test other total monthly expense amounts for particular months
	}

	@Test
	void testGetMostExpensiveMonth() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create expense object for expected most expensive month 10 (oct)
		Expense mostExpensiveMonthCompare = new Expense(10, 98.34);
		
		//get expense object for actual most expensive month
		Expense mostExpensiveMonth = ExpenseManagement.getMostExpensiveMonth(this.expenses.getMonthlyExpenses());
		
		//compare expected expense object with actual expense object
		assertEquals(mostExpensiveMonthCompare, mostExpensiveMonth, "The expected most expensive month does not match the actual most expensive month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 2 additional test cases
        // Hint(s): Create additional expense object for a particular month and add to monthly expenses
		// Test if it's the most expensive month
	}

}
