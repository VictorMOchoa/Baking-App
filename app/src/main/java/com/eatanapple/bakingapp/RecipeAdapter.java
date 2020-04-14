package com.eatanapple.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eatanapple.bakingapp.dto.Ingredient;
import com.eatanapple.bakingapp.dto.Recipe;
import com.eatanapple.bakingapp.dto.Step;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.eatanapple.bakingapp.Constants.*;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context context;
    private List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.recipes = recipes;
        this.context = context;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipes_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, final int position) {
        // Set the food picture and text
        String imageURL = recipes.get(position).getImage();
        String foodName = recipes.get(position).getName();
        if (imageURL.isEmpty()) {
            Picasso.get().load(R.drawable.cooking_icon).into(holder.food_thumbnail_iv);
        } else {
            Picasso.get().load(imageURL).into(holder.food_thumbnail_iv);
        }
        holder.recipe_name_tv.setText(foodName);
        // Now setup, click listener for each individual recipe card
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent = new Intent(context, RecipeActivity.class);
        List<Ingredient> ingredients = recipes.get(position).getIngredients();
        List<Step> steps = recipes.get(position).getSteps();
        intent.putExtra(INGREDIENTS, new Gson().toJson(ingredients));
        intent.putExtra(STEPS, new Gson().toJson(steps));
        // Must prefix with context since we are firing here
        context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipe_name_tv)
        TextView recipe_name_tv;
        @BindView(R.id.food_thumbnail_iv)
        ImageView food_thumbnail_iv;
        @BindView(R.id.recipe_card)
        CardView cardView;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
