package de.joeakeem.scratch.robot;

import java.io.IOException;
import java.net.UnknownHostException;

public interface MotionDetector {
	
	public void connect() throws UnknownHostException, IOException;
	
	public void handleMotionDetection();

}
