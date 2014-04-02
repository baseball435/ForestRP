package com.common.player;

public class Skunk extends Player {
	private static final long serialVersionUID = 1L;

	public Skunk() {
		Craft = 7;
		Prowess = 3;
		Awareness = 1;
		setIcon("/critters/skunk.PNG");
		race = RACE.Skunk;
		helpInfo = "Skunks make great crafters and have a decent ability for weapon wielding.";
	}
}
