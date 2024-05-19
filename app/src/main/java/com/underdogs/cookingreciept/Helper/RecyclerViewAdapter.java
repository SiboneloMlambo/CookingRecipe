package com.underdogs.cookingreciept.Helper;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogs.cookingreciept.ui.home.HomeFragment;
import com.underdogs.cookingrecipe.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Recipe> retrievedResponses;
    private HomeFragment context;
    public RecyclerViewAdapter(List<Recipe> retrievedResponses) {
        this.retrievedResponses = retrievedResponses;
//        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private int user_id_field;
        private TextView email_field;
        private TextView  firstName_field;
        private TextView  lastName_field;
        private TextView  recipeName_field;
        private ImageView imageURL_field;
        private TextView  recipeIngredients_field;
        private TextView  recipeInstructions_field;
        private TextView  recipeCategory_field;

        private TextView time_field;
        private int Likes_field;
        private TextView  Comments_field;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            email_field = itemView.findViewById(R.id.post_user_email);
            firstName_field = itemView.findViewById(R.id.post_user_name);
            recipeName_field = itemView.findViewById(R.id.post_recipe_name);
            imageURL_field = itemView.findViewById(R.id.post_recipe_image);
            recipeIngredients_field = itemView.findViewById(R.id.post_recipe_ingredients);
            recipeInstructions_field = itemView.findViewById(R.id.post_recipe_instructions);
            time_field = itemView.findViewById(R.id.post_recipe_cooking_time);

        }

            public void setEmail_field (String email){
                email_field.setText(email);
            }

            public void setFirstName_field (String firstName){
                firstName_field.setText(firstName);
        }

        public void recipeName_field (String RecipeName){
            recipeName_field.setText(RecipeName);
        }

        public void imageURL_field (Uri imageURL){
            imageURL_field.setImageURI(imageURL);
        }

        public void recipeIngredients_field (String recipeIngredients){
            recipeIngredients_field.setText(recipeIngredients);
        }

        public void recipeInstructions_field (String recipeInstructions){
            recipeInstructions_field.setText(recipeInstructions);
        }

        public void time_field (String time){
            time_field.setText(time);
        }


    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getActivity()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.setEmail_field(retrievedResponses.get(position).getEmail());
        holder.setFirstName_field(retrievedResponses.get(position).getFirstName());
        holder.recipeName_field(retrievedResponses.get(position).getRecipeName());
        holder.imageURL_field(retrievedResponses.get(position).getImageURL());
        holder.recipeIngredients_field(retrievedResponses.get(position).getRecipeIngredients());
        holder.recipeInstructions_field(retrievedResponses.get(position).getRecipeInstructions());
        holder.time_field(retrievedResponses.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return retrievedResponses.size();
    }
}
