package com.eatanapple.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eatanapple.bakingapp.dto.Ingredient;
import com.eatanapple.bakingapp.dto.Recipe;
import com.eatanapple.bakingapp.dto.Step;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.eatanapple.bakingapp.Constants.INGREDIENTS;
import static com.eatanapple.bakingapp.Constants.STEPS;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder>{
    private Context context;
    private List<Step> steps;

    public StepsAdapter(Context context, List<Step> steps) {
        this.steps = steps;
        this.context = context;
    }

    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.step_item, parent, false);
        return new StepsAdapter.StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepsAdapter.StepsViewHolder holder, final int position) {
        // Set the food picture and text
//        String imageURL = steps.get(position);
//        String foodName = recipes.get(position).getName();

        Picasso.get().load(R.drawable.harvest_icon).into(holder.stepImageView);

//        holder.recipe_name_tv.setText(foodName);
        // Now setup, click listener for each individual recipe card
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, RecipeActivity.class);
//                List<Ingredient> ingredients = recipes.get(position).getIngredients();
//                List<Step> steps = recipes.get(position).getSteps();
//                intent.putExtra(INGREDIENTS, new Gson().toJson(ingredients));
//                intent.putExtra(STEPS, new Gson().toJson(steps));
//                // Must prefix with context since we are firing here
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
    public class StepsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.step_tv)
        TextView stepTextView;
        @BindView(R.id.food_step_iv)
        ImageView stepImageView;
        @BindView(R.id.step_card)
        CardView cardView;

        public StepsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
