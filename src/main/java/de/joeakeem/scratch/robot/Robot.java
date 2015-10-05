package de.joeakeem.scratch.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.joeakeem.m28BYJ48.StepperMotor28BYJ48;
import de.joeakeem.scratch.rsp.RemoteSensor;
import de.joeakeem.scratch.rsp.Scratch14Instance;

@Component
public class Robot implements RemoteSensor {
	
	private static final Logger LOG = LoggerFactory.getLogger(Robot.class);
	
	private static final String MOVE_FORWARD_BROADCAST_MESSAGE = "forward";
	private static final String TURN_LEFT_BROADCAST_MESSAGE = "left";
	private static final String TURN_RIGHT_BROADCAST_MESSAGE = "right";
	
	@Autowired
	private Scratch14Instance scratch;
	
	@Autowired @Qualifier("leftMotor")
	private StepperMotor28BYJ48 leftMotor;
	
	@Autowired @Qualifier("rightMotor")
	private StepperMotor28BYJ48 rightMotor;

	public void start() {
		LOG.info("Starting Scratch Robot...");
		scratch.registerRemoteSensor(this);
		Thread sensorThread = scratch.connect();
		LOG.info("Connected to Scratch on host {} and port {}", scratch.getHost(), scratch.getPort());
		try {
			sensorThread.join();
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void broadcast(String message) {
		
		if (MOVE_FORWARD_BROADCAST_MESSAGE.equals(message)) {
			LOG.info("Moving straight");
			leftMotor.step(1);
			rightMotor.step(1);
		} else if (TURN_LEFT_BROADCAST_MESSAGE.equals(message)) {
			LOG.info("Turning left");
			rightMotor.step(1);
		} else if (TURN_RIGHT_BROADCAST_MESSAGE.equals(message)) {
			LOG.info("Turning right");
			leftMotor.step(1);
		}
	}

	@Override
	public void sensorUpdate(String name, String value) {
	}

	@Override
	public void sensorUpdate(String name, double value) {
	}

	@Override
	public void sensorUpdate(String name, boolean value) {
	}

	@Override
	public void otherMessage(String message) {
	}
}
