package com.zitab.screen.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.zitab.R;
import com.zitab.base.mvp.LifecyclePresenter;
import com.zitab.base.mvp.MVPActivity;
import com.zitab.screen.main.cats.CatsFragment;
import com.zitab.screen.main.dogs.DogsFragment;

import butterknife.BindView;

public class MainActivity extends MVPActivity implements MainActivityView {
	private static final String TAB_LAST_STATE = "tab_last_state";
	
	@BindView(R.id.tab_layout)
	TabLayout tabLayout;
	
	private MainActivityPresenter presenter = new MainActivityPresenter();
	
	@Override
	public int getLayoutResourceId() {
		return R.layout.activity_main;
	}
	
	@Override
	public void initUI(Bundle savedInstanceState) {
		tabLayout.addTab(tabLayout.newTab().setTag(1).setText(getString(R.string.tab_1)));
		tabLayout.addTab(tabLayout.newTab().setTag(2).setText(getString(R.string.tab_2)));
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				switch ((int) tab.getTag()) {
					case 1:
						setSelectedFragmentName(switchFragment(CatsFragment.class.getSimpleName()));
						break;
					case 2:
						setSelectedFragmentName(switchFragment(DogsFragment.class.getSimpleName()));
						break;
				}
			}
			
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}
			
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
		updateTabSelection(savedInstanceState);
	}
	
	private void updateTabSelection(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			tabLayout.getTabAt(savedInstanceState.getInt(TAB_LAST_STATE)).select();
		} else {
			setSelectedFragmentName(switchFragment(CatsFragment.class.getSimpleName()));
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(TAB_LAST_STATE, tabLayout.getSelectedTabPosition());
	}
	
	@Override
	public LifecyclePresenter getPresenter() {
		return presenter;
	}
}