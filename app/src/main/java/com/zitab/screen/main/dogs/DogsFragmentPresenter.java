package com.zitab.screen.main.dogs;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zitab.ApplicationLoader;
import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.base.mvp.State;
import com.zitab.utils.RxUtils;

public class DogsFragmentPresenter extends LifecyclePresenter<DogsFragmentView> {
	private static final String TAG = DogsFragmentPresenter.class.getSimpleName();
	
	private static final String DOGS_LAST_POS = "dogs_last_pos";
	private int lastScrollPos = -1;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			lastScrollPos = savedInstanceState.getInt(DOGS_LAST_POS);
		}
	}
	
	@Override
	public void attachToView(DogsFragmentView view, @Nullable Bundle savedInstanceState) {
		super.attachToView(view, savedInstanceState);
		setDogs(lastScrollPos == -1 ? 0 : lastScrollPos);
	}
	
	private void setDogs(int scrollPosition) {
		monitor(ApplicationLoader.getApplicationInstance().getAnimalRepository().getAllAvailableDogs().subscribe(dogs -> {
			getView().setDogs(dogs, scrollPosition);
		}, RxUtils.getEmptyErrorConsumer(TAG, "attachToView : setDogs")), State.DESTROY_VIEW);
	}
	
	public void setLastScrollPosition(int lastScrollPos) {
		if (isValidScrollPos(lastScrollPos)) {
			this.lastScrollPos = lastScrollPos;
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (isValidScrollPos(lastScrollPos)) {
			outState.putInt(DOGS_LAST_POS, lastScrollPos);
		}
	}
	
	private boolean isValidScrollPos(int lastScrollPos) {
		return lastScrollPos != -1;
	}
}