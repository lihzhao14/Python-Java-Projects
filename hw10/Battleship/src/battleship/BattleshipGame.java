/*
 * Class BattleshipGame
 * The main battleship game process
 * @author Haojie Zheng & Lihong Zhao
 */
package battleship;

import java.util.Scanner;

public class BattleshipGame {
	
	public static void main(String[] args) {
		// initialize the ocean
		Ocean ocean = new Ocean();
		Scanner scan = new Scanner(System.in);
		
		// welcome information
		System.out.println("------------Welcome to Battleship Game------------");
		System.out.println();

		int row = 0;
		int column = 0;
		
		// randomly place all ships
		ocean.placeAllShipsRandomly();
		
		// this is for test
//		ocean.printWithShips();
		
		// when game continues
		while(!ocean.isGameOver()) {
			// show the game UI
			ocean.print();

			boolean inputFlag = false;
			// ask the player to input the place he wants to shot, the input should be like x,x, separated by ,
			while(!inputFlag) {
				System.out.println();
				System.out.println("Please enter the row and column you want to shot: ");
				String input = scan.nextLine();
				if (input != null && input.length() != 0) {
					String[] split = input.split(",");
					row = Integer.valueOf(split[0]);
					column = Integer.valueOf(split[1]);
					inputFlag = true;
				}
				else {
					System.out.println("Invalid input, please enter again.");
				}
			}
			
			// shoot at the given place
			if (ocean.shootAt(row, column)) {
				// if this shoot results a sunk ship
				if (ocean.getShipArray()[row][column].isSunk()) {
					System.out.println("You just sank a " + ocean.getShipArray()[row][column].getShipType() + ".");
				}
			}
			// print the game information
			System.out.println("shotsFired : " + ocean.getShotsFired());
			System.out.println("hitCount : " + ocean.getHitCount());
			System.out.println("Sunk ships : " + ocean.getShipsSunk());
			System.out.println();

			//determine if the game is over
			if(ocean.isGameOver()) {
				System.out.println("Game Over.");
				System.out.println("shotsFired : " + ocean.getShotsFired());
				System.out.println("hitCount : " + ocean.getHitCount());
				System.out.println("Sunk ships : " + ocean.getShipsSunk());
				
				System.out.println();
//				System.out.println("Do you want to play again? Y/N.");
//				String flag = scan.nextLine();
//				
//				if(flag == "Y") {
//					ocean = new Ocean();
//					ocean.placeAllShipsRandomly();
//				}
				scan.close();

			}

			
		}
		
		
	}

}
