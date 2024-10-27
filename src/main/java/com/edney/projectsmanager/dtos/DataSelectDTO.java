package com.edney.projectsmanager.dtos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataSelectDTO<T> implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private T value;
	private boolean selected;

}
