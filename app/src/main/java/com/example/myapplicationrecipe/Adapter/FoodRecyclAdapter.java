package com.example.myapplicationrecipe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationrecipe.DataConverter;
import com.example.myapplicationrecipe.Listener.RecipeClickListener;
import com.example.myapplicationrecipe.Models.Recipe;
import com.example.myapplicationrecipe.R;
import com.example.myapplicationrecipe.entity.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FoodRecyclAdapter extends  RecyclerView.Adapter<FoodViewHoldeder> {
    List<Food> data ;
    public FoodRecyclAdapter(List<Food> foods){
        data = foods ;
    }
    @NonNull
    @Override
    public FoodViewHoldeder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item,parent,false);
    return new FoodViewHoldeder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHoldeder holder, int position) {
    Food food = data.get(position);
    holder.imageViewFood.setImageBitmap(DataConverter.converByteArray2Inage(food.getImage()));
    holder.name.setText(food.getName());
        holder.des.setText(food.getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class FoodViewHoldeder extends RecyclerView.ViewHolder{
CardView food_item ;
TextView name , des;
ImageView imageViewFood ;
    public FoodViewHoldeder(@NonNull View itemView) {
        super(itemView);
        food_item = itemView.findViewById(R.id.food_item);
        name = itemView.findViewById(R.id.textFoodName);
        des = itemView.findViewById(R.id.textFoodDes);
        imageViewFood = itemView.findViewById(R.id.imageFood);

    }
}
