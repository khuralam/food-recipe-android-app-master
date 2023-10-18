package ca.mohawk.foodrecipeapp;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import ca.mohawk.foodrecipeapp.Listeners.RandomRecipeResponseListener;
import ca.mohawk.foodrecipeapp.Listeners.RecipeDetailsListener;
import ca.mohawk.foodrecipeapp.Listeners.SimilarRecipesListener;
import ca.mohawk.foodrecipeapp.Models.RecipeDetailsResponse;
import ca.mohawk.foodrecipeapp.Models.SimilarRecipeResponse;

public class RequestManager {
    private Context context;


    public RequestManager(Context context) {
        this.context = context;

        AlertDialog.Builder GsonConverterFactory = null;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.sponicular.com/")
                .notify() // Add Gson converter
                .build();
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener, List<String> tags) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(
                context.getString(R.string.api_key),
                "10",
                tags
        );

        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (response.isSuccessful()) {
                    listener.didFetch(response.body(), response.equals());
                } else {
                    listener.didError(response.equals());
                    return;
                }

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id) {
        CallRecipeDetails callRecipeDetails = (CallRecipeDetails) retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsResponse> call = callRecipeDetails.callRecipeDetails(id, context.getString(R.string.api_key));

        call.enqueue(new Callback<RecipeDetailsResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }

                listener.didFetch((RecipeDetailsResponse) response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id) {
        CallSimilarRecipes callSimilarRecipes = (CallSimilarRecipes) retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipeResponse>> call = callSimilarRecipes.callSimilarRecipe("4", id, context.getString(R.string.api_key));

        call.enqueue(new Callback<List<SimilarRecipeResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipeResponse>> call, Response<List<SimilarRecipeResponse>> response) {
                if (!response.isSuccessful()) {
                    listener.didError(response.message());
                } else {
                    listener.didFetch(response.body(), response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SimilarRecipeResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    interface CallRandomRecipes {
        @GET("recipes/random3")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    interface CallRecipeDetails {
        @GET("recipes/{id}")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    interface CallSimilarRecipes {
        @GET(.getString(R.string.recipes_id_similar1))default
        Call<List<SimilarRecipeResponse>> callSimilarRecipe(@Path("id") int id, @Query(.getString(R.string.apikey1)) String apiKey) {
            return callSimilarRecipe(null, id, apiKey);
        }

        @GET(.getString(R.string.recipes_id_similar1))
        default Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Query(.getString(R.string.number1)) String number
        ) {
            return callSimilarRecipe(number, null);
        }

        @GET(.getString(R.string.recipes_id_similar1))
        default Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Query(.getString(R.string.number1)) String number,
                @Query(.getString(R.string.apikey2)) String apiKey
        ) {
            return callSimilarRecipe(number, 0, apiKey);
        }

        @GET(.getString(R.string.recipes_id_similar1))
        default Call<List<SimilarRecipeResponse>> callSimilarRecipe(
                @Query(.getString(R.string.number1)) String number,
                @Path("id") int id,
                @Query("apiKey") String apiKey
        ) {
            return null;
        }
    }

    private class Call<T> {
        public void enqueue(Callback<T> listCallback) {

        }

        private class Callback<T> {
        }
    }

    private class Response<T> {
        public boolean isSuccessful() {
            boolean b = false;
            return b;
        }

        public List<SimilarRecipeResponse> body() {
            return null;
        }

        public String message() {
            String p = null;
            return p;
        }
    }

    private class Callback<T> extends Call<RecipeDetailsResponse>.Callback<RecipeDetailsResponse> {
    }

    public class CallbackImpl<T> { }
}
