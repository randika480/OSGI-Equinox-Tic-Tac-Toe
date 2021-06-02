package com.grid_gamemaster_publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	ServiceRegistration serviceReg;

	public void start(BundleContext context) throws Exception {
		System.out.println("### Game Master Publisher starts..");
		IGameBrain obj = new GameBrainImpl();
		serviceReg = context.registerService(IGameBrain.class.getName(), obj, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("### Game Master Publisher stops..");
		serviceReg.unregister();
	}

}
