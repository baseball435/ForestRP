package com.common.player;

public class Rat extends Player {
	private static final long serialVersionUID = 1L;

	public Rat() {
		Prowess = 7;
		Fortitude = 2;
		Strength = 2;
		setIcon("/critters/rat.PNG");
		race = RACE.Rat;
		helpInfo = "Rats are gifted at weapon wielding because of their high prowess.";
	}
}
