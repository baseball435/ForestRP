package com.common.player;


public class Weasel extends Player {

	private static final long serialVersionUID = 1L;

	public Weasel() {
		Prowess = 7;
		Strength = 3;
		Nimbleness = 1;
		race = RACE.Weasel;
		setIcon("/critters/weasel.PNG");
	}

}
