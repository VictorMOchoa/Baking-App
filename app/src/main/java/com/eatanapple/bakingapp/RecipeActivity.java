package com.eatanapple.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.eatanapple.bakingapp.dto.Ingredient;
import com.eatanapple.bakingapp.dto.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.eatanapple.bakingapp.Constants.*;
public class RecipeActivity extends AppCompatActivity {
@BindView(R.id.ingredients_tv)
TextView ingredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        List<Ingredient> ingredients = new Gson().fromJson(intent.getStringExtra(INGREDIENTS),
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
    }
}
