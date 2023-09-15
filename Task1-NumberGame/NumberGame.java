import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
class NumberGame {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Welcome to the Number Guessing Game!");
        boolean rounds;int roundsWon = 0;
        while(true) {
            rounds = play(read);
            if (rounds) {
                roundsWon++;
            }
            System.out.print("Do you want to play again? (yes/no): ");
            String Again = read.next().toLowerCase();
            if (!Again.equals("yes")) {
                break;
            }
        }
        System.out.println("Thanks for playing!\nRounds Won : "+roundsWon);
        read.close();
    }
    public static boolean play(Scanner read) {
        int max=10,score=0;
        Random random=new Random();
        int number=random.nextInt(100) + 1;
        System.out.println("You can have a Maximum of " + max + " Attempts.");
        while(score<max) {
            System.out.print("Enter a Number to Guess : ");
            try {
                int num=read.nextInt();
                int diff=number - num;
                if(diff==0) {
                    System.out.println("Congratulations! Guess is correct.");
                    System.out.println("Number of attempts you have taken is : "+(score + 1));
                    return true;
                }else if(diff<0) {
                    System.out.println("Guess is too high.");
                }else {
                    System.out.println("Guess is too low.");
                }
                score++;
            }catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                read.nextLine();
            }
        }
        if(score>=max) {
            System.out.println("Maximum attempts reached. The correct number was: " + number);
        }
        return false;
    }
}
