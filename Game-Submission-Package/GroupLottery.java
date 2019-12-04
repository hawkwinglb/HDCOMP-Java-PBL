/** Instantiable class for Lottery application
HDCOMP Software Development Project

@authors Liz Bourke, Alexia Falcoz, Laurine Rolland

*/

import java.util.*;

public class GroupLottery{

	private int[] lottery;

	private int[][] userNumbers;

	private int[] matches;
	private double winnings;
	private double lineWinnings;
	private int linesWon;
	private double totalWinnings;
	private double aveWinnings;
	private int numberOfGames;


	private final double MATCH3 = 100;
	private final double MATCH4 = 300;
	private final double MATCH5 = 1500;
	private final double MATCH6 = 1000000;//@alexia==> adding here the const of six matches for an amount of money won for the entire lottery
	private final String MATCH_6 = "You won the lottery! Stop now.";



	/*public GroupLottery(){
		int[] array1 = new int[6];
		for(int i=0; i<6; i=i+1){
			Random myRandom = new Random();
			int ranNum = myRandom.nextInt(6)+1;
			array1[i] = ranNum;
			}

		int[] lottery = {0,0,0,0,0,0};
		int next_space = 0;

		boolean duplicate = false;
		while(next_space<6){
			int number_picked = array1[next_space];
			for(int i = 0; i<lottery.length; i++){
				if(number_picked == lottery[i]){
					duplicate = true;
					break;
					}
				else{
					duplicate = false;
					lottery[i] = number_picked;
					}
				}
			if(duplicate = false){
				lottery[next_space] = number_picked;
				next_space = next_space +1;
				}
			}
		}*/

	public GroupLottery(){
		lottery = new int[6];
		for(int i=0; i<6; i=i+1){
			Random myRandom = new Random();
			int ranNum = myRandom.nextInt(40)+1;

			if( (ranNum == lottery[0]) || (ranNum == lottery[1]) || (ranNum == lottery[2]) || (ranNum == lottery[3]) || (ranNum == lottery[4]) || (ranNum == lottery[5]) ){
				i= i-1;
				}
			else{
				lottery[i] = ranNum;
				}
			}
		}







	public void setUserNumbers(int userNumbers[][]){
		this.userNumbers=userNumbers;
		}

	public int[][] getUserNumbers(){
		return userNumbers;
		}

	public int[] getLottery(){
		return lottery;
		}

	public void setMatches(int matches[]){
		this.matches = matches;
		}

	public int[] getMatches(){
		return matches;
		}


	public double getWinnings(){
		return winnings;
		}


	public void calculateMatches(){

		matches = new int[userNumbers.length];
		int match = 0;
		for(int i=0; i<userNumbers.length; i++){
			for(int j= 0; j<lottery.length; j++){
				for (int k = 0; k<lottery.length; k++){
				if(userNumbers[i][j] == lottery[k]){
					match++;
					}
				}
		}
			matches[i] = match;
			}
		}

	public int getLinesWon(){
		return linesWon;
		}

	public void calculateWinnings(){
		//@alexia==> adding the method to calculate the winnings, @Laurine feel free to change anything here :-)

		winnings = 0; //initialize the sum of the winnings at 0
		linesWon=0;
		for(int i=0; i<matches.length; i++){ //loop into the array of matches (above) - each line represents one index in the array - to add to the entire sum of winnings for 1 game
			if(matches[i] == 6){
				lineWinnings = MATCH6; //store 1 000 000 into lineWinnings
				linesWon +=1;
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
		//still need to discuss the below part of the method. This part allows to say that the lottery (1 million euros) is only won once per game
		/**
		if(winnings >= 3000000){ //however, user can only win the lottery once, so this will substract the winnings at only one time lottery win (can only win 1 million)
			winnings = winnings - (2 * SIX_NUM); //basically says if more than 3 million, substract 2 million
		} else if(sumWinnings >= 2000000){
			sumWinnings = sumWinnings - SIX_NUM; //basically says if more than 2 million, substract 1 million
		}*/
		//decision taken to consider one lottery victory a win/exit condition
	}

	public void calculateAverage(double totalWinnings, int numberOfGames){
		this.totalWinnings = totalWinnings;
		this.numberOfGames = numberOfGames;

		aveWinnings = totalWinnings/numberOfGames;
		}

	public double getAveWinnings(){
		return aveWinnings;
		}


}
