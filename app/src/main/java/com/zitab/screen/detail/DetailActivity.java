package com.zitab.screen.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zitab.R;
import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.base.mvp.MVPActivity;
import com.zitab.models.pojos.Animal;

import butterknife.BindView;

public class DetailActivity extends MVPActivity implements DetailActivityView {
	public static final String BUNDLE_ANIMAL = "animal";
	
	@BindView(R.id.img_ico)
	ImageView imgIco;
	@BindView(R.id.txt_title)
	TextView txtTitle;
	
	private DetailActivityPresenter presenter = new DetailActivityPresenter();
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getIntentData();
	}
	
	@Override
	public int getLayoutResourceId() {
		return R.layout.activity_detail;
	}
	
	@Override
	public void initUI(Bundle savedInstanceState) { }
	
	@Override
	public void setTitle(String title) {
		txtTitle.setText(title);
	}
	
	@Override
	public void setImgUrl(String url) {
		Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).fit().into(imgIco);
	}
	
	private void getIntentData() {
		Intent intent = getIntent();
		if (intent != null) {
			Animal animal = (Animal) intent.getSerializableExtra(BUNDLE_ANIMAL);
			presenter.setAnimal(animal);
		}
	}
	
	@Override
	public LifecyclePresenter getPresenter() {
		return presenter;
	}
}