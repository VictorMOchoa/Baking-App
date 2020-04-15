package com.eatanapple.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.eatanapple.bakingapp.Constants.*;
public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        RecipeFragment recipeFragment = new RecipeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(INGREDIENTS, intent.getStringExtra(INGREDIENTS));
        recipeFragment.setArguments(bundle);
        System.out.println(recipeFragment.getArguments());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_fragment, recipeFragment).commit();
    }
}
