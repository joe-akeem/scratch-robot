package de.joeakeem.scratch.robot;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import de.joeakeem.scratch.rsp.RemoteSensor;

@Component
public class Robot {
	
	private static final Logger LOG = LoggerFactory.getLogger(Robot.class);
	
	@Autowired @Qualifier("leftMotorSensor")
	private RemoteSensor leftMotorSensor;
	
	@Autowired @Qualifier("rightMotorSensor")
	private RemoteSensor rightMotorSensor;
	
	@Autowired
	private MotionDetector motionDetector;

	public void start() {
		try {
			LOG.info("Starting Scratch Robot...");
			Thread leftMotorSeonsorThread = leftMotorSensor.connect();
			LOG.info("Left motor sensor connected to Scratch on host {} and port {}", leftMotorSensor.getScratchHost(), leftMotorSensor.getScratchPort());
			Thread rightMotorSeonsorThread = rightMotorSensor.connect();
			LOG.info("Right motor sensor connected to Scratch on host {} and port {}", rightMotorSensor.getScratchHost(), rightMotorSensor.getScratchPort());
			motionDetector.connect();
			leftMotorSeonsorThread.join();
			rightMotorSeonsorThread.join();
		} catch (InterruptedException e) {
		} catch (UnknownHostException e) {
			LOG.error("Failed to connect to Scratch", e);
		} catch (IOException e) {
			LOG.error("Failed to connect to Scratch", e);
		}
	}
}
