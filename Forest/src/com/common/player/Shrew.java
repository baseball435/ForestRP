package com.common.player;

public class Shrew extends Player {

	private static final long serialVersionUID = 1L;

	public Shrew() {
		Luck = 6;
		Craft = 7;
		Fortitude = -2;
		race = RACE.Shrew;
		setIcon("/critters/shrew.PNG");
		helpInfo = "Shrews are excellent scroungers and crafters.";
	}

}
