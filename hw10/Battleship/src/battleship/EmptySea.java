/**
 * Class Empty Sea
 * 
 *  * @author Haojie Zheng & Lihong Zhao
 */
package battleship;

public class EmptySea extends Ship {
	/**
	 * Describes a part of the ocean that doesn’t have a ship in it. (It
	 * seems silly to have the lack of a ship be a type of ship, but this is a
	 * trick that simplifies a lot of things. This way, every location in the
	 * ocean contains a “ship” of some kind.)
	 */
	private final String shipType = "empty";
	
	/**
	 * This zero-argument constructor sets the length variable to 1 by calling the 
	 * constructor in the super class
	 */
	public EmptySea() {
		super(1);
	}
	
	/**
	 * This method just returns the string “empty”
	 */
	@Override
	public String getShipType()
	{
		return this.shipType;
	}
	
	/**
	 * This method overrides shootAt(int row, int column) that is inherited
	 * from Ship, and always returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column)
	{
		return false;
	}
	
	/**
	 * This method overrides isSunk() that is inherited from Ship, and always
	 * returns false to indicate that you didn’t sink anything
	 */
	@Override
	boolean isSunk()
	{
		return false;
	}
	
	/**
	 * Returns the single-character “-“ String to use in the Ocean’s print method.
	 * (Note, this is the character to be displayed if a shot has been fired, but nothing
	 * has been hit.)
	 */
	@Override
	public String toString()
	{
		return "-";
	}
}
