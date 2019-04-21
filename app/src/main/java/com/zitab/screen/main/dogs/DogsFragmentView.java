package com.zitab.screen.main.dogs;

import com.zitab.base.ui.interfaces.ErrorView;
import com.zitab.models.pojos.Animal;

import java.util.List;

public interface DogsFragmentView extends ErrorView {
	void setDogs(List<Animal> dogs, int scrollPosition);
}