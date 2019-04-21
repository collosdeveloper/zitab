package com.zitab.api.requests;

import com.zitab.api.Api;
import com.zitab.models.animals.AnimalResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiAnimals {
	@GET(Api.BASE_PATH + "?query=dog")
	Observable<AnimalResponse> loadDogs();
	
	@GET(Api.BASE_PATH + "?query=cat")
	Observable<AnimalResponse> loadCats();
}