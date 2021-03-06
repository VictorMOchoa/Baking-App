package com.eatanapple.bakingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eatanapple.bakingapp.dto.Recipe;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecipeAdapter recipeAdapter;
    @BindView(R.id.recipes_rv)
    public RecyclerView recipesRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the view now/save as a variable for use in binding with butterknife
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        // Since we rely on getting json from an api, we must first check that we have connectivity
        if (networkIsAvailable()) {
            String API_URL = "http://d17h27t6h515a5.cloudfront.net/";
            OkHttpClient.Builder client = new OkHttpClient().newBuilder();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            BakingRecipe bakingRecipe;

            bakingRecipe = retrofit.create(BakingRecipe.class);

                bakingRecipe.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        recipeAdapter = new RecipeAdapter(getActivity(), response.body());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        recipesRecyclerView.setLayoutManager(linearLayoutManager);
                        recipesRecyclerView.setAdapter(recipeAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    }
                });

        } else {
            // Show message that we do not have connectivity
        }

        return view;
    }

    private boolean networkIsAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
