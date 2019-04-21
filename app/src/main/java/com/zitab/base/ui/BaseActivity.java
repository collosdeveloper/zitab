package com.zitab.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.zitab.R;
import com.zitab.base.ui.interfaces.ActivityOperations;
import com.zitab.screen.main.cats.CatsFragment;
import com.zitab.screen.main.dogs.DogsFragment;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity
		implements ActivityOperations {
	private static final String TAG = BaseActivity.class.getSimpleName();
	
	protected String selectedFragment = null;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResourceId());
		ButterKnife.bind(this);
		initUI(savedInstanceState);
	}
	
	public abstract int getLayoutResourceId();
	
	public abstract void initUI(Bundle savedInstanceState);
	
	@Override
	public void setSelectedFragmentName(String fragmentName) {
		this.selectedFragment = fragmentName;
	}
	
	@Override
	public String switchFragment(String newFragmentTag) {
		if (selectedFragment == null || !selectedFragment.equals(newFragmentTag)) {
			Fragment previewFragment = getSupportFragmentManager().findFragmentByTag(selectedFragment);
			if (previewFragment != null && !previewFragment.isDetached()) {
				FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
				fragmentTransaction.detach(previewFragment);
				fragmentTransaction.commitAllowingStateLoss();
			}
			Fragment newFragment = getSupportFragmentManager().findFragmentByTag(newFragmentTag);
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			if (newFragment == null) {
				if (newFragmentTag.equals(CatsFragment.class.getSimpleName())) {
					newFragment = CatsFragment.newInstance();
				} else if (newFragmentTag.equals(DogsFragment.class.getSimpleName())) {
					newFragment = DogsFragment.newInstance();
				}
				fragmentTransaction.add(R.id.container, newFragment, newFragmentTag);
				fragmentTransaction.commitAllowingStateLoss();
			} else {
				fragmentTransaction.attach(newFragment);
				fragmentTransaction.commitAllowingStateLoss();
			}
		}
		return newFragmentTag;
	}
	
	@Override
	public void openActivity(Intent intent) {
		startActivity(intent);
	}
	
	public void showError(String error) {
		if (!TextUtils.isEmpty(error)) {
			Toast.makeText(this, error, Toast.LENGTH_LONG).show();
		}
	}
}