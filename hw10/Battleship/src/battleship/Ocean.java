/**
 * Class Ocean
 * The environment of the game, contains a 10*10 spaces with Class Ship
 * 
 * @author Haojie Zheng & Lihong Zhao
 */
package battleship;
import java.util.Random;

public class Ocean {
	private Ship[][] ships = new Ship[10][10];
	private int shotsFired;
	private int hitCount;
	private int shipsSunk;
	private int[][] Records = new int[10][10];
	
	/**
	 * Initialize the sea with emptySea
	 */
	public Ocean() {
		for(int i = 0; i < 10; i++) {
			for(int j=0; j < 10; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].placeShipAt(i, j, true, this);
			}
		}
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	/**
	 * Place all ships randomly, place larger ships in advance
	 */
	void placeAllShipsRandomly()
	{
		// Start with the biggest ship, battleship
		Random rand = new Random();
		int num1 = 0;
		while(num1 < 1) {
			Battleship battleShip = new Battleship();
			int randRow = rand.nextInt(9);
			int randColumn = rand.nextInt(9);
			boolean randHorizontal = rand.nextBoolean();
			
			if(battleShip.okToPlaceShipAt(randRow, randColumn, randHorizontal, this)) {
				battleShip.placeShipAt(randRow, randColumn, randHorizontal, this);
				num1++;
			}
		}
		
		// Then place cruisers
		int num2 = 0;
		while(num2 < 2) {
			Cruiser cruiser = new Cruiser();
			int randRow = rand.nextInt(9);
			int randColumn = rand.nextInt(9);
			boolean randHorizontal = rand.nextBoolean();
			
			if(cruiser.okToPlaceShipAt(randRow, randColumn, randHorizontal, this)) {
				cruiser.placeShipAt(randRow, randColumn, randHorizontal, this);
				num2++;
			}
		}
		
		// 3 destroyer
		int num3 = 0;
		while(num3 < 3) {
			Destroyer destroyer = new Destroyer();
			int randRow = rand.nextInt(9);
			int randColumn = rand.nextInt(9);
			boolean randHorizontal = rand.nextBoolean();
			
			if(destroyer.okToPlaceShipAt(randRow, randColumn, randHorizontal, this)) {
				destroyer.placeShipAt(randRow, randColumn, randHorizontal, this);
				num3++;
			}
		}
		
		// 4 submarines
		int num4 = 0;
		while(num4 < 4) {
			Submarine submarine = new Submarine();
			int randRow = rand.nextInt(9);
			int randColumn = rand.nextInt(9);
			boolean randHorizontal = rand.nextBoolean();
			
			if(submarine.okToPlaceShipAt(randRow, randColumn, randHorizontal, this)) {
				submarine.placeShipAt(randRow, randColumn, randHorizontal, this);
				num4++;
			}
		}
		
	}
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean isOccupied(int row, int column)
	{
		if (row >= 0 && row < 10 && column >= 0 && column < 10) {
			if (!(ships[row][column] instanceof EmptySea)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, (not an
	 * EmptySea), false if it does not.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	boolean shootAt(int row, int column)
	{
		this.shotsFired += 1;
		this.Records[row][column]=1;
		if(isOccupied(row, column)) {
			if(ships[row][column].isSunk()==false) {
			    this.hitCount += 1;
			    boolean flag = ships[row][column].shootAt(row, column);
			    if(ships[row][column].isSunk()==true) {
			    	this.shipsSunk += 1;
			    }
			    return flag;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the number of shots fired (in the game)
	 * @return
	 */
	int getShotsFired()
	{
		return this.shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in the game).
	 * @return
	 */
	int getHitCount()
	{
		return this.hitCount;
	}
	
	/**
	 * Returns the number of ships sunk (in the game)
	 * @return
	 */
	int getShipsSunk()
	{
		return this.shipsSunk;
	}
	
	/**
	 * Returns true if all ships have been sunk, otherwise false
	 * @return
	 */
	boolean isGameOver()
	{
		if(this.shipsSunk == 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return the 10x10 array of Ships
	 */
	public Ship[][] getShipArray()
	{
		return ships;
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
	void print()
	{
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if(i==0) {
					if(j==0) {
						System.out.print(" ");
					}
					else {
						System.out.print(j-1);
					}
				}
				else {
					if(j==0) {
						System.out.print(i-1);
					}
					else {
						if (Records[i-1][j-1] == 0) {
							System.out.print(".");
						} else {
							System.out.print(ships[i-1][j-1].toString());
						}
					}
				}
				
				
			}
			System.out.println();
		}
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
	void printWithShips()
	{
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if(i==0) {
					if(j==0) {
						System.out.print(" ");
					}
					else {
						System.out.print(j-1);
					}
				}
				else {
					if(j==0) {
						System.out.print(i-1);
					}
					else {
						if ((ships[i-1][j-1] instanceof EmptySea)) {
							System.out.print(" ");
						} else {
							if(ships[i-1][j-1].getShipType()=="battleship") {
								System.out.print("b");
							}
							if(ships[i-1][j-1].getShipType()=="cruiser") {
								System.out.print("c");
							}
							if(ships[i-1][j-1].getShipType()=="destroyer") {
								System.out.print("d");
							}
							if(ships[i-1][j-1].getShipType()=="submarine") {
								System.out.print("s");
							}
						}
					}
				}
				
				
			}
			System.out.println();
		}
	}
	
}
