package ca.mohawk.foodrecipeapp.Listeners;

import ca.mohawk.foodrecipeapp.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response,String message);
    void didError(String message);
}
