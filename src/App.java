import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Random random = new Random();
        int rounds = 3;
        int maxAttempts = 10;
        int totalScore = 0;

        for (int round = 1; round <= rounds; round++) 
        {
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("Round " + round + ": Guess the number between 1 and 100");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.print("Attempt " + (attempts + 1) + ": ");
                int guess = Integer.parseInt(reader.readLine());

                if (guess < 1 || guess > 100) 
                {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }

                attempts++;
                
                if (guess < targetNumber)
                {
                    System.out.println("Guess a little higher.");
                } 
                
                else if (guess > targetNumber) 
                {
                    System.out.println("Guess a little lower.");
                }
                
                else 
                {
                    System.out.println("Congratulations, you guessed the correct number!");
                    guessedCorrectly = true;
                }
            }

            int roundScore = guessedCorrectly ? (maxAttempts - attempts + 1) : 0;
            totalScore += roundScore;
            System.out.println("Round " + round + " score: " + roundScore);
        }

        System.out.println("Game over! Your total score: " + totalScore);

    }
}

      

  
