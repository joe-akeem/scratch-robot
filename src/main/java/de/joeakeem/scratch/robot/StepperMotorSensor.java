package de.joeakeem.scratch.robot;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.joeakeem.m28BYJ48.StepperMotor28BYJ48;
import de.joeakeem.scratch.rsp.RemoteSensor;

public class StepperMotorSensor extends RemoteSensor {
	
	private static final Logger LOG = LoggerFactory.getLogger(StepperMotorSensor.class);
	
	private String name;
	private List<String> commandsToReactOn;
	
	private StepperMotor28BYJ48 motor;
	
	@PostConstruct
	public void demoMotor() {
		motor.performDemo();
	}
	
	@Override
	public void broadcast(String message) {
		if (commandsToReactOn.contains(message)) {
			LOG.info("{} motor moving", name);
			motor.step(1);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCommandsToReactOn() {
		return commandsToReactOn;
	}

	public void setCommandsToReactOn(List<String> commandsToReactOn) {
		this.commandsToReactOn = commandsToReactOn;
	}

	public StepperMotor28BYJ48 getMotor() {
		return motor;
	}

	public void setMotor(StepperMotor28BYJ48 motor) {
		this.motor = motor;
	}
}