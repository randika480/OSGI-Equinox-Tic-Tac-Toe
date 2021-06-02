package com.grid_gameboard_publisher;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBoardImpl implements IGameBoard {

	//game board size variable declaration
	static int scale = 0;
	//default game board design pattern
	static String designChar = "#";
	//declare 2D array to initialize Game Board
	static String[][] boardArray;
	//declare array list to store empty cell locations (in i,j format)
	static ArrayList<String> emptySlots = new ArrayList<>();

	@Override
	public void gameBoardCreate(int s) {

		//game scale depends on user input value
		switch (s) {
		case 1:
			scale = 7;
			break;
		case 2:
			scale = 9;
			break;
		case 3:
			scale = 11;
			break;
		default:
			scale = 7;
			break;
		}

		boardArray = new String[scale][scale];

		// add dummy data to fill Array List index 0
		emptySlots.clear();
		emptySlots.add("index 0 filled");

		// initialize values into 2d array
		for (int i = 0; i < scale; i++) {
			for (int j = 0; j < scale; j++) {
				boardArray[i][j] = "[" + i + "," + j + "]";
			}

		}

		// print 2d array(index no)
		// only for development purpose
		for (int i = 0; i < scale; i++) {
			for (int j = 0; j < scale; j++) {
				System.out.print(boardArray[i][j]);
			}
			System.out.println();
		}

		System.out.println("--------------------------------------------------");
		changeUIApproval();

		// overwrite empty slots
		for (int i = 0; i < scale; i++) {
			for (int j = 0; j < scale; j++) {

				// select rows with empty slots
				if (i == 1 || i % 2 != 0) {
					// empty slots
					if (j == 1 || j % 2 != 0) {
						boardArray[i][j] = " ";
						emptySlots.add(i + ":" + j);
					}
					// other slots
					else {
						boardArray[i][j] = designChar;
					}
				}
				// rows filled with only by pattern
				else {
					boardArray[i][j] = designChar;
				}
			}

		}

		printBoard();

	}

	//print board method implementation
	@Override
	public void printBoard() {

		// print 2d array
		for (int i = 0; i < scale; i++) {
			System.out.print("\t");
			for (int j = 0; j < scale; j++) {
				System.out.print(boardArray[i][j]);
			}
			System.out.println();
		}

	}

	//game board design handling method
	public void changeUIApproval() {

		int UIchoice = 0;
		Scanner s = new Scanner(System.in);

		System.out.println("Do you wish to load enhanced Board UI? [1/2/3/4]:");
		System.out.println("  1.UI_v1.02 [#]");
		System.out.println("  2.UI_v1.03 [*]");
		System.out.println("  3.UI_v1.04 [=]");
		System.out.println("  4.No (keep default UI [#])");

		System.out.println("----------------------------------");
		UIchoice = s.nextInt();
		System.out.println("----------------------------------");

		//conditional check for game board design pattern
		switch (UIchoice) {
		case 1:
			designChar = "#";
			break;
		case 2:
			designChar = "*";
			break;
		case 3:
			designChar = "=";
			break;
		default:
			System.out.println("Directing to Default UI..");
			break;
		}

	}

	//get method to access getEmptySlots array list
	public static ArrayList<String> getEmptySlots() {
		return emptySlots;
	}

	//location marker method
	//use i,j values to locate cell and mark X/O symbol according to user
	public void markLocation(int i, int j, String player) {
		String symbol;
		if (player == "p1") {
			symbol = "X";
		} else {
			symbol = "O";
		}
		boardArray[i][j] = symbol;
	}

}
