package com.eatanapple.bakingapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eatanapple.bakingapp.dto.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.eatanapple.bakingapp.Constants.INGREDIENTS;

public class RecipeFragment extends Fragment {

    @BindView(R.id.ingredients_tv)
    TextView ingredientsTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Toast.makeText(getContext(), "Im in detail now..", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();

        List<Ingredient> ingredients = new Gson().fromJson(bundle.getString(INGREDIENTS),
                new TypeToken<List<Ingredient>>(){}.getType());

        StringBuilder ingredientsTextForCard = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            String quantity = String.valueOf(ingredients.get(i).getQuantity());
            String measure = ingredients.get(i).getMeasure();
            String ingredient = ingredients.get(i).getIngredient();
            if (measure.equals("UNIT")) {
                ingredientsTextForCard.append(quantity + " " + ingredient + "\n");
            } else {
                ingredientsTextForCard.append(quantity + " " + measure + " " + ingredient + "\n");
            }
        }
        ingredientsTextView.setText(ingredientsTextForCard.toString());

        return view;
    }

}
