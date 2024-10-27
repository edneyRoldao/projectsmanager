package com.edney.projectsmanager.dtos;

import java.io.Serializable;

public class DataSelectDTO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private T value;
	private boolean selected;
	
	public DataSelectDTO () {
		
	}

	public DataSelectDTO (T value, boolean selected) {
		this.value = value;
		this.selected = selected;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
