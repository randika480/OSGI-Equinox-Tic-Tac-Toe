package com.grid_gamerules_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceReg;

	public void start(BundleContext context) throws Exception {
		System.out.println("### Game Rules Publisher starts..");
		IGameRules obj = new GameRulesImpl();
		serviceReg = context.registerService(IGameRules.class.getName(), obj, null);
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("### Game Rules Publisher stops..");
		serviceReg.unregister();
	}

}
