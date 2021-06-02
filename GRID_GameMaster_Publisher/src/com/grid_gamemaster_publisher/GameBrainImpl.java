package com.grid_gamemaster_publisher;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import com.grid_gameboard_publisher.GameBoardImpl;
import com.grid_gamerules_publisher.GameRulesImpl;

public class GameBrainImpl implements IGameBrain {

	static Scanner s1 = new Scanner(System.in);
	// Declare array list to store filled cells while playing
	static ArrayList<Integer> filledSlots = new ArrayList<>();
	// GameBoard_Publisher object
	GameBoardImpl obj = new GameBoardImpl();
	// GameRules_Publisher object
	GameRulesImpl obj2 = new GameRulesImpl();

	// Initiates game accordingly the game type
	public void initiateGame(int gameType) {

		switch (gameType) {
		case 1:
			gameVsPc();
			break;
		case 2:
			gameVsUser();
			break;
		default:
			System.out.println("Incorrect Game Type!!");
			System.out.println("Proceding with default settings - User vs PC :");
			gameVsPc();
			break;

		}

	}

	@Override
	public int setMarker(int cellLocation, String player) {

		// Getting actual index of entered cell index
		String[] marker = obj.getEmptySlots().get(cellLocation).split(":", 2);

		int i = Integer.parseInt(marker[0]);
		int j = Integer.parseInt(marker[1]);

		// pass the index value to the markLocation method in gameBoardImpl
		obj.markLocation(i, j, player);
		
		// check the winner
		// pass cell index and player to the winnerSelector method in GamerulesImpl
		int result = obj2.winnerSelector(cellLocation, player);
		obj.printBoard();
		return result;
	}

	//calls when the user selects Game Type 1
	public void gameVsPc() {

		int checkResult = -1;

		int markerCell = 0;
		filledSlots.clear();
		while (true) {

			System.out.println("----------------------------------");
			System.out.print("Player-01 turn:");

			markerCell = s1.nextInt();

			//avoid replacing filled cells
			while (filledSlots.contains(markerCell)) {
				System.out.println("place Taken!!");
				System.out.print("Please Enter New Value:");
				markerCell = s1.nextInt();
			}

			//Checks whether the player meets the winning conditions
			checkResult = setMarker(markerCell, "p1");
			if (checkResult > 0) {
				break;
			}

			filledSlots.add(markerCell);
			
			//Checks if all cells are filled
			checkResult = gameBreaker();
			if (checkResult > -1) {
				break;
			}

			System.out.println("----------------------------------");

			System.out.println("PC turn:");

			int cpuPos = randomPosGenerator();

			while (filledSlots.contains(cpuPos)) {
				cpuPos = randomPosGenerator();
			}

			checkResult = setMarker(cpuPos, "pc");

			if (checkResult > 0) {
				break;
			}

			filledSlots.add(cpuPos);
			checkResult = gameBreaker();
			if (checkResult > -1) {
				break;
			}

		}
	}

	//calls when the user selects Game Type 1
	public void gameVsUser() {

		int checkResult = -1;
		int markerCell = 0;
		filledSlots.clear();

		while (true) {

			System.out.println("----------------------------------");
			System.out.print("Player-01 turn:");

			markerCell = s1.nextInt();

			while (filledSlots.contains(markerCell)) {
				System.out.println("place Taken!!");
				System.out.print("Please Enter New Value:");
				markerCell = s1.nextInt();
			}

			checkResult = setMarker(markerCell, "p1");
			if (checkResult > 0) {
				break;
			}

			filledSlots.add(markerCell);

			checkResult = gameBreaker();
			if (checkResult > -1) {
				break;
			}

			System.out.println("----------------------------------");

			System.out.print("Player-02 turn:");

			markerCell = s1.nextInt();

			while (filledSlots.contains(markerCell)) {
				System.out.println("place Taken!!");
				System.out.print("Please Enter New Value:");
				markerCell = s1.nextInt();
			}

			checkResult = setMarker(markerCell, "p2");
			if (checkResult > 0) {
				break;
			}

			filledSlots.add(markerCell);

			checkResult = gameBreaker();
			if (checkResult > -1) {
				break;
			}

		}

	}

	public int gameBreaker() {

		int result = -1;

		//Checks if all cells are filled
		if (filledSlots.size() == obj.getEmptySlots().size() - 1) {

			System.out.println("Game Draw!!");
			result = 0;
		}

		return result;

	}

	//generates random number between 1 and selected board scale
	public int randomPosGenerator() {

		Random rand = new Random();
		int emptySlotsSize = obj.getEmptySlots().size();
		int randNo = rand.nextInt(emptySlotsSize - 1);
		if (randNo == 0) {
			randNo = rand.nextInt(4) + 1;
		}

		return randNo;

	}

}
