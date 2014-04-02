package com.common.player;

public class Ferret extends Player {
	private static final long serialVersionUID = 1L;

	public Ferret() {
		Nimbleness = 7;
		Craft = 4;
		race = RACE.Ferret;
		setIcon("/critters/ferret.PNG");
		helpInfo = "Ferrets have a great ability to climb trees with ease making escaping combat a sinch. They also have the ability to craft items relatively easily.";
	}
}
