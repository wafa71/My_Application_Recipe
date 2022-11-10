package com.example.myapplicationrecipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationrecipe.Models.ExtendedIngredient;
import com.example.myapplicationrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IngredientsAdapter extends  RecyclerView.Adapter<IngredientsViewHolder> {

Context context  ;
List<ExtendedIngredient> list ;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_ingredients,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.textingname.setText(list.get(position).name);
        holder.textingname.setSelected(true);
        holder.textQuantity.setText(list.get(position).original);
        holder.textQuantity.setSelected(true);

        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageIng);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class IngredientsViewHolder extends RecyclerView.ViewHolder{
TextView textQuantity, textingname ;
ImageView imageIng ;
    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
    textingname = itemView.findViewById(R.id.textview_ing_name);
        textQuantity = itemView.findViewById(R.id.textview_ing_quantity);
        imageIng=itemView.findViewById(R.id.imageViewInger);




    }
}
