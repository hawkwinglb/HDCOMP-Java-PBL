import java.util.*;

public class GroupLotteryApp{

	public static void main(String[] args){

		Scanner input = new Scanner(System.in); //get the user input

		int[][] userNumbers; //create an array to store the user numbers

		int[] matches; // create an array of matches (coming from the computation in the inst class)

		int gameCounter; //var to count the number of games the user played

		int totalWinnings = 0; //initialize the total winnings to 0 so we can add to this sum the winnings of each game
		
		double aveWinnings; //create a var to get the average winnings of all games back from the inst class

		int winnings; //create a var to get the winnings per one game back from the inst class
		int linesWon; //create a var to get the number of lines won per one game back from the inst class

		final int MAX_GAMES = 100; //we decided to have a sufficiently big array to store each game history on one row, there is one hundred rows in this array, so the user can play 100 times
		int[][] gameHistory = new int[MAX_GAMES][3]; //initialize the history array

		int noLines; //create a var for the number of lines the user will input

		int controlCounter = 0; //control counter for 

		System.out.println("You are playing a lottery game.\nFor each iteration of the game, six random numbers between 1 and 40 (inclusive) are generated. \nTo win the lottery, you must guess those numbers. \nIn each interation of the game, you may play up to three lines of six numbers. \nYou may continue playing the lottery game until you win the lottery(match six numbers) or until you choose to stop. \nPrize money is attached to matching: \n3 numbers: 100 euro\n4 numbers: 300 euro\n5 numbers: 1500 euro.\nAt the end of all game iterations, the average prize money won per game will be calculated and displayed.");
		
		gameCounter = 1;

		do{ //

			GroupLottery lottery = new GroupLottery(); //initialize the new GroupLottery Object

			System.out.println("\nChoose how many lines you want to play in game number " + (gameCounter));
			System.out.println("Please enter number of lines (between 1 and 3): ");


			int putter=0;
			do{
				noLines = input.nextInt();
				if (noLines >=1 && noLines <=3){
				putter = 1;
				}
				else{
				System.out.println("That is not a valid number. Try again.");
				}
			} while(putter!=1);


			userNumbers = new int[noLines][6];
			int control = userNumbers.length; //I wouldn't be able to explain this control.

			for(int i =0; i<control; i++){
				for(int j = 0; j < 6; j++){
					
					System.out.println("Enter your lucky numbers - between 1 and 40:");
					System.out.println("For number " + (j+1) + " on line " + (i+1));
					int number = input.nextInt();

					if(number>0 && number<41){
						System.out.println("This is not a valid number! Enter a number between 1 and 40:");
						if( (number != userNumbers[i][0]) && (number != userNumbers[i][1]) && (number != userNumbers[i][2]) && (number != userNumbers[i][3]) && (number != userNumbers[i][4]) && (number != userNumbers[i][5]) ){
							userNumbers[i][j] = number; //this if statement validates the integer input and stores the user number in the array
						} else {
							System.out.println("You have already entered this number. Please only enter unique numbers.");
							j=j-1;
						}
					}
				}
			}

			lottery.setUserNumbers(userNumbers);
			lottery.calculateMatches();

			matches = lottery.getMatches();

			for(int i = 0; i < matches.length; i++){
				System.out.println("You matched " + matches[i] + " numbers on line " + (i+1) + ".");
				if(matches[i] == 6){
					System.out.println("You won the whole lottery. You can play again soon, but we're going to stop you being greedy.");
					controlCounter = 1; //after this do-while loop completes, exit the game.

				}
			}

			lottery.calculateWinnings();
			winnings = lottery.getWinnings();
			linesWon = lottery.getLinesWon();
			totalWinnings = totalWinnings + winnings;

			System.out.println("You won " + winnings + " in this game. \nYou won " + linesWon + "lines.");

			int integerWinnings = (int)winnings;

			gameHistory[gameCounter-1][0] = noLines;
			gameHistory[gameCounter-1][1] = linesWon; //number of lines won (number of lines where 3 or more numbers matched) when we do the winnings calculation
			gameHistory[gameCounter-1][2] = winnings;

			if(controlCounter != 1){
				System.out.println("Do you want to play another game?\n Press 1 to play again. Press 2 to exit. \n Do not press any other keys.\n");
				int commit = input.nextInt();
				if(commit<1 || commit>2){
					System.out.println("That is not a valid number.\n");
				} else if(commit==2){
					controlCounter=0;
				} else {
					control=1;
					gameCounter++; 	//increments gamecounter variable
				} //this selection statement allows the user to exit from the whole-game while loop.
			} //this selection statement checks whether the user has already reached a game-ending state.




			lottery.calculateAverage(totalWinnings, gameCounter);
			aveWinnings = lottery.getAveWinnings();

		}while(controlCounter != 0); //whole game do-while loop ends


		//fetch history here
		if(gameCounter>1){
			System.out.println("You played " + gameCounter + " games."); //for correct grammar when displaying the number of games.
		} else {
			System.out.println("You played " + gameCounter + " game.");
		}


		if(aveWinnings>0){
			System.out.println("Your average winnings per game are " + aveWinnings + " euro.");
			System.out.println("Your total winnings are " + totalWinnings + " euro.");
		}
		else{
			System.out.println("You won nothing in any game.");
		}

		for(int i= 0; i<gameHistory.length;i++){ //display the number of lines played per game, the number of lines won per game, the total winnings per game
			if(gameHistory[i][0] != 0){
			System.out.println("\nFor game number " + (i+1) + ", you played " + gameHistory[i][0] + " line(s).\nYou won on " + gameHistory[i][1] + " line(s). \nYou won " + gameHistory[i][2] + " euro.");
			}
		}
	}
}
