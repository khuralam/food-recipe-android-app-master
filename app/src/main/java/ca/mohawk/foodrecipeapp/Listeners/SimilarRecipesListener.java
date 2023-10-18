package ca.mohawk.foodrecipeapp.Listeners;

import java.util.List;

import ca.mohawk.foodrecipeapp.Models.SimilarRecipeResponse;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipeResponse>response,String message);
    void didError(String message);
}
