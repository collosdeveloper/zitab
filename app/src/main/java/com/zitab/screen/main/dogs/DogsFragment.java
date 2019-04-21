package com.zitab.screen.main.dogs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zitab.R;
import com.zitab.base.adapters.AnimalAdapter;
import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.base.mvp.MVPFragment;
import com.zitab.models.pojos.Animal;
import com.zitab.screen.detail.DetailActivity;

import java.util.List;

import butterknife.BindView;

public class DogsFragment extends MVPFragment implements DogsFragmentView {
	private DogsFragmentPresenter presenter = new DogsFragmentPresenter();
	
	@BindView(R.id.list_animals)
	RecyclerView dogsList;
	
	private AnimalAdapter dogsAdapter;
	
	public static DogsFragment newInstance() {
		return new DogsFragment();
	}
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater,
	                         @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_animals, container, false);
	}
	
	@Override
	public void onPostViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onPostViewCreated(view, savedInstanceState);
		initUI();
	}
	
	private void initUI() {
		dogsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		dogsAdapter = new AnimalAdapter();
		dogsAdapter.setAnimalClickListener(animal -> {
			Intent i = new Intent(getActivity(), DetailActivity.class);
			i.putExtra(DetailActivity.BUNDLE_ANIMAL, animal);
			openDetailActivity(i);
		});
		dogsList.setAdapter(dogsAdapter);
	}
	
	@Override
	public void setDogs(List<Animal> dogs, int scrollPosition) {
		dogsAdapter.setAnimals(dogs);
		dogsList.scrollToPosition(scrollPosition);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		presenter.setLastScrollPosition(getScrollPosition());
	}
	
	private int getScrollPosition() {
		LinearLayoutManager layoutManager = (LinearLayoutManager) dogsList.getLayoutManager();
		return layoutManager.findFirstVisibleItemPosition();
	}
	
	@Override
	public LifecyclePresenter getPresenter() {
		return presenter;
	}
}