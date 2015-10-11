package de.joeakeem.scratch.robot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("simulation")
public class SimulationConfiguration {
	
	private static final String MOVE_FORWARD_BROADCAST_MESSAGE = "forward";
	private static final String TURN_LEFT_BROADCAST_MESSAGE = "left";
	private static final String TURN_RIGHT_BROADCAST_MESSAGE = "right";
	
	@Autowired
	private Environment env;

	@Bean(name="leftMotorSensor")
	public SimulationMotorSensor getLeftMotorSensor() {
		SimulationMotorSensor motorSensor = new SimulationMotorSensor();
		motorSensor.setName("Left");
		motorSensor.setScratchHost(env.getProperty("scratch.host", "localhost"));
		motorSensor.setScratchPort(Integer.parseInt(env.getProperty("scratch.port", "42001")));
		ArrayList<String> commandsToReactOn = new ArrayList<String>(2);
		commandsToReactOn.add(MOVE_FORWARD_BROADCAST_MESSAGE);
		commandsToReactOn.add(TURN_RIGHT_BROADCAST_MESSAGE);
		return motorSensor;
	}
	
	@Bean(name="rightMotorSensor")
	public SimulationMotorSensor getRightMotorSensor() {
		SimulationMotorSensor motorSensor = new SimulationMotorSensor();
		motorSensor.setName("Right");
		motorSensor.setScratchHost(env.getProperty("scratch.host", "localhost"));
		motorSensor.setScratchPort(Integer.parseInt(env.getProperty("scratch.port", "42001")));
		ArrayList<String> commandsToReactOn = new ArrayList<String>(2);
		commandsToReactOn.add(MOVE_FORWARD_BROADCAST_MESSAGE);
		commandsToReactOn.add(TURN_LEFT_BROADCAST_MESSAGE);
		return motorSensor;
	}
	
	@Bean
	public MotionDetector getMotionDetector() {
		return new SimulationMotionDetector();
	}
}
