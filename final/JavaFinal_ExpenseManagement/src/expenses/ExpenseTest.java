package expenses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExpenseTest {

	@Test
	void testEquals() {

		////////////////////////////////////////////////
		///// DO NOT MODIFY THE TEST CODE BELOW! ///////
		
		//create individual expenses
		Expense expense1 = new Expense(12, 2.34);
		Expense expense2 = new Expense(12, 2.34);
		
		//compare for equality
		assertEquals(expense1, expense2, "The 2 expenses should be considered equal. They are for the same amount and the same month.");
		
		///// DO NOT MODIFY THE TEST CODE ABOVE! ///////
		////////////////////////////////////////////////
		
		
		// TODO insert 1 additional test case
        // Hint(s): Create additional expense objects and compare
		
		//Not Equal Months
        Expense expense3 = new Expense(1, 66.66);
        Expense expense4 = new Expense(2, 66.66);

        assertNotEquals(expense3, expense4, "The 2 expenses should not be considered equal. They are for the same amount but different months.");
        
        //Not Equal Amounts
        Expense expense5 = new Expense(5, 77.77);
        Expense expense6 = new Expense(5, 30.00);

        assertNotEquals(expense5, expense6, "The 2 expenses should not be considered equal. They are for the same month but different amounts.");
	
        
        //Not Equal Months And Amounts
        Expense expense7 = new Expense(7, 150.00);
        Expense expense8 = new Expense(8, 250.00);

        assertNotEquals(expense7, expense8, "The 2 expenses should not be considered equal. They are for different amounts and different months.");
	
	}

}
