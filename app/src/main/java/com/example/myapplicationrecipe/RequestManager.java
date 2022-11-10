package com.example.myapplicationrecipe;

import android.content.Context;

import com.example.myapplicationrecipe.Listener.RandomRecipeResponseListener;
import com.example.myapplicationrecipe.Listener.RecipeDetailsListener;
import com.example.myapplicationrecipe.Models.DetailsRecipe;
import com.example.myapplicationrecipe.Models.Root;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context ;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestManager(Context context) {
        this.context = context;
    }
   public void getRandomRecipes(RandomRecipeResponseListener listener,List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<Root> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key),"10",tags);
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
   }
   public void getDetailsRecipe(RecipeDetailsListener recipeDetailsListener,int id ){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<DetailsRecipe> call= callRecipeDetails.callRandomRecipe(id,context.getString(R.string.api_key));
        call.enqueue(new Callback<DetailsRecipe>() {
            @Override
            public void onResponse(Call<DetailsRecipe> call, Response<DetailsRecipe> response) {
                if(!response.isSuccessful()){

                    recipeDetailsListener.didError(response.message());

                }
                recipeDetailsListener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<DetailsRecipe> call, Throwable t) {
                recipeDetailsListener.didError(t.getMessage());

            }
        });
   }

    private interface  CallRandomRecipes{
        @GET("recipes/random")
        Call<Root> callRandomRecipe (@Query("apiKey") String apiKey, @Query("number") String number , @Query("tags")List<String> tags);
    }
    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<DetailsRecipe> callRandomRecipe ( @Path("id") int id,@Query("apiKey") String apiKey );

    }
}
