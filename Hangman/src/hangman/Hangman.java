package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    String word; //word to be guessed
    char[] ans_arr; //char array of word to be guessed
    char[] guess_arr; //char array of what letters have been correctly guesed
    int body = 0; //number of missed guesses
    int correct = 0; //number of correct letters guessed
    ArrayList<Character> guesses = new ArrayList<>(); //list of wrong guesses

    //constructor for Hangman class
    public Hangman() throws FileNotFoundException {
        setup();
    }

    //function for setting up game 
    public void setup() throws FileNotFoundException {
        File file = new File("words.txt");

        //scanner for counting lines of text file
        Scanner count_scan = new Scanner(file);

        //get number of lines --> 852
        int count = 0;
        while (count_scan.hasNextLine()) {
            count++;
            count_scan.next();
        }

        count_scan.close();

        //select random number within number of lines
        Random rand = new Random();
        int rand_num = rand.nextInt(count);

        //find word at this line in text file
        Scanner find_scan = new Scanner(file);
        int line = 0;
        while (line < rand_num) {
            find_scan.nextLine();
            line++;
        }
        word = find_scan.nextLine();
        ans_arr = word.toCharArray();
        guess_arr = new char[word.length()];

        //populate guess array with blank spaces
        for (int i = 0; i < word.length(); i++) {
            guess_arr[i] = '_';
        }
    }

    //function to check if guess is correct
    public boolean check(char guess) {
        boolean isCorrect = false;
        for (int i = 0; i < word.length(); i++) {
            if (ans_arr[i] == guess) {
                guess_arr[i] = guess;
                correct++;
                isCorrect = true;
            }
        }
        if (!isCorrect) {
            body++;
        }
        return isCorrect;
    }

    //function to print hangman picture
    public void printBody() {
        //switch case determiened by value of body --> wrong guesses
        switch (body) {
            case 0:
                System.out.println("  _____");
                System.out.println(" |    ");
                System.out.println(" |   ");
                System.out.println(" |   ");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 1:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |   ");
                System.out.println(" |   ");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 2:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |    |");
                System.out.println(" |   ");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 3:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |   -|");
                System.out.println(" |   ");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 4:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |   -|-");
                System.out.println(" |   ");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 5:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |   -|-");
                System.out.println(" |   /");
                System.out.println(" |");
                System.out.println("---");
                break;
            case 6:
                System.out.println("  _____");
                System.out.println(" |    O");
                System.out.println(" |   -|-");
                System.out.println(" |   / \\");
                System.out.println(" |");
                System.out.println("---");
                break;

        }

    }

    //function to prompt for guess
    public void makeGuess() {

        //prompt and scan guess from command line
        System.out.print("Guess a letter: ");
        Scanner scan = new Scanner(System.in);
        char guess = scan.next().charAt(0);

        //keep prompting until new guess is made
        while (guesses.contains(guess)) {
            System.out.print("You already guessed that! Guess again: ");
            guess = scan.next().charAt(0);
        }

        //check if correct
        check(guess);

        //add guess to list
        guesses.add(guess);
        
    }

    //function to play game
    public void play() {
        //game continues while wrong guesses are less than 6 or word has not been guessed
        while (body < 6 && correct < word.length()) {
            System.out.println("---------------------------");
            for (char c : guess_arr) {
                System.out.print(c + " ");
            }
            System.out.println();
            printBody();
            makeGuess();

        }

        if (correct == word.length()) {
            System.out.println(word);
            System.out.println("!!!!!!!!!!!!!!!!");
            System.out.println("u win");
            System.out.println("!!!!!!!!!!!!!!!!");
        } else {
            printBody();
            System.out.println("word = " + word);
            System.out.println("!!!!!!!!!!!!!!!!");
            System.out.println("u lose");
            System.out.println("!!!!!!!!!!!!!!!!");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        Hangman hm = new Hangman();

        hm.play();
    }

}
