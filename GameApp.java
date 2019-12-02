//Author: a.falcoz

import java.util.*;


public class GameApp{
	public static void main(String args[]){


		Scanner input = new Scanner(System.in); // input

		System.out.println("Welcome to the lottery game! (Enter 0 to exit now)"); //need to find another exit as array is init at 0
		System.out.println("Please enter a number of lines (between 1 and 3): ");

		int numberOfLines = input.nextInt();// enter the number of lines the user wants

		final int MAX_GAMES = 100;
		int numberOfGames = 0; //initialize the number of games to 0 the user playes
		int timesLotteryWon = 0; //initialize the number of times to 0 the user won the lottery
		int totalWinnings = 0; //initialize the total number of winnings to 0 in order to calculate the average

		int[][] historyGames = new int[MAX_GAMES][3]; //initialize the history array 


		while (numberOfLines!= 0){ // start the game, entire while loop for the game


			while(numberOfLines < 0 || numberOfLines > 3){ //data validation of number of lines
				System.out.println("This is not a valid number! Please enter a number of lines (between 1 and 3): ");
				numberOfLines = input.nextInt(); //ask again for input from the user for number of lines
			}



			int[][] numbers = new int[numberOfLines][6]; //initializing the array when number of lines is validated, array has 0 for all its values



			for(int i=0; i<numberOfLines; i++){ // iterating through the array, per lines

				System.out.println("Thank you! Please enter 6 of your lucky numbers (between 1 and 40): ");
				System.out.println("For line " + (i+1)); // asking input from the user for numbers in each line


				for(int j=0; j< 6; j++){ // iterating through the array columns - each number in line 1, each number in line 2, etc.

					System.out.print("For number " + (j+1) + ": ");
					int number = input.nextInt(); // user input is stored in an outside int

					while(number > 40 || number < 1){ //checking if number is in the range
						System.out.println("This is not a valid number! Please enter 6 of your lucky numbers (between 1 and 40): ");
						System.out.print("For number " + (j+1) + ": "); 
						number = input.nextInt(); // 
					}

					if((number == numbers[i][0])  || (number == numbers[i][1]) || (number == numbers[i][2]) || (number == numbers[i][3]) || (number == numbers[i][4]) || (number == numbers[i][5])){ //checking if number is unique
						System.out.println("You have already entered " + number + "! " + "Please enter a unique number: ");
						j = j-1;
					} else {
					numbers[i][j] = number; //storing the number in the array if unique
					}
				}

			} // array is initialized with user input and now can be compared with lottery numbers

			

			//System.out.println(Arrays.deepToString(numbers)); <-- DO NOT USE - print the userNumbers array



			Game lotteryGame = new Game(); // array of lottery nums is created



			int[] numsLot = lotteryGame.getLotteryNumbers(); //get the lottery numbers
			System.out.println("The lottery numbers are: "); //display the lottery numbers
			for(int i =0; i<numsLot.length; i++){
				System.out.print(numsLot[i] + " ");
			}
			System.out.println(" ");



			lotteryGame.setUserNumbers(numbers); //send the user numbers to the instantiable class
			lotteryGame.compareNumbers(); // compute = compare numbers
			int[] numsAndLinesWon = lotteryGame.getNumsAndLinesWon(); //get the comparison results from the instantiable class

			//System.out.println(Arrays.toString(numsAndLinesWon)); <--DO NOT USE - print the array of comparison of num per lines


			int[] linesWon = new int[3]; //initialize the array to [0,0,0]

			for(int i=0; i<numsAndLinesWon.length; i++){ //print the results (correct numbers per line)
				System.out.println("You had " + numsAndLinesWon[i]+ " correct number(s) for line " + (i+1) + ". ");
				if(numsAndLinesWon[i] >= 3){ //if the line is won, so more than 3 correct numbers, add 1 at the array index of the line.
					linesWon[i] = 1;
				}
			}

			int sumLinesWon = 0; //initiate the sum of lines won
			for(int i=0; i<linesWon.length; i++){
				sumLinesWon = sumLinesWon + linesWon[i]; //calculate the sum of all lines won per game
			}


			//System.out.println(Arrays.toString(numsLot)); <--DO NOT USE - print the lottery number.
			//System.out.println(Arrays.toString(linesWon)); <--DO NOT USE - print the line number won.

			lotteryGame.calculateSumWinnings();
			int winnings = lotteryGame.getSumWinnings(); //get the total lottery money winnings for this game
			System.out.println("For this game, you won " + winnings + " €uros!"); //print the total lottery money winnings for this game

			totalWinnings = totalWinnings + winnings; //add the winnings to the total so we can calculate the average


			String lotteryWon = lotteryGame.getLotteryWinning(); //get the String "you win the lottery"
			if (lotteryWon != null){ //display the String "you win the lottery" only if true
				System.out.println(lotteryWon);
				timesLotteryWon = timesLotteryWon + 1; // add 1 to the number of times user won the lottery
			} 

		
			System.out.println("You played " + numberOfLines + " line(s) in this game.");//display the history showing number of lines played


			//store the number of lines played per game, the number of lines won per game, the total winnings per game
			historyGames[numberOfGames][0] = numberOfLines;
			historyGames[numberOfGames][1] = sumLinesWon; //breaks because less than 3 lines;
			historyGames[numberOfGames][2] = winnings;

			numberOfGames = numberOfGames + 1; // increment the number of games played since user confirmed he wants to play



			System.out.println("Do you want to play again? Press 0 to exit or enter the number of Lines you'd like to play: "); //ask the user if he wants to play again
			numberOfLines = input.nextInt(); 
		}
		

		System.out.println("You played " + numberOfGames + " game(s)."); //Display the number of games played.

		//System.out.println(Arrays.deepToString(historyGames)); <--DO NOT USE - print the array of line number won

		for(int i= 0; i<historyGames.length;i++){ //display the number of lines played per game, the number of lines won per game, the total winnings per game
			if(historyGames[i][0] != 0){
				System.out.println("For game number " + (i+1) + ", you played " + historyGames[i][0] + " line(s), you won on " + historyGames[i][1] + " line(s) and you won " + historyGames[i][2] + " €uros.");
			}
		}

		//for game 1, you played 2 lines, you won 1500 for 3 correct numbers on line 1;

		double averageWinnings = (double)totalWinnings / numberOfGames; //Calculate the winnings average
		System.out.println("Average winnings for all your games is: " + averageWinnings); //display the average winnings
		System.out.println("You won the lottery " + timesLotteryWon + " time(s)!"); //display how many times the user won the lottery

		//display a history of all the games played: number of games played, average winning across all games


	}
}
