package hw8;

import java.util.Random;
import java.util.Scanner;

public class Human {
	
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * The score of the current player
	 */
	private int score = 0;
	
	
	/**
	 * Constructs a human player with the given name
	 * @param name of human
	 */
	public Human(String name) {
		this.name = name;
	}

	/**
	 * Controls human player to roll the dice, and prints the related information for each roll.
	 * First of all, this method will automatically roll one time for the human player, 
	 * if the result is 6, the player will have no right to roll again and this method should return 0; 
	 * else, this roll will be added to the total score for this turn.
	 * User should answer Y or N (y or n) to determine whether they want to roll the dice again 
	 * or stop with the current score.
	 * 
	 * This method will also update the human's total score.
	 * -- You can either add the total of all the rolls to the human's total score, within this move method
	 * e.g. this.score += scoreOneRound;
	 *  
	 * -- or you can call the setScore method from outside of this class, after calling this move method 
	 * e.g. int scoreOneRound = human.move(computer, random, sc);
	 *      human.setScore(scoreOneRound + human.getScore());
	 * 
	 * @param computer player
	 * @param random number generator
	 * @param sc to get input from user
	 * @return the score this round (for example, 
	 * 1. the player rolls: 2, 6, then this method should return 0
	 * 2. the player rolls: 5, 5, 2, then this method should return 12
	 * )
	 */
	public int move(Computer computer, Random random, Scanner sc) {
		// initialize human's score in current round
		int score_currentRound = 0;
		boolean process = true;
		System.out.println();
		while(process){
			
			// roll one time for the human player randomly from score 1-6
			int score_oneRound = random.nextInt(6) + 1;
			System.out.println(this.name + " 's roll: " + score_oneRound);
			if (score_oneRound == 6) {
				/*
				 * If human rolls 6, the human's turn ends and return 0
				 */
				score_currentRound = 0;
				System.out.println(this.name + "'s score in this round: " + score_currentRound);
				System.out.println(this.name+ "'s total score is: "+ this.score);
				return this.score;
			} else if (this.score + score_oneRound + score_currentRound >= 50) {
				/*
				 * If the total score (including the current round's score) is greater than or equal to 50,
				 * the human stops rolling and adds the current round's score to the total score
				 */
				score_currentRound += score_oneRound;
				setScore(score_currentRound + this.score);
				System.out.println(this.name + "'s score in this round: " + score_currentRound);
				System.out.println(this.name+ "'s total score is: "+ this.score);
				return this.score;
			} else {
				// accumulates the score from the current roll
				score_currentRound += score_oneRound;
			}
		
			System.out.println("Do you want to roll again?");
			String input = sc.next();
			
			char answer = input.charAt(0); // take the first letter of user input
			answer = Character.toLowerCase(answer); // make the letter lower case
			
			if (answer == 'y') {
				process = true;
			} else if (answer == 'n') {
				process = false;
			}
		}
		
	return this.score;

	}
	
	/**
	 * Get the name of human
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the score of human
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Set the score of human
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
}


