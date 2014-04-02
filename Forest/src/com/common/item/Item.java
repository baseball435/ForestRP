package com.common.item;

import java.io.Serializable;

import com.common.movable.Movable;

public abstract class Item extends Movable implements Serializable {

	private static final long serialVersionUID = 1L;

	public double weight;

	public int rarity;

	public boolean edible = false;

}
