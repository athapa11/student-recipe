package com.example.studentrecipes.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentrecipes.R;
import com.example.studentrecipes.activities.RecipeDetailsActivity;
import com.example.studentrecipes.models.Recipe;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryItemViewHolder> {

    private Context context;
    private List<Recipe> categoryRecipeList;
    private String username;

    public CategoryRecyclerAdapter(Context context, List<Recipe> categoryRecipeList, String username) {
        this.context = context;
        this.categoryRecipeList = categoryRecipeList;
        this.username = username;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.category_row_recipes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position)
    {
        holder.recipeName.setText(categoryRecipeList.get(position).getRecipeName());
        holder.recipeImage.setImageResource(categoryRecipeList.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // View recipe details screen + pass recipe details
                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("recipeName", categoryRecipeList.get(position).getRecipeName());
                intent.putExtra("recipeUrl", categoryRecipeList.get(position).getImageUrl());
                intent.putExtra("duration", categoryRecipeList.get(position).getDuration());
                intent.putExtra("cuisine", categoryRecipeList.get(position).getCuisine());
                intent.putExtra("diet", categoryRecipeList.get(position).getDiet());
                intent.putExtra("username", username);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRecipeList.size();
    }


    public final class CategoryItemViewHolder extends RecyclerView.ViewHolder{
        ImageView recipeImage;
        TextView recipeName;

        public CategoryItemViewHolder(@NonNull View recipeView) {
            super(recipeView);

            recipeImage = recipeView.findViewById(R.id.recipe_image);
            recipeName = recipeView.findViewById(R.id.recipe_name);
        }
    }
}
