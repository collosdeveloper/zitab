package com.zitab.screen.main.cats;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zitab.ApplicationLoader;
import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.base.mvp.State;
import com.zitab.utils.RxUtils;

public class CatsFragmentPresenter extends LifecyclePresenter<CatsFragmentView> {
	private static final String TAG = CatsFragmentPresenter.class.getSimpleName();
	
	private static final String CATS_LAST_POS = "cats_last_pos";
	private int lastScrollPos = -1;
	
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null) {
			lastScrollPos = savedInstanceState.getInt(CATS_LAST_POS);
		}
	}
	
	@Override
	public void attachToView(CatsFragmentView view, @Nullable Bundle savedInstanceState) {
		super.attachToView(view, savedInstanceState);
		setCats(lastScrollPos == -1 ? 0 : lastScrollPos);
	}
	
	private void setCats(int scrollPosition) {
		monitor(ApplicationLoader.getApplicationInstance().getAnimalRepository().getAllAvailableCats().subscribe(cats -> {
			getView().setCats(cats, scrollPosition);
		}, RxUtils.getEmptyErrorConsumer(TAG, "attachToView : setCats")), State.DESTROY_VIEW);
	}
	
	public void setLastScrollPosition(int lastScrollPos) {
		if(isValidScrollPos(lastScrollPos)) {
			this.lastScrollPos = lastScrollPos;
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(isValidScrollPos(lastScrollPos)) {
			outState.putInt(CATS_LAST_POS, lastScrollPos);
		}
	}
	
	private boolean isValidScrollPos(int lastScrollPos) {
		return lastScrollPos != -1;
	}
}