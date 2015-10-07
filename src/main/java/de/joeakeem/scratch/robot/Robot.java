package de.joeakeem.scratch.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Robot {
	
	private static final Logger LOG = LoggerFactory.getLogger(Robot.class);
	
	@Autowired @Qualifier("leftMotorSensor")
	private MotorSensor leftMotorSensor;
	
	@Autowired @Qualifier("rightMotorSensor")
	private MotorSensor rightMotorSensor;

	public void start() {
		LOG.info("Starting Scratch Robot...");
		Thread leftMotorSeonsorThread = leftMotorSensor.connect();
		LOG.info("Left motor sensor connected to Scratch on host {} and port {}", leftMotorSensor.getScratchHost(), leftMotorSensor.getScratchPort());
		Thread rightMotorSeonsorThread = rightMotorSensor.connect();
		LOG.info("Right motor sensor connected to Scratch on host {} and port {}", rightMotorSensor.getScratchHost(), rightMotorSensor.getScratchPort());
		try {
			leftMotorSeonsorThread.join();
			rightMotorSeonsorThread.join();
		} catch (InterruptedException e) {
		}
	}
}
