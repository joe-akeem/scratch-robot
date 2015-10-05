package de.joeakeem.scratch.robot;

import static com.pi4j.io.gpio.RaspiPin.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import de.joeakeem.m28BYJ48.StepperMotor28BYJ48;
import de.joeakeem.m28BYJ48.StepperMotor28BYJ48.SteppingMethod;
import de.joeakeem.scratch.rsp.Scratch14Instance;

@SpringBootApplication
public class Application {
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		Robot robot = ctx.getBean(Robot.class);
		robot.start();
	}
	
	@Bean
	public Scratch14Instance getScrachInstance() {
		Scratch14Instance scratch = new Scratch14Instance();
		scratch.setHost(env.getProperty("scratch.host", "localhost"));
		scratch.setPort(Integer.parseInt(env.getProperty("scratch.port", "42001")));
		return scratch;
	}

	@Bean(name="leftMotor")
	public StepperMotor28BYJ48 getLeftMotor() {
		return new StepperMotor28BYJ48(GPIO_07, GPIO_00, GPIO_02, GPIO_03, 3, SteppingMethod.FULL_STEP);
	}
	
	@Bean(name="rightMotor")
	public StepperMotor28BYJ48 getRightMotor() {
		return new StepperMotor28BYJ48(GPIO_01, GPIO_04, GPIO_05, GPIO_06, 3, SteppingMethod.FULL_STEP);
	}
}
