package expenses;

import java.util.Objects;

/**
 * Represents a single expense for a particular month.
 * @author Lihong Zhao
 */
public class Expense {

	/**
	 * Number of month for expense.
	 */
	private int month;
	
	/**
	 * Amount of expense.
	 */
	private double amount;
	
	/**
	 * Creates Expense with given month number and amount.
	 * @param month for expense
	 * @param amount for expense
	 */
	public Expense(int month, double amount) {
		this.month = month;
		this.amount = amount;
	}
	
	/**
	 * Get month of expense.
	 * @return month
	 */
	public int getMonth() {
		return this.month;
	}
	
	/**
	 * Get amount of expense.
	 * @return amount
	 */
	public double getAmount() {
		return this.amount;
	}
	
	/**
	 * Returns the month number and amount for expense.
	 */
	@Override 
	public String toString() {
		return this.month + " : " + this.amount;
	}
	
	/**
	 * Compares two Expense objects for equality, based on the months and amounts.
	 * If the month and amount of one Expense object is equal to 
	 * the month and amount of the other Expense object, 
	 * the two Expense objects are equal.
	 */
	@Override 
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Expense expense = (Expense) o;

        return month == expense.month && Double.compare(expense.amount, amount) == 0;
    }

    /**
     * Generates a hash code for this Expense object.
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(month, amount);
    }
}