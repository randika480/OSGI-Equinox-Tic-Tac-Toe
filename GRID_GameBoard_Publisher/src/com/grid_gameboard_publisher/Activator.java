package com.grid_gameboard_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceReg;

	public void start(BundleContext context) throws Exception {
		System.out.println("### Game Board Publisher starts..");
		IGameBoard obj = new GameBoardImpl();
		serviceReg = context.registerService(IGameBoard.class.getName(), obj, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("### Game Board Publisher stops..");
		serviceReg.unregister();

	}

}
