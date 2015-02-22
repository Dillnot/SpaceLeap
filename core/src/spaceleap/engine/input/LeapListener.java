package spaceleap.engine.input;

/******************************************************************************\
 * Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
 * Leap Motion proprietary and confidential. Not for distribution.              *
 * Use subject to the terms of the Leap Motion SDK Agreement available at       *
 * https://developer.leapmotion.com/sdk_agreement, or another agreement         *
 * between Leap Motion and you, your company or other organization.             *
 \******************************************************************************/

import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

public class LeapListener extends Listener {
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	public void onConnect(Controller controller) {
		System.out.println("Leap conntected");
	}

	public void onDisconnect(Controller controller) {
		// Note: not dispatched when running in a debugger.
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller) {
		System.out.println("Exited");
	}

	public short direction = 0;
	public boolean fire = false;

	public void onFrame(Controller controller) {
		// Get the most recent frame and report some basic information
		Frame frame = controller.frame();

		// Checking for movement in SpaceLeap
		if (frame.hands().count() == 0) {
			direction = 0;
		} else {
			for (Hand hand : frame.hands()) {
				float pos = hand.palmPosition().getX();

				if (pos < 0) {
					direction = -1;
				} else if (pos > 0) {
					direction = 1;
				} else {
					direction = 0;
				}

				// We want to check the index finger!
				// Checking for fist so you can fire
				int id = (hand.id() * 10) + 1;

				if (!hand.finger(id).isExtended()) {
					fire = true;
				} else {
					fire = false;
				}
			}

		}
	}
}
