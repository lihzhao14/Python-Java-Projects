package battleship;

public class Ocean {
	
	//quickly determine which ship is in any given location
	private Ship[][]ships = new Ship[10][10];
	
	//The total number of shots fired by the user
	private int shotsFired;
	
	//The number of times a shot hit a ship.
	private int hitCount;	//If the user shoots the same part of a ship more than once, 
							//every hit is counted, even though additional “hits” don’t do the
							//user any good.
	
	//The number of ships sunk (10 ships in all)
	private int shipsSunk;
	
	//Helper functions
    /**
     * 
     * @return the number of rows in the ocean grid
     */
    public int getRows() {
        return this.ships.length;
    }

    /**
     * 
     * @return the number of columns in the ocean grid
     */
    public int getColumns() {
        return this.ships[0].length;
    }
	

	
	
	
	
	//Constructor
	/**
	 * Creates an ”empty” ocean (and fills the ships array with EmptySea objects).
	 * You could create a private helper method to do this.
	 * Also initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
        // Initialize the ships array with EmptySea objects
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                ships[i][j] = new EmptySea();
            }
        }
	}
	
	
	//Methods
	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 * Place larger ships before smaller ones, or you may end up with 
	 * no legal place to put a large ship.
	 * 
	 */
	void placeAllShipsRandomly() {
		

	}
	
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column) {
		return !(this.ships[row][column] instanceof EmptySea);
	}
	
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an
	 * EmptySea), false if it does not.
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column) {
		
	}
	
	
	/**
	 * Returns the number of shots fired (in the game)
	 * @return
	 */
	int getShotsFired() {
		
	}
	
	
	/**
	 * Returns the number of hits recorded (in the game).
	 * @return
	 */
	int getHitCount() {
		
	}
	
	
	/**
	 * Returns the number of ships sunk (in the game)
	 * @return
	 */
	int getShipsSunk() {
		
	}
	
	
	/**
	 * Returns true if all ships have been sunk, otherwise false
	 * @return
	 */
	boolean isGameOver() {
		
	}
	
	
	/**
	 * The methods in the Ship class that take an Ocean parameter 
	 * need to be able to look at the contents of this array;
	 * the placeShipAt() method even needs to modify it.
	 * While it is undesirable to allow methods in one class to directly 
	 * access instance variables in another class,
	 * sometimes there is just no good alternative.
	 * 
	 * @return the 10x10 array of Ships
	 */
	public Ship[][] getShipArray(){
		return this.ships;
	}
	
	
	/**
	 * Prints the Ocean.
	 * row numbers should be displayed along the left edge of the array, 
	 * and column numbers should be displayed along the top
	 * Numbers should be 0 to 9, not 1 to 10.
	 * ‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
	 * ‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing there.
	 * ‘s’: Use ‘s’ to indicate a location containing a sunken ship.
	 * ‘.’: and use ‘.’ (a period) to indicate a location that you have never fired upon
	 * This is the only method in the Ocean class that does any input/output, and it is never 
	 * called from within the Ocean class, only from the BattleshipGame class.
	 */
	void print() {
		
	}
	
	
	/**
	 * USED FOR DEBUGGING PURPOSES ONLY
	 * Like the print() method, this method prints the Ocean with row numbers
	 * displayed along the left edge of the array and column numbers displayed along the top.
	 * Numbers should be 0 to 9, not 1 to 10.
	 * The top left corner square should be 0, 0.
	 * This method shows the location of the ships.
	 * This method can be used during development and debugging, to see where
	 * ships are actually being placed in the Ocean.
	 * It can be called from the BattleshipGame class, after creating the Ocean 
	 * and placing ships in it.
	 * 
	 * ‘b’: Use ‘ b’ to indicate a Battleship.
	 * ‘c’: Use ‘c’ to indicate a Cruiser.
	 * ‘d’: Use ‘d ’ to indicate a Destroyer.
	 * ‘s’: Use ‘ s ’ to indicate a Submarine.
	 * ‘ ‘: Use ‘ ’ (single space) to indicate an EmptySea.
	 */
	void printWithShips() {
		
	}
}
