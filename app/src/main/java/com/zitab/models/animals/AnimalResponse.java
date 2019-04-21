package com.zitab.models.animals;

import com.zitab.models.pojos.Animal;

import java.io.Serializable;
import java.util.List;

public class AnimalResponse implements Serializable {
	private String message;
	private List<Animal> data;
	
	public String getMessage() {
		return message;
	}
	
	public List<Animal> getAnimals() {
		return data;
	}
	
	@Override
	public String toString() {
		return "AnimalResponse{" +
				"message='" + message + '\'' +
				", data=" + data +
				'}';
	}
}