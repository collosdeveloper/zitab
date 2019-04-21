package com.zitab.api.rx_tasks.animals;

import com.zitab.api.Api;
import com.zitab.api.rx_tasks.ApiTask;
import com.zitab.models.animals.AnimalResponse;

import io.reactivex.Observable;

public class LoadAnimal extends ApiTask<AnimalResponse> {
	public enum AnimalType {
		DOG,
		CAT
	}
	
	private final AnimalType animalType;
	
	public LoadAnimal(AnimalType animalType) {
		super();
		
		this.animalType = animalType;
	}
	
	@Override
	protected Observable<AnimalResponse> getObservableTask() {
		Observable<AnimalResponse> animalResponseObservable = null;
		switch (animalType) {
			case DOG:
				animalResponseObservable
						= Api.getInst().animals().loadDogs();
				break;
			case CAT:
				animalResponseObservable
						= Api.getInst().animals().loadCats();
				break;
		}
		return animalResponseObservable;
	}
}