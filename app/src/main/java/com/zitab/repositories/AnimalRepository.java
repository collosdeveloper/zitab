package com.zitab.repositories;

import com.zitab.api.rx_tasks.animals.LoadAnimal;
import com.zitab.models.pojos.Animal;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class AnimalRepository {
	private List<Animal> cats;
	private List<Animal> dogs;
	
	public AnimalRepository() {
		this.cats = new ArrayList<>();
		this.dogs = new ArrayList<>();
	}
	
	public Observable<List<Animal>> getAllAvailableCats() {
		if (!cats.isEmpty()) {
			return Observable.just(cats);
		} else {
			return new LoadAnimal(LoadAnimal.AnimalType.CAT).getTask()
					.flatMap(animalResponse -> Observable.just(animalResponse.getAnimals()));
		}
	}
	
	public Observable<List<Animal>> getAllAvailableDogs() {
		if (!dogs.isEmpty()) {
			return Observable.just(dogs);
		} else {
			return new LoadAnimal(LoadAnimal.AnimalType.DOG).getTask()
					.flatMap(animalResponse -> Observable.just(animalResponse.getAnimals()));
		}
	}
}