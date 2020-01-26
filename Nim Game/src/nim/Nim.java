package nim;
 
import java.util.Scanner;
import java.lang.Math;
 
public class Nim {
	
    public static int stones = 3; // for isValid to operate on difString
	
    public static boolean isValidEntry(String n) {
        return "123".contains(n) && n.length() == 1 && Integer.parseInt(n) <= stones;
    }
    public static int drawStones(int difficulty) {
        switch(difficulty) {
        case 1:
            // Always plays 1 on easy difficulty
            return 1;
        case 2:
            // Plays randomly
            return (int)(Math.random() * Math.min(3, stones)) + 1;
        default:
            /*
             * If the number of stones left is one greater than a multiple of 4,
             * the person who's turn it is will lose if the other player plays optimally.
             * A "losing state" is one where your turn has 4n+1 stones for some integer n
             * A "winning state" is any other turn, as you can then force your opponent
             * into a losing state over an over until n=0, where they will have to take
             * the last stone.
             */
             
            switch(stones % 4) {
            case 0:
                return 3; // Forces player into next lowest losing state
            case 2:
                return 1; // Forces player into next lowest losing state
            case 3:
                return 2; // Forces player into next lowest losing state
            default:
                // Computer is in losing state. Plays randomly in hopes of going into a winning state
                return (int)(Math.random() * Math.min(3, stones)) + 1;
            }
        }
    }
    public static int getUserInput(Scanner input) {
        if(stones == 1) {
            System.out.println("There is 1 stone. You are forced to take it.");
            return 1;
        }
         
        // Gets and validates user input, returning their choice
        System.out.print("There are " + stones + " stones. How many would you like? ");
        String choice = input.nextLine();
        while(!isValidEntry(choice)) {
            System.out.print("You must take 1-" + Math.min(3, stones) + " stones. How many would you like? ");
            choice = input.nextLine();
        }
        return Integer.parseInt(choice);
    }
    public static int getCompInput(int difficulty) {
        if(stones == 1) {
            System.out.println("There is 1 stone. The computer is forced to take it.");
            return 1;
        }
         
        // Prints and returns the computer's choice based on the difficulty (see the drawStones method)
        int c = drawStones(difficulty);
        System.out.println("There are " + stones + " stones. The computer takes " + c + (c == 1 ? " stone." : " stones."));
        return c;
    }
    public static void main(String[] args) {
         
        System.out.println("This is the game of Nim. The objective is to force your opponent to take the last stone.\n");
        Scanner input = new Scanner(System.in);
         
        System.out.print("Difficulty:\n1=Easy     2=Medium     3=Hard\n\nEnter your choice: ");
        // Validating difficulty input
        String difString = input.nextLine();
        while(!isValidEntry(difString)) {
            System.out.print("Enter 1, 2, or 3: ");
            difString = input.nextLine();
        }
        System.out.println();
         
        // Sets difficulty number and the starting number of stones (15-30 inclusive)
        int difInt = Integer.parseInt(difString);
        stones = (int)(Math.random() * 16) + 15;
         
         
         
        // INITIALIZING THE GAME
         
        boolean playerWon = false; // becomes true if the computer ends with 0 stones
        while(stones > 0) { // Loop ends when all stones are gone
            stones -= getUserInput(input);
            if(stones == 0) // This would mean that the player loses, so the computer shouldn't play
                break;
            stones -= getCompInput(difInt);
            playerWon = stones == 0;
        }
        input.close();
        
        // Prints corresponding win message
        if(playerWon)
            System.out.print("\nThe player beats the computer!");
        else
            System.out.print("\nThe computer beats the player!");
    }
}
