package battleship;

public abstract class Ship {
	private int bowRow;	//The row that contains the bow (front part of the ship)
	private int bowColumn;	//The column that contains the bow (front part of the ship)
	private int length;	//The length of the ship
	private boolean horizontal;	//A boolean that represents whether the ship is going to be placed horizontally or vertically
	private boolean[] hit;	//An array of booleans that indicate whether that part of the ship has been hit or not
	
	/**
	 * This constructor sets the length property of the particular ship and initializes
	 * the hit array based on that length
	 * 
	 * @param length
	 */
	public Ship(int length)	{
		this.length = length;
//		this.hit = new boolean[4];
		this.hit = new boolean[length];
		for (int i = 0; i < length; i++) {
            this.hit[i] = false;
        }
	}
	
	
	
	//Getters
	/**
	 * 
	 * @return the ship length
	 */
	public int getLength() {
		return this.length;
	}
	
	
	/**
	 * 
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow() {
		return this.bowRow;
	}
	

	/**
	 * Returns the column corresponding to the position of the bow
	 * @return
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	
	/**
	 * 
	 * @return the hit array
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	
	/**
	 * 
	 * @return whether the ship is horizontal (true) or not (false)
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	
	
	// Setters
	/**
	 * Sets the value of bowRow
	 * 
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	
	/**
	 * Sets the value of bowColumn
	 * 
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * Sets the value of the instance variable horizontal
	 * 
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	
	
	//Abstract Methods
	/**
	 * Every specific type of Ship (e.g. BattleShip, Cruiser, etc.) has to 
	 * override and implement this method and return the corresponding ship type.
	 * 
	 * @return the type of ship as a String
	 */
	public abstract String getShipType();
	
	
	
	//Other Methods
	/**
	 * Based on the given row, column, and orientation,
	 * returns true if it is okay to put a ship of this length with its bow in this location;
	 * false otherwise. 
	 * The ship must not overlap another ship, or touch another ship (vertically, horizontally, 
	 * or diagonally)
	 * it must not ”stick out” beyond the array. Does not actually change either the ship or the Ocean
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    // Calculate the starting row and column for checking ship placement
	    int startRow = Math.max(row - 1, 0);
	    int startColumn = Math.max(column - 1, 0);

	    // Calculate the ending row and column for checking ship placement
	    int endRow = horizontal ? row + 1 : row + this.length;
	    int endColumn = horizontal ? column + this.length : column + 1;

	    // Check if the ship goes beyond the ocean's bounds
	    if (endRow > ocean.getRows() || endColumn > ocean.getColumns()) {
	        return false;
	    }

	    // Check for overlaps and adjacent ships in the area where the ship will be placed
	    int maxRow = Math.min(endRow, ocean.getRows());
	    int maxColumn = Math.min(endColumn, ocean.getColumns());

	    for (int i = startRow; i < maxRow; i++) {
	        for (int j = startColumn; j < maxColumn; j++) {
	            // If a cell in the area is occupied, return false (invalid placement)
	            if (ocean.isOccupied(i, j)) {
	                return false;
	            }
	        }
	    }
	    // If all conditions are met, return true (valid placement)
	    return true;
	}
	
	
	/**
	 * “Puts” the ship in the ocean. This involves giving values to the bowRow,
	 * bowColumn, and horizontal instance variables in the ship, and it also involves
	 * putting a reference to the ship in each of 1 or more locations (up to 4) in the ships
	 * array in the Ocean object.
	 * Note: This will be as many as four identical references; 
	 * you can’t refer to a ”part” of a ship, only to the whole ship.
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	    // Assign the ship's position and orientation
	    this.bowRow = row;
	    this.bowColumn = column;
	    this.horizontal = horizontal;

	    // Store the ship's reference in the ocean's ships array
	    for (int i = 0; i < this.length; i++) {
	        if (horizontal) {
	            ocean.getShips()[row][column - i] = this;
	        } else {
	            ocean.getShips()[row - i][column] = this;
	        }
	    }
    }

	
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been
	 * sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates the
	 * bow) and return true; otherwise return false.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		
	}
	
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return
	 */
	boolean isSunk() {
		
	}
	
	
	/**
	 * Returns a single-character String to use in the Ocean’s print method
	 * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk
	 * This method can be used to print out locations in the ocean that have been shot at; it
	 * should not be used to print locations that have not been shot at. Since toString
	 * behaves exactly the same for all ship types, it is placed here in the Ship class.
	 */
	@Override
	public String toString() {
		
	}
	
	
	/**
	 * Returns one of the strings “battleship”, “cruiser”, “destroyer”, or “submarine”, as appropriate
	 * Again, these types of hard-coded string values are good candidates for static final variables
	 * This method can be useful for identifying what type of ship you are dealing with,
	 * at any given point in time, and eliminates the need to use instanceof、
	 * 
	 * @return
	 */
//	@Override
//	public String getShipType() {
//		
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	