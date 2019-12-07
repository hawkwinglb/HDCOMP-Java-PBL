import java.util.*;

public class GroupLottery{

	private int[] lottery; //array of lottery numbers

	private int[][] userNumbers; //array of user numbers

	private int[] matches; //array of matches per line, array is as long as the number of rows in the array of the user numbers (e.g. if user chose 3 lines, array of matches will be [0,0,0], 2 lines [0,0]...
	
	private int winnings; //sum of winnings for one game
	
	private int lineWinnings; //we don't need this one as it is never returned in the app class, can be local to the method calculateWinnings, unless you want to have them printed at the end of one game
	
	private int linesWon; //sum of lines won for one game
	
	private int totalWinnings; //sum of all the games winnings
	
	private double aveWinnings; //average of all the winnings for all the games
	
	private int numberOfGames; //number of games the user played


	private final int MATCH3 = 100; //sum won for 3 matches
	private final int MATCH4 = 300; //sum won for 4 matches
	private final int MATCH5 = 1500; //sum won for 5 matches
	private final int MATCH6 = 1000000; //sum won for 6 matches - entire lottery! :-)
	private final String MATCH_6 = "Congratulations, you won the lottery! :-D "; //user won the lottery :-)



	public GroupLottery(){ //generate the array of lottery (random) numbers
		lottery = new int[6]; //initialize a new array of six values
		for(int i=0; i<6; i=i+1){ //loop through the array
			Random myRandom = new Random(); //generate one random number
			int ranNum = myRandom.nextInt(6)+1; //specify the range in which the random numbers will be picked and store a first number within the range inside the variable ranNum

			if( (ranNum == lottery[0]) || (ranNum == lottery[1]) || (ranNum == lottery[2]) || (ranNum == lottery[3]) || (ranNum == lottery[4]) || (ranNum == lottery[5]) ){ //check uniqueness of the random number picked, compare variable ranNum to each of the value of the array, it will compare to 0 the first time the array is initialized
				i= i-1; //if not unique so if the number is already in the array, index go back of one and a new random number will be picked
				}
			else {
				lottery[i] = ranNum; //if unique, the ranNum value will be stored inside the lottery array
			}
		}
	}


	public void setUserNumbers(int userNumbers[][]){ //take the user numbers from the app class so they can be compared to the lottery numbers
		this.userNumbers=userNumbers;
	}
	
	

	public int[] getLottery(){ //get the lottery numbers back to the app class so they can be displayed to the user
		return lottery;
	}

	/** do we need this? they are only recorded in the instantiable class
	public void setMatches(int matches[]){ 
		this.matches = matches;
	}
	*/
	
	/** Do we need this? They are already stored/existing in the app class
	public int[][] getUserNumbers(){ 
		return userNumbers;
	}*/

	public int[] getMatches(){ //get the array of matches between user numbers and lottery, number of matches per lines
		return matches;
	}


	public int getWinnings(){ 
		return winnings;
	}


	public void calculateMatches(){ //main computation here, compare the array of user numbers and the array of lottery numbers

		matches = new int[userNumbers.length]; //create a new array of the length equivalent to the number of lines the user chose for the game

		for(int i=0; i<userNumbers.length; i++){ //iterate through both arrays of user numbers and lottery and compare the number
			int match = 0; //reinitialise the match number to 0 for each line
			for(int j=0; j<userNumbers[i].length; j++){ // iterate on columns of array of user numbers
				for(int k= 0; k<lottery.length; k++){ // iterate on columns of array of lottery numbers
					if(userNumbers[i][j] == lottery[k]){ //check if nums between user numbers and lottery are the same
						match++; //add 1 to var if num are same
					}
				}
				matches[i] = match; // store number of matches per line, with line number being the index
			}
		}
	}
	
	
	public int getLinesWon(){ //get the number of lines won for one game
		return linesWon;
	}

	public void calculateWinnings(){
		//@alexia==> adding the method to calculate the winnings, @Laurine feel free to change anything here :-)

		winnings = 0; //initialize the sum of the winnings at 0
		linesWon=0; //initialize the sum of linesWon at 0
		for(int i=0; i<matches.length; i++){ //loop into the array of matches (above) - each line represents one index in the array - to add to the entire sum of winnings for 1 game
			if(matches[i] == 6){
				lineWinnings = MATCH6; //store 1 000 000 into lineWinnings
				linesWon +=1; //add 1
			} else if(matches[i] == 5){
				lineWinnings = MATCH5; // store 1 500 into lineWinnings
				linesWon +=1;
			} else if(matches[i] == 4){
				lineWinnings = MATCH4; //store 300 into lineWinnings
				linesWon +=1;
			} else if(matches[i] == 3){
				lineWinnings = MATCH3; //store 100 into lineWinnings
				linesWon +=1;
			}
			winnings = winnings + lineWinnings; //add all line winnings together so these are the winnings for one game
		}
	}

	public void calculateAverage(int totalWinnings, int numberOfGames){ //calculate the average of winnings of all the games
		this.totalWinnings = totalWinnings; //taken from the App class
		this.numberOfGames = numberOfGames; //taken from the App class

		aveWinnings = (double)totalWinnings/numberOfGames; //casting here to double since it is a division
	}

	
	public double getAveWinnings(){ //we could do this inside the calculateAverage in order to get less messy, up to you.
		return aveWinnings;
	}


}
