package de.joeakeem.scratch.robot;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import de.joeakeem.scratch.rsp.Scratch14Instance;

public class PirMotionDetector extends Scratch14Instance implements MotionDetector, GpioPinListenerDigital {
	
	private static final Logger LOG = LoggerFactory.getLogger(PirMotionDetector.class);
	
	private static final String MOTION_DETECTION_BROADCASAT_MESSAGE = "alarm";
	
	private Pin sensorPin;
	
	PirMotionDetector(Pin sensorPin) {
		this.sensorPin = sensorPin;
	}
	
	@PostConstruct
	public void registerListener() {
		GpioController gpio = GpioFactory.getInstance();
		GpioPinDigitalInput motionSensor = gpio.provisionDigitalInputPin(sensorPin, PinPullResistance.PULL_UP);
		motionSensor.addListener(this);
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(
			GpioPinDigitalStateChangeEvent event)
	{
		handleMotionDetection();
	}

	@Override
	public void handleMotionDetection() {
		try {
			broadcast(MOTION_DETECTION_BROADCASAT_MESSAGE);
		} catch (IOException e) {
			LOG.error("Failed to broadcast to Scratch", e);
		}
	}

}
