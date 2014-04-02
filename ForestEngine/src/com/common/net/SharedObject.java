package com.common.net;

import java.io.Serializable;
import java.lang.reflect.Field;

@SuppressWarnings("serial")
public abstract class SharedObject implements Serializable {

	public String identity;

	public void setField(String fieldName, Object value)
			throws NoSuchFieldException, IllegalAccessException {
		// Field field = getClass().getDeclaredField(fieldName);
		Field field = getClass().getField(fieldName);
		field.set(this, value);
	}

}
