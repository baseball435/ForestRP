package com.common.player;

public class Squirrel extends Player {

	private static final long serialVersionUID = 1L;

	public Squirrel() {
		Nimbleness = 6;
		Luck = 2;
		Craft = 5;
		Fortitude = -2;
		race = RACE.Squirrel;
		setIcon("/critters/squirrel.PNG");
		helpInfo = "Squirrels have a great ability to climb and craft and make decent scroungers.";
	}
}
