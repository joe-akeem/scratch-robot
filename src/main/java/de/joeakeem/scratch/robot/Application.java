package de.joeakeem.scratch.robot;

import static com.pi4j.io.gpio.RaspiPin.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import de.joeakeem.m28BYJ48.StepperMotor28BYJ48;
import de.joeakeem.m28BYJ48.StepperMotor28BYJ48.SteppingMethod;
import de.joeakeem.scratch.rsp.RemoteSensor;

@SpringBootApplication
public class Application {
	
	private static final String MOVE_FORWARD_BROADCAST_MESSAGE = "forward";
	private static final String TURN_LEFT_BROADCAST_MESSAGE = "left";
	private static final String TURN_RIGHT_BROADCAST_MESSAGE = "right";
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		Robot robot = ctx.getBean(Robot.class);
		robot.start();
	}

	@Bean(name="leftMotor")
	public StepperMotor28BYJ48 getLeftMotor() {
		return new StepperMotor28BYJ48(GPIO_07, GPIO_00, GPIO_02, GPIO_03, 3, SteppingMethod.FULL_STEP);
	}
	
	@Bean(name="rightMotor")
	public StepperMotor28BYJ48 getRightMotor() {
		return new StepperMotor28BYJ48(GPIO_01, GPIO_04, GPIO_05, GPIO_06, 3, SteppingMethod.FULL_STEP);
	}
	
	@Bean(name="leftMotorSensor")
	public MotorSensor getLeftMotorSensor(@Qualifier("leftMotor") StepperMotor28BYJ48 leftMotor) {
		MotorSensor motorSensor = new MotorSensor();
		motorSensor.setName("Left");
		motorSensor.setScratchHost(env.getProperty("scratch.host", "localhost"));
		motorSensor.setScratchPort(Integer.parseInt(env.getProperty("scratch.port", "42001")));
		motorSensor.setMotor(leftMotor);
		ArrayList<String> commandsToReactOn = new ArrayList<String>(2);
		commandsToReactOn.add(MOVE_FORWARD_BROADCAST_MESSAGE);
		commandsToReactOn.add(TURN_RIGHT_BROADCAST_MESSAGE);
		motorSensor.setCommandsToReactOn(commandsToReactOn);
		return motorSensor;
	}
	
	@Bean(name="rightMotorSensor")
	public MotorSensor getRightMotorSensor(@Qualifier("rightMotor") StepperMotor28BYJ48 rightMotor) {
		MotorSensor motorSensor = new MotorSensor();
		motorSensor.setName("Right");
		motorSensor.setScratchHost(env.getProperty("scratch.host", "localhost"));
		motorSensor.setScratchPort(Integer.parseInt(env.getProperty("scratch.port", "42001")));
		motorSensor.setMotor(rightMotor);
		ArrayList<String> commandsToReactOn = new ArrayList<String>(2);
		commandsToReactOn.add(MOVE_FORWARD_BROADCAST_MESSAGE);
		commandsToReactOn.add(TURN_LEFT_BROADCAST_MESSAGE);
		motorSensor.setCommandsToReactOn(commandsToReactOn);
		return motorSensor;
	}
}
