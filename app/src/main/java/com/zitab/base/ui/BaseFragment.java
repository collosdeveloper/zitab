package com.zitab.base.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.zitab.base.ui.interfaces.ActivityOperations;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
	private Unbinder unbinder;
	private ActivityOperations activityOperations;
	
	@Override
	public final void onAttach(Context context) {
		super.onAttach(context);
		onAttachToContext(context);
	}
	
	@Override
	public final void onAttach(Activity activity) {
		super.onAttach(activity);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
			onAttachToContext(activity);
		}
	}
	
	protected void onAttachToContext(Context context) {
		activityOperations = (ActivityOperations) context;
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		unbinder = ButterKnife.bind(this, view);
		view.setClickable(true);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		activityOperations = null;
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder.unbind();
	}
	
	protected void openDetailActivity(Intent intent) {
		activityOperations.openActivity(intent);
	}
	
	public void showError(String errorText) {
		if (!TextUtils.isEmpty(errorText)) {
			Toast.makeText(getContext(), errorText, Toast.LENGTH_LONG).show();
		}
	}
}