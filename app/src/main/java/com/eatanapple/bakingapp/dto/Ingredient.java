package com.eatanapple.bakingapp.dto;

import android.os.Parcelable;

import java.io.Serializable;

public class Ingredient {

    private String ingredient;
    private double quantity;
    private String measure;

    public Ingredient() {
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredient='" + ingredient + '\'' +
                ", quantity=" + quantity +
                ", measure='" + measure + '\'' +
                '}';
    }
}
