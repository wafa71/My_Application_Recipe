package com.example.myapplicationrecipe;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplicationrecipe.Adapter.RandomRecipeAdapter;
import com.example.myapplicationrecipe.Listener.RandomRecipeResponseListener;
import com.example.myapplicationrecipe.Listener.RecipeClickListener;
import com.example.myapplicationrecipe.Models.Recipe;
import com.example.myapplicationrecipe.Models.Root;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog ;
    RequestManager manager ;
    RandomRecipeAdapter randomRecipeAdapter ;
    RecyclerView recyclerView ;
    List<Recipe> recipes =  new ArrayList<>();
    List<String> tags= new ArrayList<>();
    Spinner spinner ;
    SearchView searchView ;
    private  final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(Root response, String mesg) {
            recipes = response.recipes;
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this,recipes,recipeClickListener);
            System.out.println(recipes.size());
            recyclerView.setAdapter(randomRecipeAdapter);
            dialog.dismiss();
        }

        @Override
        public void didError(String mesg) {
            Toast.makeText(MainActivity.this,mesg,Toast.LENGTH_LONG);
        }

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loding ....");
        manager = new RequestManager(this);
        //manager.getRandomRecipes(randomRecipeResponseListener,tags);
        searchView = findViewById(R.id.searchView_home);
        searchView.setQueryHint("search for something");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recyclerView=(RecyclerView) findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
        randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this,recipes,recipeClickListener);
        System.out.println(recipes.size());
        recyclerView.setAdapter(randomRecipeAdapter);
        //spinner selected
        spinner = findViewById(R.id.spinner_tags);
        spinner.setOnItemSelectedListener(sprinnerSelectedListe);
        //dialog.show();


    }
    private final AdapterView.OnItemSelectedListener sprinnerSelectedListe= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tags.clear();
        tags.add(parent.getSelectedItem().toString());
        manager.getRandomRecipes(randomRecipeResponseListener,tags);
        dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
    @Override
    public void onRecipeClicked(String id) {
       startActivity(new Intent(MainActivity.this,RecipeDetailsActivity.class).putExtra("id",id) );
    }
};
}