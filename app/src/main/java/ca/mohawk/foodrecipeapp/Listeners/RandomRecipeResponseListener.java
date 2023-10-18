package ca.mohawk.foodrecipeapp.Listeners;

import ca.mohawk.foodrecipeapp.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response,String message);
    void didError(String messege);
}
