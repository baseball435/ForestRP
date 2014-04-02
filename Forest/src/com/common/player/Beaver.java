package com.common.player;

public class Beaver extends Player {
	private static final long serialVersionUID = 1L;

	public Beaver() {
		race = RACE.Beaver;
		Fortitude = 7;
		Strength = 3;
		Craft = 1;
		setIcon("/critters/beaver.PNG");
		helpInfo = "Beavers high fortitude let's them take a real beating in combat. Their strength also allows them to return a beating.";
	}
}
