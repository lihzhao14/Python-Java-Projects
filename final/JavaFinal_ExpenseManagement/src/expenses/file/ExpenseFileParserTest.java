package expenses.file;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpenseFileParserTest {

	//list to store lines from expenses_sample.txt file
	List<String> expensesListSample;
	
	//list to store lines from expenses.txt file
	List<String> expensesList;
		
	@BeforeEach
	void setUp() throws Exception {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//load expenses_sample.txt file and get list of expenses
		this.expensesListSample = ExpenseFileReader.loadExpenses("expenses_sample.txt");
				
		//load expenses.txt file and get list of expenses
		this.expensesList = ExpenseFileReader.loadExpenses("expenses.txt");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
	}

	@Test
	void testParseExpenses() {
		
		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//parse list of sample expenses into a list of expense maps
		List<Map<Integer, Double>> monthlyExpensesSample = ExpenseFileParser.parseExpenses(this.expensesListSample);
		
		//create arraylist with expected expense maps
		List<Map<Integer, Double>> expectedMonthlyExpensesSample = new ArrayList<Map<Integer, Double>>();
		
		Map<Integer, Double> sampleExpenseMap = new HashMap<Integer, Double>();
		sampleExpenseMap.put(1, 57.38);
		expectedMonthlyExpensesSample.add(sampleExpenseMap);
		
		sampleExpenseMap = new HashMap<Integer, Double>();
		sampleExpenseMap.put(2, 32.88);
		expectedMonthlyExpensesSample.add(sampleExpenseMap);
		
		sampleExpenseMap = new HashMap<Integer, Double>();
		sampleExpenseMap.put(3, 44.64);
		expectedMonthlyExpensesSample.add(sampleExpenseMap);
		
		//compare to actual expense maps
		assertEquals(expectedMonthlyExpensesSample, monthlyExpensesSample, "The expected 3 expense maps do not match the actual 3 expense maps from the sample expenses.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 1 additional test case
        // Hint(s): Parse and test list of expenses from expenses.txt
		//parse list of expenses from expenses.txt into a list of expense maps
	    List<Map<Integer, Double>> monthlyExpenses = ExpenseFileParser.parseExpenses(this.expensesList);

	    //create an ArrayList with expected expense maps
	    List<Map<Integer, Double>> expectedMonthlyExpenses = new ArrayList<Map<Integer, Double>>();

	    Map<Integer, Double> expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(1, 57.38);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(2, 32.88);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(3, 44.64);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(4, 3.09);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(5, 5.06);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(10, 456.99);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(5, 3.99);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(2, 10.00);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(12, 32.00);
	    expectedMonthlyExpenses.add(expenseMap);
	    
	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(1, 57.38);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(2, 1.10);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(5, 0.00);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(7, 999.00);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(4, 4.00);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(12, 5.00);
	    expectedMonthlyExpenses.add(expenseMap);

	    expenseMap = new HashMap<Integer, Double>();
	    expenseMap.put(11, 6.00);
	    expectedMonthlyExpenses.add(expenseMap);


	    // Additional expense maps from your expenses.txt can be added here

	    //compare to actual expense maps
	    assertEquals(expectedMonthlyExpenses, monthlyExpenses, "The expected expense maps do not match the actual expense maps from expenses.txt.");
			
	}

}
