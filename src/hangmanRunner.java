import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hangmanRunner {

	public static void main(String[] args) {
		
		ArrayList<String> words = new ArrayList<String>(); 
        File file = new File("randomWords.txt"); 	// try-catch loop inspiration from my dad
           try { 
               Scanner fileScanner = new Scanner(file); 
               while (fileScanner.hasNextLine()) { 
                   String line = fileScanner.nextLine(); 
                   words.add(line); 
               } 
             fileScanner.close(); 
           } catch (FileNotFoundException e) { // if error is thrown, do this
        	   System.out.println("File not found! Where could it be?");
           } 
         Random random = new Random(); 
         int word = random.nextInt(words.size()); // select random int that respresents a word
         String wordSelected = words.get(word).toLowerCase(); // retrieve word corresponding to int value
         String partialWord = ""; //partialWord is the 'hint' with *s instead of non-guessed characters
         for (int i=0; i < wordSelected.length(); i++) {
        	 if (wordSelected.charAt(i) != ' ') {
        		 partialWord += "*";
        	 }
         }
//         String wordSelected = "java"; // test word
//         String partialWord = "****"; // test word 
         String lettersGuessed = ""; // all letters guessed by user, displayed every turn
         boolean gameOver = false;
         int wrongGuesses = 0;
         while (partialWord.indexOf("*") != -1 && !gameOver) {
        	 Scanner scanner = new Scanner(System.in);
        	 if (wrongGuesses == 1) { // conditional ifs based on number of incorrect guesses
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 for (int j = 0; j < 9; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 2) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 System.out.println("|  /");
        		 for (int j = 0; j < 8; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 3) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 System.out.println("|  / \\");
        		 for (int j = 0; j < 7; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 4) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 System.out.println("|  / \\");
        		 System.out.println("|   |");
        		 for (int j = 0; j < 6; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 5) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 System.out.println("|  / \\");
        		 System.out.println("|   |");
        		 System.out.println("|  /");
        		 for (int j = 0; j < 5; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 6) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   O");
        		 System.out.println("|  / \\");
        		 System.out.println("|   |");
        		 System.out.println("|  / \\");
        		 for (int j = 0; j < 4; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        	 }
        	 if (wrongGuesses == 7) {
        		 System.out.println("|---|");
        		 System.out.println("|   |");
        		 System.out.println("|   |");
        		 System.out.println("|   0");
        		 System.out.println("|  / \\");
        		 System.out.println("|   |");
        		 System.out.println("|  / \\");
        		 for (int j = 0; j < 3; j++) {
        			 System.out.println("|");
        		 }
        		 System.out.println("\nIncorrect guesses: " + wrongGuesses);
        		 System.out.println("GAME OVER");
        		 gameOver = true;
        		 System.out.println("The correct answer is: " + wordSelected);
        	 }
        	 if (wrongGuesses < 7) {
        		 System.out.println("Word: " + partialWord);
        		 System.out.println("Letters guessed so far: " + lettersGuessed);
        		 System.out.print("Guess: ");
        		 String guess = scanner.next();
        		 while (guess.length() == 1 && lettersGuessed.indexOf(guess) >= 0) { // check if letter has already been guessed
        			 System.out.println("Letter '" + guess +"' already chosen! Try again.");
        			 System.out.print("Guess: ");
        			 guess = scanner.next();
        		 }
        		 if (guess.length() == 1) { // if letterguess, add to guessed letters list
        			 lettersGuessed += guess;
        		 }
        		 if (guess.length() == wordSelected.length() && guess.equals(wordSelected)){ // if correct wordguess, loop exits with game win
        			 gameOver = true;
        			 System.out.println("\n You win! The word was '" + wordSelected + "'!");
        		 } else if (guess.length() == 1){ // if correct letterguess, replace * in word with correct letter
        			 String newHint = "";
        			 boolean letterCheck = false;
        			 for (int j=0; j < wordSelected.length(); j++) {
        				 if (wordSelected.charAt(j) == guess.toLowerCase().charAt(0)) {
        					 newHint += guess;
        					 letterCheck = true;
        				 }
        				 else {
        					 newHint += partialWord.charAt(j);
        				 }
        			 }
        			 if (!letterCheck) {
        				 wrongGuesses++;
        			 }
        			 partialWord = newHint;
        		 } else if (guess.length() > 1) { // if incorrect wordguess, add nothing to guessed letter list and display incorrect message
        			 System.out.println("Incorrect! Try again!");
        		 }
        	 }
         }
         System.out.println("\nYou win! The word was '" + wordSelected + "'!"); // if all letters are correctly guessed instead of a wordguess
	}
}
