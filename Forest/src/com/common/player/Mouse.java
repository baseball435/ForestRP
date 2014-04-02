package com.common.player;

public class Mouse extends Player {

	private static final long serialVersionUID = 1L;

	public Mouse() {
		Intelligence = 8;
		Awareness = 3;
		Nimbleness = 1;
		Fortitude = -1;
		setIcon("/critters/mouse.PNG");
		race = RACE.Mouse;
		helpInfo = "Mice are extremely intelligent and consequently have a great ability to use magic.";
	}

}
