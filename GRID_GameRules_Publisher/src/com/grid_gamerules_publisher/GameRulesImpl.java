package com.grid_gamerules_publisher;

import java.util.*;


import com.grid_gameboard_publisher.GameBoardImpl;

public class GameRulesImpl implements IGameRules{
	
	GameBoardImpl obj = new GameBoardImpl();
	int counter = 0;
	
	//declare array lists to collect player positions
	ArrayList<Integer> player1Position = new ArrayList<Integer>();
	ArrayList<Integer> player2Position = new ArrayList<Integer>();
	

	//WinnerSelector method
	//Check player positions and add them
	@Override
	public int winnerSelector(int cell,String player) {
		
		
		int boardSize = obj.getEmptySlots().size();	
		if(player == "p1") {
			player1Position.add(cell);
		}
		else {
			player2Position.add(cell);
		}
		
		int result = manageWinning(boardSize, player);

		return result;

		
	}
	

	//manageWinning method
	//check the board using the size and define the winner.
	public int manageWinning(int boardSize, String player) {
		
	//declaring winning conditions using array list
		List<List> winning = new ArrayList<>();
		
		//for small board
		if(boardSize == 10) {
			
			List srow1 = Arrays.asList(1, 2, 3);
			List srow2 = Arrays.asList(4, 5, 6);
			List srow3 = Arrays.asList(7, 8, 9);
			List scol1 = Arrays.asList(1, 4, 7);
			List scol2 = Arrays.asList(2, 5, 8);
			List scol3 = Arrays.asList(3, 6, 9);
			List scross1 = Arrays.asList(1, 5, 9);
			List scross2 = Arrays.asList(7, 5, 3);
			
			
			winning.add(srow1);
			winning.add(srow2);
			winning.add(srow3);
			winning.add(scol1);
			winning.add(scol2);
			winning.add(scol3);
			winning.add(scross1);
			winning.add(scross2); 
		}
		//for medium size board
		else if(boardSize == 17) {
			List srow1 = Arrays.asList(1, 2, 3, 4);
			List srow2 = Arrays.asList(5, 6, 7, 8);
			List srow3 = Arrays.asList(9, 10, 11, 12);
			List srow4 = Arrays.asList(13, 14, 15, 16);
			
			List scol1 = Arrays.asList(1, 5, 9, 13);
			List scol2 = Arrays.asList(2, 6, 10, 14);
			List scol3 = Arrays.asList(3, 7, 11, 15);
			List scol4 = Arrays.asList(4, 8, 12, 16);
			
			
			List scross1 = Arrays.asList(1, 6, 11, 16);
			List scross2 = Arrays.asList(4, 7, 10, 13);
			
			
			winning.add(srow1);
			winning.add(srow2);
			winning.add(srow3);
			winning.add(srow4);
			winning.add(scol1);
			winning.add(scol2);
			winning.add(scol3);
			winning.add(scol4);
			winning.add(scross1);
			winning.add(scross2);
			
		}
		
		//for large size board
		else if(boardSize == 26) {
			List srow1 = Arrays.asList(1, 2, 3, 4, 5);
			List srow2 = Arrays.asList(6, 7, 8, 9, 10);
			List srow3 = Arrays.asList(11, 12, 13, 14, 15);
			List srow4 = Arrays.asList(16, 17, 18, 19, 20);
			List srow5 = Arrays.asList(21, 22, 23, 24, 25);
			
			List scol1 = Arrays.asList(1, 6, 11, 16, 21);
			List scol2 = Arrays.asList(2, 7, 12, 17, 22);
			List scol3 = Arrays.asList(3, 8, 13, 18, 23);
			List scol4 = Arrays.asList(4, 9, 14, 19, 24);
			List scol5 = Arrays.asList(5, 10, 15, 20, 25);
			
			
			List scross1 = Arrays.asList(1, 7, 13, 19, 25);
			List scross2 = Arrays.asList(5, 9, 13, 17, 21);
		
			winning.add(srow1);
			winning.add(srow2);
			winning.add(srow3);
			winning.add(srow4);
			winning.add(srow5);
			winning.add(scol1);
			winning.add(scol2);
			winning.add(scol3);
			winning.add(scol4);
			winning.add(scol5);
			winning.add(scross1);
			winning.add(scross2);
		}
		
		//check winner using if else condions and print it
		for(List l : winning) {
			if (player1Position.containsAll(l)){
				System.out.println("----------------------------------");
				System.out.println("----------------------------------");
				System.out.println("Congratulations Player 1 won!");
				System.out.println("----------------------------------");
				System.out.println("----------------------------------");
				return 1;
			}else if(player2Position.containsAll(l)) {
				if(player == "p2") {
					System.out.println("----------------------------------");
					System.out.println("----------------------------------");
					System.out.println("Congratulations Player 2 won");
					System.out.println("----------------------------------");
					System.out.println("----------------------------------");
				}
				else {
					System.out.println("----------------------------------");
					System.out.println("----------------------------------");
					System.out.println("CPU wins! Sorry :(");
					System.out.println("----------------------------------");
					System.out.println("----------------------------------");
					
				}
	
				return 2;
				
			}
			
		}
		return 0;
	}
	
	
	
	
	

}
