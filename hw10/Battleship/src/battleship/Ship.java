/*
 * Class Ship
 * 
 * Superclass of different kind of ships
 * 
 * @author Haojie Zheng & Lihong Zhao
 */

package battleship;

public abstract class Ship {
	// Variables of Ship
	private int bowRow;			//The row that contains the bow (front part of the ship)
	private int bowColumn; 		//The column that contains the bow (front part of the ship)
	private int length; 		//The length of the ship
	private boolean horizontal; //A boolean that represents whether the ship is going to be placed horizontally or vertically
	private boolean[] hit; 		//An array of booleans that indicate whether that part of the ship has been hit or not
	
	/**
	 * This constructor sets the length property of the particular ship and initializes
	 * the hit array based on that length
	 * 
	 * @param length
	 */
	public Ship(int length)
	{
		this.length = length;
		this.hit = new boolean[length];
		for(int i=0; i<length; i=i+1) {
			hit[i] = false;
		}
	}
	
	// Getters of Ship
	/**
	 * 
	 * @return the ship length
	 */
	public int getLength()
	{
		return length;
	}
	
	/**
	 * 
	 * @return the row corresponding to the position of the bow
	 */
	public int getBowRow()
	{
		return bowRow;
	}
	
	/**
	 * Returns the column corresponding to the position of the bow
	 * @return
	 */
	public int getBowColumn() 
	{
		return bowColumn;
	}
	
	/**
	 * 
	 * @return the hit array
	 */
	public boolean[] getHit()
	{
		return hit;
	}
	
	/**
	 * 
	 * @return whether the ship is horizontal (true) or not (false)
	 */
	public boolean isHorizontal()
	{
		return horizontal;
	}
	
	
	// Setters of Ship
	/**
	 * Sets the value of bowRow
	 * 
	 * @param row
	 */
	public void setBowRow(int row)
	{
		this.bowRow = row;
	}
	
	/**
	 * Sets the value of bowColumn
	 * 
	 * @param column
	 */
	public void setBowColumn(int column)
	{
		this.bowColumn = column;
	}
	
	/**
	 * Sets the value of the instance variable horizontal
	 * 
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal)
	{
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
	 * Determine if it is legal to put ship on specific place
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) 
	{
		Ship[][] ships = ocean.getShipArray();
		
		// horizontal case
		if(horizontal == true) {
			// first determine if the row or column is out of range
			if(row < 0 || row >= 10) {
				return false;
			}
			for(int i = 0; i < this.length; i++) {
				if(column+i < 0 || column+i >= 10){
					return false;
				}
			}
			
			// the places this ship should be placed on and its neighbor should be emptySea
			for(int startRow = row - 1; startRow <= row + 1; startRow++) {
				for(int startColumn = column - 1; startColumn <= column + this.length+1; startColumn++){
					if(startRow >= 0 && startColumn >= 0 && startColumn < 10 && startRow < 10 ) {
						Ship testShip = ships[startRow][startColumn];
						if(!(testShip instanceof EmptySea)) {
							return false;
						}
					}
				}
			}
		}
		
		// vertical case
		if(horizontal == false) {
			// first determine if the row or column is out of range
			if(column < 0 || column >= 10) {
				return false;
			}
			for(int i = 0; i < this.length; i++) {
				if(row+i < 0 || row+i >= 10){
					return false;
				}
			}
			
			// the places this ship should be placed on and its neighbor should be emptySea
			for(int startRow = row - 1; startRow <= row+1+this.length; startRow++) {
				for(int startColumn = column - 1; startColumn <= column + 1; startColumn++){
					if(startRow >= 0 && startColumn >= 0 && startColumn < 10 && startRow < 10 ) {
						Ship testShip = ships[startRow][startColumn];
						if(!(testShip instanceof EmptySea)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * place the ship on the ocean based on row column and horizontal
	 * 
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)
	{
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		if(horizontal == true){
			for(int i = 0; i < length; i++) {
				ocean.getShipArray()[row][column+i] = this;
			}
		}
		else {
			for(int j = 0; j < length; j++) {
				ocean.getShipArray()[row+j][column] = this;
			}
		}
	}
	
	/**
	 * If part of the ship is hit
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column)
	{
		// horizontal case
		if(horizontal == true){
			if(row == this.bowRow && column >= this.bowColumn && column <= this.bowColumn+this.length-1) {
				hit[column-this.bowColumn] = true;
				return true;
			}
		}
		else {
			if(column == this.bowColumn && row >= this.bowRow && row <= this.bowRow+this.length-1) {
				hit[row-this.bowRow] = true;
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Determine if the ship is sunk
	 * 
	 * @return
	 */
	boolean isSunk()
	{
		int hitCount = 0; 
		for(int i = 0; i < this.length; i++) {
			if(hit[i] == true) {
				hitCount++;
			}
		}
		
		if(hitCount == this.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Returns a single-character String to use in the Ocean’s print method
	 * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk
	 * This method can be used to print out locations in the ocean that have been shot at; it
	 * should not be used to print locations that have not been shot at. Since toString
	 * behaves exactly the same for all ship types, it is placed here in the Ship class.
	 */
	@Override
	public String toString()
	{
		String str;
		if(this.isSunk()) {
			str = "s";
		}
		else {
			str = "x";
		}
		return str;
	}

}
