package com.common.item;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class CraftableItem extends Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public ArrayList<String> materials = new ArrayList<String>();

}
