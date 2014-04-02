package com.common.item;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class EquippableItem extends CraftableItem implements Serializable {
	public enum EQUIPLOC {
		HAND, NECK, RING
	}

	public String skillUsed;
	public EQUIPLOC equipLoc;

	/**
	 * When items reach 0 or below strength, the item is destroyed
	 */
	public float strength = 100f;
}
