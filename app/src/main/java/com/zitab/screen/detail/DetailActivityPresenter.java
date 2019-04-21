package com.zitab.screen.detail;

import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.models.pojos.Animal;

public class DetailActivityPresenter extends LifecyclePresenter<DetailActivityView> {
	
	public void setAnimal(final Animal animal) {
		getView().setTitle(animal.getTitle());
		getView().setImgUrl(animal.getUrl());
	}
}