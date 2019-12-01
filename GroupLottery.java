import java.util.*;

public class GroupLottery{

	private int[] lottery;

	private int[][] userNumbers;

	private int[] matches;
	private double winnings;


	private final double MATCH3 = 100;
	private final double MATCH4 = 300;
	private final double MATCH5 = 1500;
	private final String MATCH6 = "You won the lottery! Stop now.";



	public GroupLottery(){
		lottery = new int[6];
		for(int i=0; i<=6; i++){
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

		for(int i=0; i<=userNumbers.length; i++){
			int match = 0;
			for(int j= 0; j<=lottery.length; j++){
				if(userNumbers[i][j] == lottery[j]){
					match++;
					}
				}
			matches[i] = match;
			}
		}


	public void calculateWinnings(){
	}



}