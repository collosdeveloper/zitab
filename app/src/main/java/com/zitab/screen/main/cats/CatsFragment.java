package com.zitab.screen.main.cats;

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

public class CatsFragment extends MVPFragment implements CatsFragmentView {
	private CatsFragmentPresenter presenter = new CatsFragmentPresenter();
	
	@BindView(R.id.list_animals)
	RecyclerView catsList;
	
	private AnimalAdapter catsAdapter;
	
	public static CatsFragment newInstance() {
		return new CatsFragment();
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
		catsList.setLayoutManager(new LinearLayoutManager(getActivity()));
		catsAdapter = new AnimalAdapter();
		catsAdapter.setAnimalClickListener(animal -> {
			Intent i = new Intent(getActivity(), DetailActivity.class);
			i.putExtra(DetailActivity.BUNDLE_ANIMAL, animal);
			openDetailActivity(i);
		});
		catsList.setAdapter(catsAdapter);
	}
	
	@Override
	public void setCats(List<Animal> cats, int scrollPosition) {
		catsAdapter.setAnimals(cats);
		catsList.scrollToPosition(scrollPosition);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		presenter.setLastScrollPosition(getScrollPosition());
	}
	
	private int getScrollPosition() {
		LinearLayoutManager layoutManager = (LinearLayoutManager) catsList.getLayoutManager();
		return layoutManager.findFirstVisibleItemPosition();
	}
	
	@Override
	public LifecyclePresenter getPresenter() {
		return presenter;
	}
}