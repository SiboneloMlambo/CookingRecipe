package com.underdogs.cookingreciept.Helper;

import android.net.Uri;

public class Recipe {

    private int user_id;
    private String email;
    private String firstName;
    private String lastName;
    private String recipeName;
    private Uri imageURL;
    private String recipeIngredients;
    private String recipeInstructions;
    private String recipeCategory;
    private String Likes;
    private String Comments;

    private String time;

    public Recipe(String email, String firstName, String recipeName, Uri imageURL, String recipeIngredients, String recipeInstructions, String time) {
//        this.user_id = user_id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.recipeName = recipeName;
        this.imageURL = imageURL;
        this.recipeIngredients = recipeIngredients;
        this.recipeInstructions = recipeInstructions;
        this.recipeCategory = recipeCategory;
//        Likes = likes;
//        Comments = comments;
        this.time = time;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Uri getImageURL() {
        return imageURL;
    }

    public void setImageURL(Uri imageURL) {
        this.imageURL = imageURL;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getLikes() {
        return Likes;
    }

    public void setLikes(String likes) {
        Likes = likes;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
    @Override
    public String toString() {
        return "Recipe{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", recipeIngredients='" + recipeIngredients + '\'' +
                ", recipeInstructions='" + recipeInstructions + '\'' +
                ", recipeCategory='" + recipeCategory + '\'' +
                ", Likes='" + Likes + '\'' +
                ", Comments='" + Comments + '\'' +
                '}';
    }
}
