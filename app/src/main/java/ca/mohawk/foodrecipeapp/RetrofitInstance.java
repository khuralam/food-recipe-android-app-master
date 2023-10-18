package ca.mohawk.foodrecipeapp;

public class RetrofitInstance {
    private static Class<?> retrofit;
    private static final String BASE_URL = "http://api.sponicular.com/";

    public static Class<?> getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Builder()
                    .baseUrl("http://api.sponicular.com/")
                    .getClass();
        }
        return retrofit;
    }

    private static class Retrofit {
    }

    private static class Builder {
        public Object baseUrl(String baseUrl) {
            Object o = null;
            return o;
        }
    }

    private static class GsonConverterFactory {
        public static Object create() {
            return null;
        }
    }
}
