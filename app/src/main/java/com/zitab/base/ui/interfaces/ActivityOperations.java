package com.zitab.base.ui.interfaces;

import android.content.Intent;

public interface ActivityOperations {
	void setSelectedFragmentName(String fragmentName);
	
	String switchFragment(String newFragmentTag);
	
	void openActivity(Intent intent);
}