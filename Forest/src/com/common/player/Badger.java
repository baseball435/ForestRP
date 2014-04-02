package com.common.player;

public class Badger extends Player {

	private static final long serialVersionUID = 1L;

	public Badger() {
		race = RACE.Badger;
		Strength = 7;
		Fortitude = 6;
		Intelligence = -2;
		setIcon("/critters/badger.PNG");
		helpInfo = "Badgers are great with blacksmithing and close ranged combat because of their high strength and fortitude.";
	}
}
