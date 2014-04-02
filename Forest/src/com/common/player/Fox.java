package com.common.player;

public class Fox extends Player {
	private static final long serialVersionUID = 1L;

	public Fox() {
		Craft = 4;
		Intelligence = 3;
		Nimbleness = 4;
		setIcon("/critters/fox.PNG");
		race = RACE.Fox;
		helpInfo = "Foxes have an aptitude for climbing and crafting. They are also quite intelligence.";
	}
}
