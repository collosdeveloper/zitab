package com.zitab;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.zitab.repositories.AnimalRepository;

public class ApplicationLoader extends MultiDexApplication {
	private static ApplicationLoader applicationLoader;
	private AnimalRepository animalRepository;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		applicationLoader = this;
	}
	
	public AnimalRepository getAnimalRepository() {
		if (animalRepository == null) {
			animalRepository = new AnimalRepository();
		}
		return animalRepository;
	}
	
	public static Context getContext() {
		return applicationLoader.getApplicationContext();
	}
	
	public static ApplicationLoader getApplicationInstance() {
		return applicationLoader;
	}
}