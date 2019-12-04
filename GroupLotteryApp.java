import java.util.*;

public class GroupLotteryApp{

public static void main(String[] args){

	Scanner input = new Scanner(System.in);

	int[][] userNumbers; //array of the numbers user chooses to play the lottery
	int[] matches; //array of the number of matches per line
	int gameCounter; //count the number of games the user plays
	double totalWinnings = 0;
	double aveWinnings; 
	double winnings;
	int linesWon;
	int[][] gameHistory = new int[100][3]; //this is history of the game. It stores each game information on one row (so user can play up to 100 games). Each row contains three values: number of lines played, number of lines won (line won is a line matching at least 3 numbers with the random numbers line), and the total winnings per game.

	
	int noLines;
	int controlCounter = 0;

	System.out.println("You are playing a lottery game.\nFor each iteration of the game, six random numbers between 1 and 40 (inclusive) are generated. \nTo win the lottery, you must guess those numbers. \nIn each interation of the game, you may play up to three lines of six numbers. \nYou may continue playing the lottery game until you win the lottery(match six numbers) or until you choose to stop. \nPrize money is attached to matching: \n3 numbers: 100 euro\n4 numbers: 300 euro\n5 numbers: 1500 euro.\nAt the end of all game iterations, the average prize money won per game will be calculated and displayed.");

	gameCounter = 1; //increment the number of games played. though, what if the user doesn't want to play anymore, is the exit only available at the end of one game?

	do{

		GroupLottery lottery = new GroupLottery();
		
		System.out.println("\nChoose how many lines you want to play in game: " + (gameCounter));
		System.out.println("Enter number of lines: ");

	int putter=0; //control input
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
		int control = userNumbers.length;



				for(int i =0; i<control; i++){
					for(int j = 0; j < 6; j++){
						System.out.println("Enter a whole number between 1 and 40:");
						int number = input.nextInt();

						if(number>0 && number<41){

							if( (number != userNumbers[i][0]) &&(number != userNumbers[i][1]) && (number != userNumbers[i][2]) && (number != userNumbers[i][3]) && (number != userNumbers[i][4]) && (number != userNumbers[i][5]) ){
								userNumbers[i][j] = number;
							}
						//this if statement validates the integer input
							else{
							System.out.println("That is not a valid number. Try again:");
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

			lottery.calculateWinnings(); //
			winnings = lottery.getWinnings();
			linesWon = lottery.getLinesWon();

			System.out.println("You won " + winnings + " in this game. \nYou won " + linesWon + "lines.");

			totalWinnings = totalWinnings + winnings; 
			int integerWinnings = (int)winnings; 

			gameHistory[gameCounter-1][0] = noLines;
			gameHistory[gameCounter-1][1] = linesWon; //number of lines won (number of lines where 3 or more numbers matched) when we do the winnings calculation
			gameHistory[gameCounter-1][2] = integerWinnings; //when we do the winnings calculation

	if(controlCounter != 1){
		System.out.println("Do you want to play another game?\n Press 1 to play again. Press 2 to exit. \n Do not press any other keys.\n");

		int commit = input.nextInt();
		if(commit<1 || commit>2){
			System.out.println("That is not a valid number.\n");
		}
			else if(commit==2){
				controlCounter=0;
			}
			else{
				control=1;
				gameCounter++; //increments gamecounter variable
			} //this selection statement allows the user to exit from the whole-game while loop.
	} //this selection statement checks whether the user has already reached a game-ending state.


	lottery.calculateAverage(totalWinnings, gameCounter);
	aveWinnings = lottery.getAveWinnings();

	}while(controlCounter != 0); 			 //whole-game while loop ends


	//fetch history here
	if(gameCounter>1){
		System.out.println("You played " + gameCounter + " games."); //for correct grammar when displaying the number of games.
	}
	else{
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
