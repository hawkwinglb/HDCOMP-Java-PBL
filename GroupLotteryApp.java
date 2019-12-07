import java.util.Scanner;

public class GroupLotteryApp{

	public static void main(String[] args){

		Scanner input = new Scanner(System.in); //get the user input

		int[][] userNumbers; //create an array to store the user numbers

		int[] lotteryNumbers; //to get the lottery numbers from the inst class

		int[] matches; // create an array of matches (coming from the computation in the inst class)

		int gameCounter; //var to count the number of games the user played

		int totalWinnings = 0; //initialize the total winnings to 0 so we can add to this sum the winnings of each game

		double aveWinnings; //create a var to get the average winnings of all games back from the inst class

		int winnings; //create a var to get the winnings per one game back from the inst class

		int linesWon; //create a var to get the number of lines won per one game back from the inst class

		final int MAX_GAMES = 100; //we decided to have a sufficiently big array to store each game history on one row, there is one hundred rows in this array, so the user can play 100 times
		int[][] gameHistory = new int[MAX_GAMES][3]; //initialize the history array

		int noLines; //create a var for the number of lines the user will input

		int controlCounter = 0; //control counter used if the user wants to exit the game or lottery is won

		System.out.println("You are playing a lottery game.\nFor each iteration of the game, six random numbers between 1 and 40 (inclusive) are generated. \nTo win the lottery, you must guess those numbers. \nIn each interation of the game, you may play up to three lines of six numbers. \nYou may continue playing the lottery game until you win the lottery(match six numbers) or until you choose to stop. \nPrize money is attached to matching: \n3 numbers: 100 euro\n4 numbers: 300 euro\n5 numbers: 1500 euro\n6 numbers: you win the entire lottery of 1 000 000 euro.\nAt the end of all game iterations, the average prize money won per game will be calculated and displayed.");


		GroupLottery lottery = new GroupLottery(); //initialize the new GroupLottery Object and initialize the lottery numbers from the constructor
		gameCounter = 1;

		do{ // main game loop
			System.out.println("\nFor game number " + (gameCounter));
			System.out.print("Please enter the number of lines you want to play (between 1 and 3): ");

			noLines = input.nextInt(); // take the input for the number of lines
			while(noLines<1 || noLines>3){ // data validation for number of lines
				System.out.print("That is not a valid number! Please enter number of lines between 1 and 3: ");
				noLines = input.nextInt(); 
			}

			userNumbers = new int[noLines][6]; // initialize userNumbers array with the noLines being the number of rows in the array

			System.out.println("\nThank you! Now, please enter your lucky numbers - between 1 and 40:");

			for(int i =0; i<userNumbers.length; i++){ //iterating through the array userNumber at row  index
				System.out.println("For line " + (i+1));
				for(int j = 0; j < userNumbers[i].length; j++){ //iterating through the array userNumber at column index
					System.out.print("For number " + (j+1)+ ": ");
					int number = input.nextInt(); // take the user input for numbers

					if(number>0 && number<41){ // check if number is in the right range (0,40) inclusive
						if( (number != userNumbers[i][0]) && (number != userNumbers[i][1]) && (number != userNumbers[i][2]) && (number != userNumbers[i][3]) && (number != userNumbers[i][4]) && (number != userNumbers[i][5]) ){
							userNumbers[i][j] = number; //this if statement validates the integer input and stores the user number in the array if number is not already in the array
						} else {
							System.out.println("You have already entered this number! Please only enter unique numbers."); //meaningful messaging if number is already entered
							j=j-1; //goes back of 1 index if number is already present so that the user number is stored at the correct index in the array
						}
					} else {
						System.out.println("That is not a valid number! Please enter a number between 1 and 40."); //meaningful messaging if number is out of the range
						j=j-1; //goes back of 1 index if number is not in range so that the user number is stored at the correct index in the array
					}
				}
				System.out.println();
			}

			lottery.setUserNumbers(userNumbers); //send the userNumber array to instantiable class
			lottery.calculateMatches(); //calculate the matches 

			matches = lottery.getMatches(); //get the number of matches
			
			int lotteryWon = 0; //initialise the lotteryWon variable to 0, so that sum is not overwritten while iterating through the matches array
			for(int i = 0; i < matches.length; i++){
				System.out.println();
				System.out.println("You guessed correctly " + matches[i] + " number(s) on line " + (i+1) + ".");
				if(matches[i] == 6){
					lotteryWon++;
					if(lotteryWon == 1){ //user won the lottery :-D
						System.out.println("You won the whole lottery!!! Congratulations! :-D \nYou can play again soon, but we're going to stop you being greedy. ;-)");
					} else if(lotteryWon > 1){ //user won the lottery more than once and cannot as per our decision
						System.out.println("You guessed all numbers again! But you already won the lottery :) ");
					}
					controlCounter = 1; //after this do-while loop completes, exit the game as decided that the lottery game stops if lottery is won
				}

			}

			lottery.calculateWinnings(); //calculate the winnings won for the game
			winnings = lottery.getWinnings(); //get the winnings won for the game
			linesWon = lottery.getLinesWon(); //get the number of lines won for the game
			totalWinnings = totalWinnings + winnings; //add the winnings of this game to the other games played previously if any

			System.out.println("\nYou won on " + linesWon + " line(s) and you won " + winnings + " euro in this game.");
			//game history here
			gameHistory[gameCounter-1][0] = noLines; //store the number of lines played per game in the history array
			gameHistory[gameCounter-1][1] = linesWon; //store the number of lines won per game in the history array (number of lines where 3 or more numbers matched)
			gameHistory[gameCounter-1][2] = winnings; //store the winnings per game in the history array

			if(controlCounter == 0){ //if user didn't win the lottery

				System.out.print("\nDo you want to play another game?\nPress 1 to play again or press 2 to exit: ");

				int commit = input.nextInt(); //we check what the user wants to do

				while (commit<1 || commit>2){ // data validation for the commit input
					System.out.print("That is not a valid number! Please press 1 to play again or press 2 to exit: "); //1 or 2 only
					commit = input.nextInt();
				}

				if( commit == 1){ //user wants to play again
					gameCounter++; //increments gamecounter variable
				} else { // user doesn't want to play again
					controlCounter = 1; //increment the control counter and closes the game loop
				} //this selection statement allows the user to exit from the whole-game while loop.
			} //this selection statement checks whether the user has already reached a game-ending state.


			lottery.calculateAverage(totalWinnings, gameCounter); //calculate the average of winning across all games
			aveWinnings = lottery.getAveWinnings(); //get the average of winning across all games

		}while(controlCounter != 1); //whole game do-while loop ends

		//fetch history here

		System.out.println("\nThanks for playing! This is the end of the lottery game.");

		lotteryNumbers = lottery.getLottery(); //print the lottery numbers
		System.out.print("The lottery numbers were: "); 
		for(int i=0; i< lotteryNumbers.length;i++){
			System.out.print(lotteryNumbers[i] + " ");
		}

		System.out.println("\nIn total, you played " + gameCounter + " game(s).");

		for(int i= 0; i<gameHistory.length;i++){ //display the number of lines played per game, the number of lines won per game, the total winnings per game
			if(gameHistory[i][0] != 0){
			System.out.println("\nFor game number " + (i+1) + ", you played " + gameHistory[i][0] + " line(s).\nYou won on " + gameHistory[i][1] + " line(s). \nYou won " + gameHistory[i][2] + " euro.");
			}
		}


		if(aveWinnings>0){ // if user won nothing, display meaningful output
			System.out.println("\nYour average winnings per game are " + aveWinnings + " euro.");
			System.out.println("Your total winnings across all games are " + totalWinnings + " euro.\n");
		}
		else{
			System.out.println("You won nothing in any game.\n");
		}

	}
}
