package com.zitab.base.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zitab.R;
import com.zitab.models.pojos.Animal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalsViewHolder> {
	private AnimalClickListener animalClickListener;
	private List<Animal> animals = new ArrayList<>();
	
	@NonNull
	@Override
	public AnimalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new AnimalsViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),
				parent, false));
	}
	
	private int getLayoutId() {
		return R.layout.item_animal;
	}
	
	@Override
	public void onBindViewHolder(@NonNull AnimalsViewHolder holder, int position) {
		holder.bind(animals.get(position), position);
	}
	
	@Override
	public int getItemCount() {
		return animals == null ? 0 : animals.size();
	}
	
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
		notifyDataSetChanged();
	}
	
	class AnimalsViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.container)
		LinearLayout container;
		@BindView(R.id.img_ico)
		ImageView imgIco;
		@BindView(R.id.txt_title)
		TextView txtTitle;
		@BindView(R.id.txt_subtitle)
		TextView txtSubtitle;
		
		private Animal animal;
		
		AnimalsViewHolder(View view) {
			super(view);
			
			ButterKnife.bind(this, view);
			
			container.setOnClickListener(v -> {
				if (animalClickListener != null) {
					animalClickListener.onItemClicked(animal);
				}
			});
		}
		
		void bind(Animal animal, int position) {
			this.animal = animal;
			txtTitle.setText(String.valueOf(position));
			txtSubtitle.setText(animal.getTitle());
			setPicture();
		}
		
		private void setPicture() {
			Picasso.get().load(animal.getUrl()).placeholder(R.mipmap.ic_launcher).fit().into(imgIco);
		}
	}
	
	public void setAnimalClickListener(AnimalClickListener animalClickListener) {
		this.animalClickListener = animalClickListener;
	}
	
	public interface AnimalClickListener {
		void onItemClicked(Animal animal);
	}
}