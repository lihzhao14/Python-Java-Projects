/**
 * Ship Class Test
 * @author Haojie Zheng & Lihong Zhao
 */

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests
		// test 3 different ship
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		ship = new Submarine();
		assertEquals(1, ship.getLength());
		ship = new EmptySea();
		assertEquals(1, ship.getLength());

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 2;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		
		// test 3 different ship with different row
		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests
		
		// test 3 different ship with different column
		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests
		
		// test 3 different ship's hit array

		ship = new Cruiser();
		hits = new boolean[3];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		ship = new Destroyer();
		hits = new boolean[2];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		ship = new Submarine();
		hits = new boolean[1];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests
		
		// test 3 different ship's type
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());


	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	
		// test 3 different ship with different horizontal

		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.isHorizontal());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertFalse(submarine.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
		// test 3 different ship with several Row

		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.setBowRow(row);
		assertEquals(row, destroyer.getBowRow());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.setBowRow(row);
		assertEquals(row, submarine.getBowRow());
		
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests
		// test 3 different ship with several column
		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.setBowColumn(column);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests
		
		// test 3 different ship with different horizontal

		Ship cruiser = new Cruiser();
		row = 3; 
		column = 3;
		horizontal = false;
		cruiser.setHorizontal(horizontal);
		assertFalse(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		row = 4; 
		column = 5;
		horizontal = true;
		destroyer.setHorizontal(horizontal);
		assertTrue(destroyer.isHorizontal());
		
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		submarine.setHorizontal(horizontal);
		assertFalse(submarine.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ocean ocean = new Ocean();
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests
		
		// test a ship placed in an invalid place (out of range)
		Ship destroyer = new Destroyer();
		row = -1; 
		column = 10;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		// test a ship placed in an invalid place (out of range)
		destroyer = new Destroyer();
		row = 0; 
		column = 9;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
		
		// test a ship placed in a valid place
		destroyer = new Destroyer();
		row = 0; 
		column = 9;
		horizontal = false;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// test a ship placed in a valid place
		Ship submarine = new Submarine();
		row = 7; 
		column = 7;
		horizontal = false;
		ok = submarine.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests
		// curiser shouldn't allowed to be placed on 0,4
		Ship cruiser = new Cruiser();
		row = 0; 
		column = 4;
		horizontal = false;
		boolean ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
			
		// curiser shouldn't allowed to be placed on 1,4
		Ship cruiser2 = new Cruiser();
		row = 1; 
		column = 4;
		horizontal = false;
		ok = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok, "not OK to place ship here.");
				
		// curiser can be placed on 2,4
		row = 2; 
		column = 4;
		horizontal = false;
		ok = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "not OK to place ship here.");

	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals("battleship", ocean.getShipArray()[0][4].getShipType());
		assertEquals("battleship", ocean.getShipArray()[0][5].getShipType());
		assertEquals("battleship", ocean.getShipArray()[0][6].getShipType());
		assertEquals("battleship", ocean.getShipArray()[0][7].getShipType());
		assertEquals("empty", ocean.getShipArray()[0][8].getShipType());
		

		//TODO
		//More tests
		// place a cruiser on the ocean
		Ship cruiser = new Cruiser();
		row = 3;
		column = 3;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		// test the type of place we drop the ship and its neighbors
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals("cruiser", ocean.getShipArray()[3][3].getShipType());
		assertEquals("cruiser", ocean.getShipArray()[4][3].getShipType());
		assertEquals("cruiser", ocean.getShipArray()[5][3].getShipType());
		assertEquals("empty", ocean.getShipArray()[6][3].getShipType());
		assertEquals("empty", ocean.getShipArray()[3][4].getShipType());
		
		// place a destroyer on the ocean
		Ship destroyer = new Destroyer();
		row = 7;
		column = 3;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		// test the type of place we drop the ship and its neighbors

		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertFalse(destroyer.isHorizontal());
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals("destroyer", ocean.getShipArray()[7][3].getShipType());
		assertEquals("destroyer", ocean.getShipArray()[8][3].getShipType());
		assertEquals("empty", ocean.getShipArray()[7][5].getShipType());
		assertEquals("empty", ocean.getShipArray()[7][4].getShipType());
		assertEquals("empty", ocean.getShipArray()[3][4].getShipType());
		
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, true, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
		// place a cruiser on the ocean, shot it several times see if the shot function works
		Ship cruiser = new Cruiser();
		row = 2;
		column = 2;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(cruiser.shootAt(2, 2));
		assertTrue(cruiser.shootAt(2, 3));
		assertTrue(cruiser.shootAt(2, 4));
		assertFalse(cruiser.shootAt(2, 5));

		// test the hit array
		boolean[] hitArray1 = {true, true, true};
		assertArrayEquals(hitArray1, cruiser.getHit());
		
		// place a destroyer on the ocean, shot it several times see if the shot function works
		Ship destroyer = new Destroyer();
		row = 4;
		column = 4;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(destroyer.shootAt(4, 4));
		assertTrue(destroyer.shootAt(5, 4));
		assertFalse(destroyer.shootAt(4, 6));
		assertFalse(destroyer.shootAt(3, 4));
		// test the hit array
		boolean[] hitArray2 = {true, true};
		assertArrayEquals(hitArray2, destroyer.getHit());
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		assertTrue(submarine.shootAt(3,3));
		assertTrue(submarine.isSunk());
		
		// place a battleship on ocean, shot it several times to see if it sinks
		Ship battleship = new Battleship();
		row = 1;
		column = 5;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship.shootAt(5, 2));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(1, 5));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(1, 6));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(1, 7));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(1, 8));
		assertTrue(battleship.isSunk());
		
		// place a destroyer on ocean, shot it several times to see if it sinks
		Ship destroyer = new Destroyer();
		row = 5;
		column = 8;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertFalse(destroyer.shootAt(5, 7));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(5, 8));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(6, 8));
		assertTrue(destroyer.isSunk());
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 1;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(1, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests
		// test toString before and after a ship sinks
		battleship.shootAt(2, 1);
		battleship.shootAt(3, 1);
		battleship.shootAt(4, 1);
		assertEquals("s", battleship.toString());
		
		Ship submarine = new Submarine();
		row = 3;
		column = 3;
		horizontal = true;
		
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(1, 1);
		assertEquals("x", submarine.toString());
		submarine.shootAt(3, 3);
		assertEquals("s", submarine.toString());
		
	}

}
