package com.example.myapplicationrecipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationrecipe.Listener.RecipeClickListener;
import com.example.myapplicationrecipe.Models.Recipe;
import com.example.myapplicationrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RandomRecipeAdapter extends  RecyclerView.Adapter<RandomRecipeViewHoldeder> {
    Context context ;
    List<Recipe> list ;
    RecipeClickListener recipeClickListener ;
    public RandomRecipeAdapter(Context context, List<Recipe> list,RecipeClickListener recipeClickListener ) {
        this.context = context;
        this.list = list;
        this.recipeClickListener= recipeClickListener ;
    }

    @NonNull
    @Override
    public RandomRecipeViewHoldeder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_random_recipe, parent, false);
        return new RandomRecipeViewHoldeder(view);
       // return new RandomRecipeViewHoldeder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHoldeder holder, int position) {
   Recipe recipe = list.get(position);
   System.out.println(recipe.aggregateLikes);
    holder.textViewTitle.setText(recipe.title);
        holder.textViewTitle.setSelected(true);
        holder.textViewLikes.setText(recipe.aggregateLikes+" Likes");
        holder.textViewservigns.setText(recipe.servings+" servings");

        holder.textViewTime.setText(recipe.readyInMinutes+" Minutes");

        Picasso.get().load(recipe.image).into(holder.imageViewFood);
        holder.listcardrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeClickListener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class RandomRecipeViewHoldeder extends RecyclerView.ViewHolder{
CardView listcardrandom ;
TextView textViewTitle , textViewservigns, textViewLikes, textViewTime ;
ImageView imageViewFood ;
    public RandomRecipeViewHoldeder(@NonNull View itemView) {
        super(itemView);
        listcardrandom = itemView.findViewById(R.id.listcardrandom);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        textViewservigns = itemView.findViewById(R.id.textView_servings);
        textViewTime = itemView.findViewById(R.id.textView_time);
        imageViewFood = itemView.findViewById(R.id.imageView_food);
        textViewLikes = itemView.findViewById(R.id.textView_likes);

    }
}
