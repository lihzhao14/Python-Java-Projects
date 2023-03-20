/**
 *  Student Name in Canvas: Lihong Zhao
 *  Penn ID: 51007389
 *  Did you do this homework on your own (yes / no): yes
 *  Resources used outside course materials: None
 *  Statement: I admit that this assignment was done by me alone without help.
 */


package hw7;
import java.util.Scanner;
public class HelloWorld {
	/**
	 * The HelloWorld class is a simple program that performs a variety of tasks,
	 * including greeting the user, summing numbers, identifying even or odd numbers,
	 * determining whether a number is prime or composite, converting seconds to hours,
	 * minutes, and seconds, and saying goodbye to the user.
	 */
	public static void main(String[] args) {
		
		// 1. Say Hello
		/*
		 * a. Ask the user to enter their full name.
		 * b. The user should type in their first name and last name, separated by a space.
		 *   i. Print “Hello, <full name>!” where <full name> gets replaced by the full name of the user.
		 */
		// create instance of Scanner class
		Scanner input1 = new Scanner(System.in);
		// ask the user to enter their full name
		System.out.println("Please Enter Your Full Name (separated first name and last name by a space): ");
		// read the user's full name
		String Name = input1.nextLine();
		System.out.println("Hello, " + Name + "!");
		System.out.println();
		System.out.println("----------");
		System.out.println();
		

		
		// 2. Add Five Numbers
		/*
		 * a. Ask the user to enter a total of 5 numbers (ints or doubles), and hit enter after each. 
		 * Assume each number is an int or a double. 
		 * b. Print the sum (as a double) of all the numbers each time.
		 */
		
		// initialize the scanner for user input
        Scanner input2 = new Scanner(System.in);
        // ask the user to enter 5 numbers
        System.out.println("Please enter 5 numbers (ints or doubles):");

        double sum = 0;
        // loop through 5 times to get user input
        for (int i = 0; i < 5; i++) {
        	// read a number from the user
            double num = input2.nextDouble();
            sum += num;
            System.out.println("Sum for now: " + sum);
        }

        System.out.println("Total sum: " + sum);
        
        
        // 3. Even or Odd
        /*
         * a. Ask the user to enter an integer. 
         * b. Check if the number is even or odd. Assume this will be a positive integer.
         * 	i. If it is even, print “<number> is even”, where <number> gets replaced by the number.
         * 	ii. If it is odd, print “<number> is odd”, where <number> gets replaced by 	the number.
         */
        
        // initialize the scanner for user input
        Scanner input3 = new Scanner(System.in);
        // ask the user to enter an integer
        System.out.println("Please enter an integer:");
        // read the integer from the user
        int x = input3.nextInt();
        // check if the number is even or odd
        if (x % 2 == 0) {
        	// if the number is even, print that it is even
            System.out.println(x + " is even");
        } else {
        	// if the number is odd, print that it is odd
            System.out.println(x + " is odd");
        }
        
        
        
        // 4. Prime or Composite
        /*
         * a. Ask the user to enter a positive integer. Assume this will be a positive integer.
         * b. Check if the number is prime or composite.
         * 	i. If it is prime, print “<number> is prime”, where <number> gets replaced by the number.
         * 	ii. If it is composite, print “<number> is composite”, where <number> gets replaced by the number.
         *  iii. If the number is 1, print 1.
         */
        
        // initialize the scanner for user input
        Scanner input4 = new Scanner(System.in);
        // ask the user to enter a positive integer
        System.out.println("Please enter a positive integer:");
        // read the positive integer from the user
        int num = input4.nextInt();
        
        // check if the number is 1, 2, prime or composite
        if (num == 1) {
            System.out.println("1");
        } else if (num == 2) {
            System.out.println("2 is prime");
        } else {
            boolean isComposite = false;
            // Iterate through numbers from 2 to the square root of num
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isComposite = true; // set isComposite to true if num is divisible by i
                    break;
                }
            }
            if (isComposite) {
                System.out.println(num + " is composite");
            } else {
                System.out.println(num + " is prime");
            }
        }
        
        
        
        // 5. Convert Seconds to Time
        /*
         * a. Ask the user to enter some number of seconds (as an int) and convert it to hours:minutes:seconds.
         * 	i. For example, if input seconds is 1432, print output in the format: 0:23:52
         * 	ii. If input seconds is 0, print output in the format: 0:0:0
         * 	iii. If input seconds is negative, print output in the format: -1:-1:-1
         */
        
        // initialize the scanner for user input
        Scanner input5 = new Scanner(System.in);
        // ask the user to enter a number of seconds
        System.out.print("Please enter a number of seconds: ");
        // read the user's input as an integer and store it in the variable totalSeconds
        int totalSeconds = input5.nextInt();
        int hours, minutes, seconds;
        
        // convert totalSeconds to hours:minutes:seconds format and print the result
        if (totalSeconds < 0) {
        	// If input seconds is negative, print output in the format: -1:-1:-1
            hours = -1;
            minutes = -1;
            seconds = -1;
            System.out.printf("%d:%d:%d\n", hours, minutes, seconds);
        } else if (totalSeconds == 0) {
            // If input seconds is 0, print output in the format: 0:0:0
            hours = 0;
            minutes = 0;
            seconds = 0;
            System.out.printf("%d:%d:%d\n", hours, minutes, seconds);
        } else {
            hours = totalSeconds / 3600; // Calculate hours
            minutes = (totalSeconds % 3600) / 60; // Calculate minutes
            seconds = totalSeconds % 60; // Calculate seconds
            System.out.printf("%d:%02d:%02d\n", hours, minutes, seconds);
        }
        
        
        // 6. Say Goodbye
        /*
         * Print “Goodbye, <full name>!” where <full name> gets replaced by the full name of the user.
         */
        System.out.println("Goodbye, " + Name + "!");
        
	}

}
