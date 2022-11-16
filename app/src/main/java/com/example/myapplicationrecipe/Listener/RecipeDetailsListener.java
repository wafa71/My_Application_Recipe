package com.example.myapplicationrecipe.Listener;

import com.example.myapplicationrecipe.Models.DetailsRecipe;

public interface RecipeDetailsListener {
    void didFetch(DetailsRecipe recipe, String message);
    void didError (String message);

}
