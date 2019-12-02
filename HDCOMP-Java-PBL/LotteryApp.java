import java.util.*;

public class LotteryApp{

	public static void main(String[] args){



		Scanner input = new Scanner(System.in);				 //take input from user

		List<Integer> matchList = new ArrayList<Integer>(); //create list of matches

		int maxlinespergame = 3;
		List<ArrayList<Integer>> linematches = new ArrayList<ArrayList<Integer>>(3); //this is a 2D ArrayList to record matches per line

		for(int v=0; v<maxlinespergame; v++){
			linematches.add(new ArrayList<Integer>());
			}																		//in this for loop we add an ArrayList to the outer ArrayList 3 times, as 																		the maximum number of lines per game is 3.




		int gamecounter =1; 								//create counter for no. of games
		int wincount =0;									//create counter for no. of times whole lottery won
		int totalWinnings =0;								//create variable for total amount of money won
		int size =0;




		int[] matchesPerLine = new int[0];
		/* here above is a one-dimensional array to keep track of matchesPerLine: it will be amended within the game and line loops*/


		List<Integer> lineCountList = new ArrayList<Integer>();
		List<Integer> winningsCounterList = new ArrayList<Integer>();
		List<Integer> lottoVictoryCounter = new ArrayList<Integer>();

		/*these here above are ArrayLists, which are not of predefined length, which will keep track of lines per game (lineCountList), winnings per game (winningsCounterList), and instances where the whole lottery was won (lottoVictoryCounter)

		The number of times the whole lottery was won is being recorded as an integer variable and as an array to allow the game history to display not only how many times the whole lottery was won, but whether it was won in a particular game.

		*/



		System.out.println("This is a lottery game.");
		System.out.println("You may enter up to three lines of six numbers.");


	int control = 1; 											//set control variable for multiple game loops

	while(control!=0){
		/* this loop will allow the game to run until the user decides to stop it with input that changes the control variable to something that isn't 0*/

		Lottery lotto = new Lottery();
		/* The Lottery object generates a new ArrayList of six random numbers every time the game loop runs*/



		int y =0;
		/* we use this control variable in the outer of two nested do-while loops*/

		int linecounter=0;		//this variable keeps track of the number of lines in a game

		do{
			/*This do-while loop contains the instructions for what happens when a user plays between 1 and 3 lines of the lottery game*/

			int[] userAppNumbers = new int[6];  //we create an array of predefined size to hold numbers entered by the user


			int i =0;
			/* we use this control variable in the inner of the two nested do-while loops.*/

			do {
				/*This do-while loop allows the user to enter six integer numbers and have those numbers saved to the userAppNumbers array */

				System.out.println("Enter a whole number between 1 and 40:");
				int number = input.nextInt();


				if(number>0 && number<41){
					userAppNumbers[i] = number;
					i++;
					//this if statement validates the integer input
					}
				else{
					System.out.println("That is not a valid number. Try again:");
					}
				}while(i<6); //inner do-while loop ends


			if(linecounter<=3){
				/*This selection statement uses the linecounter variable to keep track of how many lines are played, and to allow the user to choose to stop after 1 or 2 lines*/

				System.out.println("\n Line has ended. \n Press 1 to continue. Press 2 to finish. \n Do not press any other number.");
				int finish = input.nextInt();

				matchesPerLine = new int[3];		//array to store match values

				if(finish<1 || finish>2){
					System.out.println("That is not a valid number.");
					System.out.println("This may cause errors.");
					linecounter = linecounter;

					}
				else if(finish==2){
					System.out.println("You entered " + Arrays.toString(userAppNumbers)); //Arrays.toString allows the array to be displayed as a string

					lotto.setUserNumbers(userAppNumbers); //this uses the Lottery class methods to set the userNumbers variable
					lotto.compareNumbers();				  //in order to compare userNumbers with the random hiddenNumbers variable

					int match = lotto.getMatch();					//this tells us how many numbers match
					matchList.add(y, match);						//this inserts the match value at index y in the matchList ArrayList.
					linematches.get(linecounter).add(match);		//this inserts the match value into the two-dimensional array: in the first array at the index of linecounter: in the second array at the first available index

					matchesPerLine[linecounter] = match;	//this inserts the match value at index [linecounter] in the matchesPerLine array.
					linecounter++;							//increments linecounter variable
					y=3;									//exits from this iteration of the game


					}
				else if(finish==1){
					System.out.println("You entered " + Arrays.toString(userAppNumbers));
					lotto.setUserNumbers(userAppNumbers);
					lotto.compareNumbers();

					int match = lotto.getMatch();
					matchList.add(y, match);
					linematches.get(linecounter).add(match);		//this inserts the match value into the two-dimensional array: in the first array at the index of linecounter: in the second array at the first available index

					matchesPerLine[linecounter] = match;

					linecounter++;						//increments the linecounter variable, which is a control variable for the outer selection statement
					y++;								//increases control variable for do-while loop


					}


				}

		lineCountList.add((gamecounter-1), linecounter);

		/*we add the current linecounter value (or number of lines played this game) to lineCountList, so we can retrieve this value from any given index of lineCountList (that is, the linecount of any individual game) later*/

		}while(y<3); 	//outer do-while loop ends

		//both do-while loops have ended

			System.out.println("The random numbers for this game were: " + (lotto.getHiddenNumbers())); /*this displays the random numbers for this iteration of the game*/


			int listSize = matchList.size() - size;
			size = listSize;

			/* These two lines are a dirty workaround.

			I want to display matches per line for each of three lines, but sometimes there are fewer than three lines.

			So I set an initial variable size (size=0). Then I get the length of the matchList ArrayList, which is increasing every time we record how many matches are on each line. I create a new variable, listSize, and set it as equal to the (length of matchList)-(size). Then I set my initial variable size to be equal to this new variable, listSize.

			This workaround means that in each iteration of the game, the size variable (which I am using as a control in the selection statement below) is equal to the number of _new_ match values added to the matchList. So size will never be larger than 3 or smaller than 1, but it can also change in each iteration of the game.

			*/


			int sum = 0; 			//this is a variable I am using in the process of calculating winnings

			if (size==3){

				int matchLine1 = matchList.get(0);			//get the value at the index 0 of matchList ArrayList
				lotto.addToMatchList(matchLine1); 			//not necessary but I created this functionality and it might be useful

				lotto.calculateWinnings(matchLine1);		//use Lottery class method to calculate winnings for this line
				sum = sum + lotto.getWinnings();			/*use Lottery getter method to return winnings figure and add it to sum variable in order to 												keep a running total*/

				int matchLine2 = matchList.get(1);
				lotto.addToMatchList(matchLine2);

				lotto.calculateWinnings(matchLine2);
				sum = sum + lotto.getWinnings();

				int matchLine3 = matchList.get(2);
				lotto.addToMatchList(matchLine3);

				lotto.calculateWinnings(matchLine3);
				sum = sum + lotto.getWinnings();

				System.out.println("You matched " + matchLine1 + " on line 1.\n You matched " + matchLine2 + "on line 2.\n You matched " + matchLine3 + " one line 3.");

				if((matchLine1 == 6 || matchLine2 == 6)|| matchLine3 ==6){
					System.out.println("You won " + sum + " and also the whole lottery. You should probably stop playing now.\n");
					wincount++;
					/*This selection statement checks to see if you've won the whole lottery and increments the count of how many times you've won the lottery, */
					}

				System.out.println("You won " + sum + " in this game. \n");

				winningsCounterList.add(sum);
				//this adds the total sum of your winnings for this game to an ArrayList, so that the total winnings of each game can be found later

				lottoVictoryCounter.add(wincount);
				//this adds the count of how many times you've won the lottery to an ArrayList
				}

			else if (size==2){

				int matchLine1 = matchList.get(0);
				lotto.addToMatchList(matchLine1);

				lotto.calculateWinnings(matchLine1);
				sum = sum + lotto.getWinnings();

				int matchLine2 = matchList.get(1);
				lotto.addToMatchList(matchLine2);

				lotto.calculateWinnings(matchLine2);
				sum = sum + lotto.getWinnings();

				System.out.println("You matched " + matchLine1 + " on line 1.\n You matched " + matchLine2 + " on line 2.\n");

				if(matchLine1 == 6 || matchLine2 == 6){
					System.out.println("You won " + sum + " and also the whole lottery. You should probably stop playing now.\n");
					wincount++;
					lottoVictoryCounter.add(wincount);
					}

				System.out.println("You won " + sum + " in this game. \n");
				winningsCounterList.add(sum);
				}
			else {
				int matchLine1 = matchList.get(0);
				lotto.addToMatchList(matchLine1);

				lotto.calculateWinnings(matchLine1);
				sum = sum + lotto.getWinnings();

				System.out.println("You matched " + matchLine1 + " on line 1.");

				if(matchLine1 == 6){
					System.out.println("You won " + sum + " and also the whole lottery. You should probably stop playing now.\n");
					wincount++;
					lottoVictoryCounter.add(wincount);
					}
				System.out.println("You won " + sum + " in this game. \n");
				winningsCounterList.add(sum);
			}









		totalWinnings = totalWinnings + sum;	//adds sum of new winnings to totalWinnings variable



		System.out.println("Do you want to play another game?\n Press 1 to play again. Press 2 to exit. \n Do not press any other keys.\n");

		int commit = input.nextInt();

				if(commit<1 || commit>2){
					System.out.println("That is not a valid number.\n");
					}
				else if(commit==2){
					control=0;

					}
				else{
					control=1;
					gamecounter++; 							//increments gamecounter variable
					} 										//this selection statement allows the user to exit from the whole-game while loop.

		} //whole-game while loop ends


		System.out.println(" \n\n\nYou played " + gamecounter + " games.\n");
		System.out.println(" You won the entire lottery " + wincount + " times.\n");

		double average = (double)totalWinnings/gamecounter;

		System.out.println("You won an average of " + average + " euros per game.\n\n\n");


		int e =0;
		do { 			//this do-while loop iterates through number of games played


			System.out.println("\n In game " + (e+1) + " you played " + (lineCountList.get(e)) + " lines. " ); //use lineCountList ArrayList to remember lines-per-game

			int lines = linematches.get(e).size();
			for(int f = 0; f<lines; f++){
				int newmatches = linematches.get(e).get(f);


				/*this nested for loop iterates inside the while loop to get matches per line per game */

				System.out.println("In game " + (e+1) + " In line " + (f+1) + " you got " + newmatches + " matches.");

				//linesPerGame is the two-dimensional array that remembers matches per line in a given iteration of the game.

				}


			if (winningsCounterList.get(e)>0){

				System.out.println(" You won " + (winningsCounterList.get(e)) + " total euro.");
			}
			else {
				System.out.println(" You won no sum of money in euro.");
				}

			if (lottoVictoryCounter.size()>0){
				/*control for whether you won the lottery at all, because if you didn't, trying to see whether you won it at index[e] will throw an Out Of Bounds Exception for the array*/
				if (lottoVictoryCounter.get(e)>0){
				//control for whether you won the whole lottery
				System.out.println(" You won the whole lottery, for which a sum is not given.");
					}
				}




			e++;
			}while(e<gamecounter);










}
}