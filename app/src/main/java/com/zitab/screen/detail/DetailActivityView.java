package com.zitab.screen.detail;

import com.zitab.base.ui.interfaces.ErrorView;

interface DetailActivityView extends ErrorView {
	void setTitle(String title);
	
	void setImgUrl(String url);
}