import java.util.*;

public class Lottery{

	//array to store hidden random numbers
	private List<Integer> hiddenNumbers = new ArrayList<Integer>();


	private int[] userNumbers = new int[6]; 	//user input

	//no. of matches in a line
	private int match;

	//list for matches
	private List<Integer> matchList = new ArrayList<Integer>();

	private int winnings;



	private final int MATCH3 = 125;
	private final int MATCH4 = 300;
	private final int MATCH5 = 1500;
	private final String MATCH6 = "Victory!";






	public Lottery(){
		Random random = new Random();
		for(int i=0; i<7; i++){
			int x = (random.nextInt(40)) +1;
			hiddenNumbers.add(i, x);
			}
		}

	public void setUserNumbers(int[] userNumbers){
		this.userNumbers = userNumbers;
		}

	public void addToMatchList(int x){
			matchList.add(x);
		}

	public List getMatchList(){
		return matchList;
		}




	public void compareNumbers(){
		match =0;
			for(int count = 0; count<userNumbers.length; count++){

				for(int morecount = 0; morecount<hiddenNumbers.size(); morecount++){

					if(userNumbers[count] == hiddenNumbers.get(morecount)){
						match++;
						}
					}
				}
		}

	public int getMatch(){
		return match;
		}

	public List getHiddenNumbers(){
		return hiddenNumbers;
	}


	public void calculateWinnings(int x){
		if (x<3){
			winnings = 0;
			}
		else if(x==3){
			winnings = MATCH3;
			}
		else if(x==4){
			winnings=MATCH4;
			}
		else if(x==5){
			winnings=MATCH5;
			}
		}

	public int getWinnings(){
		return winnings;
		}









}