package de.joeakeem.scratch.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.joeakeem.scratch.rsp.RemoteSensor;

public class SimulationMotorSensor extends RemoteSensor {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimulationMotorSensor.class);
	
	private String name;

	@Override
	protected void broadcast(String message) {
		LOG.info("{} motor: received broadcat '{}'", name, message);
	}

	@Override
	protected void sensorUpdate(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sensorUpdate(String name, double value) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sensorUpdate(String name, boolean value) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void otherMessage(String message) {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
