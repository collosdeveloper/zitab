package com.zitab.screen.main.cats;

import com.zitab.base.ui.interfaces.ErrorView;
import com.zitab.models.pojos.Animal;

import java.util.List;

public interface CatsFragmentView extends ErrorView {
	void setCats(List<Animal> cats, int scrollPosition);
}