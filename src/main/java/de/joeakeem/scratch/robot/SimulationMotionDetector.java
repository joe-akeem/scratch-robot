package de.joeakeem.scratch.robot;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.joeakeem.scratch.rsp.Scratch14Instance;

public class SimulationMotionDetector extends Scratch14Instance implements MotionDetector, Runnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimulationMotionDetector.class);
	
	private static final String MOTION_DETECTION_BROADCASAT_MESSAGE = "alarm";
	
	@Override
	public void handleMotionDetection() {
		try {
			LOG.info("Sending motion detection event...");
			broadcast(MOTION_DETECTION_BROADCASAT_MESSAGE);
		} catch (IOException e) {
			LOG.error("Failed to broadcast to Scratch", e);
		}
	}

	@Override
	public void run() {
		while (true) {
			handleMotionDetection();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void connect() throws UnknownHostException, IOException {
		super.connect();
		new Thread(this).start();
	}
}
