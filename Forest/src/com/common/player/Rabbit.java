package com.common.player;

public class Rabbit extends Player {
	private static final long serialVersionUID = 1L;

	public Rabbit() {
		Awareness = 4;
		Nimbleness = 3;
		Luck = 4;
		setIcon("/critters/rabbit.PNG");
		race = RACE.Rabbit;
		helpInfo = "Rabbits are inheritently lucky from birth. They also make excellent climbers and are very aware of their surroundings.";
	}
}
