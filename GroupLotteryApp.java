import java.util.*;

public class GroupLotteryApp{

public static void main(String[] args){


	Scanner input = new Scanner(System.in);

	int[][] userNumbers;

	int[] matches;

	int gameCounter;

	int lineCounter;

	double totalWinnings;

	double winnings;
	int linesWon;


	int[][] gameHistory = new int[100][3];


	gameCounter = 1;
	int noLines;

	int controlCounter = 0;

	do{

		GroupLottery lottery = new GroupLottery();

		System.out.println("You are playing the lottery. You may play up to three lines of six numbers. You must choose how many lines you want to play in game " + (gameCounter+1));
		System.out.println("Enter number of lines: ");

		//control input


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
					System.out.println("You won the whole lottery. You should stop now.");
					controlCounter = 1; //after this do-while loop completes, exit the game.

					}
			}

			lottery.calculateWinnings();

			winnings = lottery.getWinnings();
			linesWon = lottery.getLinesWon();



			System.out.println("You won " + winnings + " in this game. \nYou won " + linesWon + "lines.");

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
						gameCounter++; 							//increments gamecounter variable
						} 										//this selection statement allows the user to exit from the whole-game while loop.
	} //this selection statement checks whether the user has already reached a game-ending state.





	}while(controlCounter != 0); 			 //whole-game while loop ends


	//fetch history here





}
}