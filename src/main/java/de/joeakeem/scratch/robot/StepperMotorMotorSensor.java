package de.joeakeem.scratch.robot;

import java.util.List;

import de.joeakeem.m28BYJ48.StepperMotor28BYJ48;
import de.joeakeem.scratch.rsp.RemoteSensor;

public class StepperMotorMotorSensor extends RemoteSensor {
	
	private String name;
	private List<String> commandsToReactOn;
	
	private StepperMotor28BYJ48 motor;
	
	@Override
	public void broadcast(String message) {
		if (commandsToReactOn.contains(message)) {
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