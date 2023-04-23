/**
 * Ocean Class Test
 * @author Haojie Zheng & Lihong Zhao
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests
		// place a battleship on the ocean
		Ship battleship = new Battleship();
		battleship.placeShipAt(7, 1, true, ocean);
		// see if these places are occupied
		assertTrue(ocean.isOccupied(0, 0));
		assertTrue(ocean.isOccupied(7, 1));
		assertTrue(ocean.isOccupied(7, 3));
		assertFalse(ocean.isOccupied(6, 3));
		assertFalse(ocean.isOccupied(9, 9));

		
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(2, 5));
		
		//TODO
		//More tests
		
		// after second shot see if destroyer is sunk
		assertTrue(destroyer.isSunk());
		
		// place a battleship on the ocean
	    Ship battleship = new Battleship();
	    battleship.placeShipAt(5, 5, true, ocean);
	    // shot several times until it sinks
		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(5, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(5, 7));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(5, 8));
		assertTrue(battleship.isSunk());


	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(2, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());  
		
		//TODO
		//More tests
		// place a battleship on the ocean
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		// shot several times missed
		assertFalse(ocean.shootAt(3, 5));
		assertFalse(ocean.shootAt(4, 5));
		// see if the count of shots fired works
		assertEquals(8, ocean.getShotsFired());  
		
		// shot several times on target
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 7));
		assertTrue(ocean.shootAt(5, 8));
		assertTrue(destroyer.isSunk());
		// see if the count of shots fired works
		assertEquals(12, ocean.getShotsFired());  


	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests
		// place a battleship on the ocean
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		// shot several times, both missed and on target
		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 7));
		assertTrue(ocean.shootAt(5, 8));	
		assertTrue(battleship.isSunk());
		// see if the count of hits works
		assertEquals(5, ocean.getHitCount());
		
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests
		// add a battleship on the ocean
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		// if shots fire works for the battleship
		assertTrue(ocean.shootAt(5, 5));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 7));
		assertTrue(ocean.shootAt(5, 8));	
		assertTrue(battleship.isSunk());
		// after 4 shots see if it is sunk
		assertEquals(1, ocean.getShipsSunk());
		// shot the given destroyer to see if it is sunk
		assertTrue(ocean.shootAt(2, 5));
		assertEquals(2, ocean.getShipsSunk());

		

	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		// add a horizontal battleship on 5,5
		Ship battleship = new Battleship();
		battleship.placeShipAt(5, 5, true, ocean);
		// test the place of battleship
		assertEquals("battleship", shipArray[5][5].getShipType());
		assertEquals("battleship", shipArray[5][6].getShipType());
		assertEquals("battleship", shipArray[5][7].getShipType());
		assertEquals("battleship", shipArray[5][8].getShipType());
		assertEquals("empty", shipArray[5][9].getShipType());
		
		// add another submarine to test these places
		Ship submarine = new Submarine();
		submarine.placeShipAt(7, 7, true, ocean);
		assertEquals("empty", shipArray[7][8].getShipType());
		assertEquals("submarine", shipArray[7][7].getShipType());

	}
	
	@Test
	void testGameOver() {
		// initialize the ocean
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		// now the game is not over yet
		assertEquals(ocean.isGameOver(), false);
		
		// shoot one place and the game continues
		ocean.shootAt(1,1);
		assertEquals(ocean.isGameOver(), false);

		
		// shot every place on the ocean
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
	    // now the game must be over
		assertEquals(ocean.isGameOver(), true);

	}

}
