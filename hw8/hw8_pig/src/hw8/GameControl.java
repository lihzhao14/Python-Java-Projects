/**
 *  @StudentName Lihong Zhao
 *  @PennID: 51007389
 *  Did you do this homework on your own (yes / no): yes
 *  Resources used outside course materials: None
 *  Statement: I admit that this assignment was done by me alone without help.
 */


package hw8;

import java.util.Random;
import java.util.Scanner;

public class GameControl {
	
	/**
	 * Computer player
	 */
	Computer computer;
	
	/**
	 * Human player
	 */
	Human human;
	
	/**
	 * A random number generator to be used for returning random dice result (1-6) for both computer and human player
	 */
	Random random = new Random();
	
	/**
	 * The main method just creates a GameControl object and calls its run method
	 * @param args not used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.println("Welcome to Pig!");
		System.out.println();
		// create a GameControl object
		GameControl gc = new GameControl();
		while (true) {
			gc.run(sc); // call GameControl run method
			System.out.println("--------------------");
			System.out.println("Do you want to play again?");
			
			boolean check = gc.askYesNo(sc);
			if (!check) {
				System.out.println("Goodbye!");
				sc.close();
				break;
			}
		}
	}
	
	/**
     * Calls methods to perform each of the following actions:
     * - Ask user to input the name for human player, and print welcome with the name
     * - Create the players (one human and one computer)
     * - Control the play of the game
     * - Print the final results
	 * @param sc to use for getting user input
	 */
	public void run(Scanner sc) {
		System.out.println("Please input your name");
		String name = sc.next();
		System.out.println("Welcome " + name);
		createPlayers(name);
		
		while (true) {
			computer.move(human, random);
			human.move(computer, random, sc);
			boolean check = checkWinningStatus();
			if (check) {
				printResults();
				printWinner();
				break;
			}
		}
		
	}
	
	/**
     * Creates one human player with the given humanName, and one computer player with a name.
     * @param humanName for human player
     */
	public void createPlayers(String humanName) {
		this.human = new Human(humanName);	// create human player with the given humanName
		this.computer = new Computer();
	}
	
	/**
     * Checks if a winning status has been achieved
     * @return true if one player has won the game
     */
	public boolean checkWinningStatus() {
		int computerStatus = this.computer.getScore();
		int humanStatus = this.human.getScore();
		if (computerStatus < 50 && humanStatus < 50) return false;
		else if (computerStatus < 50 && humanStatus >= 50) return true;
		else if (computerStatus >= 50 && humanStatus < 50) return true;
		else{
			// both of computer and human are greater than 50
			if (computerStatus == humanStatus ) return false;
			else return true;
		}
	}
	
	/**
	 * Prints the final scores of the human player and computer player
	 */
	public void printResults() {
		System.out.println("Human gets " + this.human.getScore());
		System.out.println("Computer gets " + this.computer.getScore());
	}
	
	/**
     * Determines who won the game, and prints the results
     */
	public void printWinner() {
		if (this.computer.getScore() > this.human.getScore()) {
			System.out.println("Computer wins!");
		}
		else {
			System.out.println("Human wins!");
		}
	}
	
	/**
	 * If the user responds a string starting with "y" or "Y", the function returns True. 
	 * If the  user responds a string starting with "n" or "N", the function returns False. 
	 * Any other response will cause the question to be repeated until the user provides an acceptable response. 
	 * @param sc to use for getting user input
	 * @return true if user responds with "y" or "Y"
	 */
	public boolean askYesNo(Scanner sc) {
		boolean result;
		while(true) {
			String input = sc.next();
			char answer = input.charAt(0); // take the first letter of user input
			answer = Character.toLowerCase(answer); // make the letter lower case
			
			if (answer == 'y') {
				result = true;
				break;
			} else if (answer == 'n') {
				result = false;
				break;
			} else {
				System.out.println("Wrong Input. Try again!");
			}
		}
		return result;
	}
	
}
