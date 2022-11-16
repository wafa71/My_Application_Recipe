package com.example.myapplicationrecipe;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationrecipe.Adapter.IngredientsAdapter;
import com.example.myapplicationrecipe.Listener.RecipeDetailsListener;
import com.example.myapplicationrecipe.Models.DetailsRecipe;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailsActivity extends AppCompatActivity {
TextView textName;
    TextView textSouce;
    TextView textSummary;
    ImageView imageView ;
    RecyclerView recycleIngredients ;
    int id;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        findViews();
        id= Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getDetailsRecipe(recipeDetailslistener,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading details......");
        dialog.show();
    }

    private void findViews() {
    textName = findViewById(R.id.nameText);
        textSouce = findViewById(R.id.sourceText);
        textSummary = findViewById(R.id.summaryText);
        imageView = findViewById(R.id.imagedetails);
        recycleIngredients = findViewById(R.id.recycleIngredients);

    }
    private  final RecipeDetailsListener recipeDetailslistener=new RecipeDetailsListener() {
        @Override
        public void didFetch(DetailsRecipe recipe, String message) {
        dialog.dismiss();
        textName.setText(recipe.title);
        textSouce.setText(recipe.sourceName);
        textSummary.setText(recipe.summary);
            Picasso.get().load(recipe.image).into(imageView);
            recycleIngredients.setHasFixedSize(true);
            recycleIngredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingredientsAdapter = new IngredientsAdapter(RecipeDetailsActivity.this, recipe.extendedIngredients);
            recycleIngredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    };

}