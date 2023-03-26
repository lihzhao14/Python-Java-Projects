package hw8;

import java.util.Random;

public class Computer {
	
	/**
	 * The score of the computer
	 */
	private int score = 0;
	
	/**
	 * Controls computer player to roll the dice, and prints the related information for each roll
	 * First of all, this method will automatically roll one time for the computer player, 
	 * if the result is 6, the computer will have no right to roll and this method should return 0; 
	 * else, this roll will be added to the total score for this turn. 
	 * The computer should play intelligently to determine whether they want to roll the dice again 
	 * or stop with the current score.  It depends on you how to design the strategy for the computer.
	 * 
	 * This method will also update the computer's total score.
	 * -- You can either add the total of all the rolls to the computer's total score, within this move method
	 * e.g. this.score += scoreOneRound;
	 *  
	 * -- or you can call the setScore method from outside of this class, after calling this move method
	 * e.g. int scoreOneRound = computer.move(human, random);
	 *      computer.setScore(scoreOneRound + computer.getScore());
	 * 
	 * @param human player
	 * @param random number generator
	 * @return the score this round (for example, 
	 * 1. the computer rolls: 2, 6, then this method should return 0;
	 * 2. the computer rolls: 5, 5, 2, then this method should return 12;
	 * )
	 */
	public int move(Human human, Random random) {
		// initialize computer's score in current round
		int score_currentRound = 0;
		while(true){
			// roll one time for the computer player randomly from score 1-6
			int score_oneRound = random.nextInt(6) + 1;
			System.out.println();
			System.out.println("Computer's roll: " + score_oneRound);
			if (score_oneRound == 6) {
				/*
				 * If computer rolls 6,  the computer's turn ends and return 0
				 */
				score_currentRound = 0;
				System.out.println("Computer's score in this round is: " + score_currentRound);
				System.out.println("Computer's total score is: " + this.score);
				return this.score;
			} else if (this.score + score_oneRound + score_currentRound >= 50) {
				/*
				 * If the total score (including the current round's score) is greater than or equal to 50,
				 * the computer stops rolling and adds the current round's score to the total score
				 */
				score_currentRound += score_oneRound;
				setScore(score_currentRound + this.score);
				System.out.println("Computer's score in this round is: " + score_currentRound);
				System.out.println("Computer's total score is: " + this.score);
				return this.score;
			} else if (this.score + score_oneRound + score_currentRound - human.getScore() >= 5){
				/* 
				 * if computer score is greater than the user score plus 5,
				 * the computer stops rolling and adds the current round's score to the total score
				 */
				score_currentRound += score_oneRound;
				setScore(score_currentRound + this.score);
				System.out.println("Computer's score in this round is: " + score_currentRound);
				System.out.println("Computer's total score is: " + this.score);
				return this.score;
			} else {
				// accumulates the score from the current roll
				score_currentRound += score_oneRound;
			}
		}
		
	}
	
	/**
	 * Get the score of computer
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Set the score of computer
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
