import java.util.*;


public class Game{

	private int[][] userNumbers;
	private int[] lotteryNumbers;
	private int[] numsAndLinesWon;
	private int numsWon;
	private int lineWinnings;
	private int sumWinnings;
	private String winLottery;
	private final int THREE_NUM = 125;
	private final int FOUR_NUM = 300;
	private final int FIVE_NUM = 1500;
	private final int SIX_NUM = 1000000;
	private final int LOTTERY_TWICE = 2000000;
	private final int LOTTERY_THREE_TIMES = 3000000;
	private final String LOTTERY_WON = "Congratulations! YOU WON THE LOTTERY! :-D";



	public Game(){ //constructor, create a lottery array

		lotteryNumbers = new int[6]; // initialize variable with 6 values at 0

		for(int i=0; i< lotteryNumbers.length; i++){
			Random myRan = new Random(); //create an object of type random
			int ranNum = myRan.nextInt(40)+1; //stores a random number into ranNum variable

			if((ranNum == lotteryNumbers[0]) || (ranNum == lotteryNumbers[1]) || (ranNum == lotteryNumbers[2]) || (ranNum == lotteryNumbers[3]) || (ranNum == lotteryNumbers[4]) || (ranNum == lotteryNumbers[5])){ //check if the ranNum is unique - at first not equal to 0
				i=i-1; //go 1 backwards if it is not
			} else {
			lotteryNumbers[i] = ranNum; //if it is unique, stored in the lottery num array
			} 
		} // array lottery game is created with 6 random numbers
	}


	public int[] getLotteryNumbers(){
		return lotteryNumbers;
	}


	public void setUserNumbers(int[][] userNumbers){
		this.userNumbers = userNumbers; //takes the 2d array from the app class and store it in the instantiable class
	}


	public void compareNumbers(){
		numsAndLinesWon = new int[userNumbers.length]; // initialise a new array for num per line

		for(int i=0; i<userNumbers.length; i++){ // iterate at row 0 of user array

			numsWon = 0; //reinitialise the correct number to 0;

			for(int j=0; j<userNumbers[i].length; j++){ // iterate at column 0 of user array

				for(int k=0; k< lotteryNumbers.length; k++){ // iterate at column 0 of lottery array

					if(userNumbers[i][j]==lotteryNumbers[k]){ //check if num are same

						numsWon = numsWon+1; //add 1 to var if num are same
					}
				}

				numsAndLinesWon[i] = numsWon; // store num won per line, with line being the index
			}
		}
	}


	public int[] getNumsAndLinesWon(){ //get result of the numbers comparison
		return numsAndLinesWon;
	}


	public void calculateSumWinnings(){
		sumWinnings = 0; //initialize the sum of the winnings at 0
		for(int i=0; i<numsAndLinesWon.length; i++){
			if(numsAndLinesWon[i] == 6){
				lineWinnings = SIX_NUM;
			} else if(numsAndLinesWon[i] == 5){
				lineWinnings = FIVE_NUM;
			} else if(numsAndLinesWon[i] == 4){
				lineWinnings = FOUR_NUM;
			} else if(numsAndLinesWon[i] == 3){
				lineWinnings = THREE_NUM;
			}
			sumWinnings = sumWinnings + lineWinnings; //add all winnings together
		}

		if(sumWinnings >= LOTTERY_THREE_TIMES){ //however, user can only win the lottery once, so this will substract the winnings at only one time lottery win (can only win 1 million)
			sumWinnings = sumWinnings - (2 * SIX_NUM);
		} else if(sumWinnings >= LOTTERY_TWICE){
			sumWinnings = sumWinnings - SIX_NUM;
		}
	}


	public int getSumWinnings(){ //get the sum winnings
		return sumWinnings;
	}


	public String getLotteryWinning(){ //get the winning message if lottery is won
		for(int i=0; i< numsAndLinesWon.length; i++){
			if(numsAndLinesWon[i] == 6){
				winLottery = LOTTERY_WON;
			}
		}
		return winLottery;
	}


	//getter to get the results

}