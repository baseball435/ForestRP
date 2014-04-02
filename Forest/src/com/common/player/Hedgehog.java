package com.common.player;

public class Hedgehog extends Player {
	private static final long serialVersionUID = 1L;

	public Hedgehog() {
		Fortitude = 9;
		Strength = 2;
		setIcon("/critters/hedgehog.PNG");
		race = RACE.Hedgehog;
		helpInfo = "Hedgehogs have the highest fortitude of any race, thus can take an extreme pounding and remain standing.";
	}
}
