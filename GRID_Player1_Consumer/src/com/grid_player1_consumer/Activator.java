package com.grid_player1_consumer;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.grid_gameboard_publisher.IGameBoard;
import com.grid_gamemaster_publisher.IGameBrain;

public class Activator implements BundleActivator {

	ServiceReference serviceRef1, serviceRef2;

	public void start(BundleContext context) throws Exception {

		int scale = 0;
		Scanner s1 = new Scanner(System.in);
		
		//when the GRID_Player1_Consumer bundle starts below println line will be display as the output
		System.out.println("### Game Player-01 start..");
		System.out.println();
		System.out.println("~~Tic-Tac-Toe Game By team GRID~~");
		System.out.println();

		//registering and referencing to get services from the GRID_GameBoard_Publisher bundle and GRID_GameMaster_Publisher bundle
		serviceRef1 = context.getServiceReference(IGameBoard.class.getName());
		IGameBoard gameBoardObj = (IGameBoard) context.getService(serviceRef1);
		serviceRef2 = context.getServiceReference(IGameBrain.class.getName());
		IGameBrain gameMasterObj = (IGameBrain) context.getService(serviceRef2);

		//Asking user to select the board scale
		System.out.println("Please select game board scale[1/2/3]:");
		System.out.println("  1.Small");
		System.out.println("  2.Medium");
		System.out.println("  3.Large");
		System.out.println(" (If user inputs invalid command the game board will create under the default value of small size)");
		System.out.println("----------------------------------");

		//getting the user input for the board scale and save it in scale variable
		scale = s1.nextInt();
		System.out.println("--------------------------------------------------");

		//calling the method of gameBoardCreate in GRID_GameBoard_Publisher bundle
		gameBoardObj.gameBoardCreate(scale);
		
		System.out.println("----------------------------------");
		
		//Asking user to select the gameType
		System.out.println("Please Select Game type");
		System.out.println("  1.vs pc");
		System.out.println("  2.vs user");
		System.out.println("----------------------------------");
		int gameType = s1.nextInt();
		
		//calling the method of initiateGame in GRID_GameMaster_Publisher bundle
		gameMasterObj.initiateGame(gameType);
		

		System.out.println("----------------------------------");

	}

	public void stop(BundleContext context) throws Exception {
		//when the GRID_Player1_Consumer bundle stops below println line will be display as the output
		System.out.println("### Game Player stop..");
		
		//unregistering from  getting services from the GRID_GameBoard_Publisher bundle and GRID_GameMaster_Publisher bundle
		context.ungetService(serviceRef1);
		context.ungetService(serviceRef2);
	}

}
