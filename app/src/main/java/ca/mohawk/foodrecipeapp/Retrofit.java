package ca.mohawk.foodrecipeapp;

public class Retrofit {
    public RequestManager.CallRandomRecipes create(Class<RequestManager.CallSimilarRecipes> callRandomRecipesClass) {
        return null;
    }

    public Object create() {
        RequestManager.CallRandomRecipes callRandomRecipes = create(null);
        return callRandomRecipes;
    }

    public static class Builder {
        public Object baseUrl(String s) {
            return null;
        }
    }
}
