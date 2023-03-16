package hw7;
import java.util.Scanner;
public class HelloWorld {

	public static void main(String[] args) {
		
		// 1. Say Hello
		// create instance of Scanner class
		Scanner input1 = new Scanner(System.in);
		System.out.println("Please Enter Your Full Name (separated first name and last name by a space): ");
		String Name = input1.nextLine(); //wait for user input
		System.out.println("Hello, " + Name + "!");
		System.out.println();
		System.out.println("----------");
		System.out.println();
		

		
		// 2. Add Five Numbers
        Scanner input2 = new Scanner(System.in);

        System.out.println("Please enter 5 numbers (ints or doubles):");

        double sum = 0;
        for (int i = 0; i < 5; i++) {
            double num = input2.nextDouble();
            sum += num;
            System.out.println("Sum for now: " + sum);
        }

        System.out.println("Total sum: " + sum);
        
        // 3. Even or Odd
        Scanner input3 = new Scanner(System.in);
        
        System.out.println("Please enter an integer:");
        int x = input3.nextInt();

        if (x % 2 == 0) {
            System.out.println(x + " is even");
        } else {
            System.out.println(x + " is odd");
        }
        
        // 4. Prime or Composite
            
        Scanner input4 = new Scanner(System.in);

        System.out.println("Please enter a positive integer:");
        int num = input4.nextInt();

        if (num == 1) {
            System.out.println("1");
        } else if (num == 2) {
            System.out.println("2 is prime");
        } else {
            boolean isComposite = false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    isComposite = true;
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
        Scanner input5 = new Scanner(System.in);

        System.out.print("Please enter a number of seconds: ");
        int totalSeconds = input5.nextInt();

        int hours, minutes, seconds;

        if (totalSeconds < 0) {
        	// If input seconds is negative, print output in the format: -1:-1:-1
            hours = -1;
            minutes = -1;
            seconds = -1;
        } else {
        	// If input seconds is 0, print output in the format: 0:0:0
            hours = totalSeconds / 3600;
            minutes = (totalSeconds % 3600) / 60;
            seconds = totalSeconds % 60;
        }

        System.out.printf("%d:%02d:%02d\n", hours, minutes, seconds);
        
        
        System.out.println("Goodbye, " + Name + "!");
        
	}

}
