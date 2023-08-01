package com.example.studentrecipes.models;

public class Recipe {
    String recipeName;
    Integer imageUrl;
    Integer duration;
    String diet;
    String cuisine;
    String ingredients;
    String method;

    public Recipe(String recipeName, Integer imageUrl, Integer duration, String diet, String cuisine) {
        this.recipeName = recipeName;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.diet = diet;
        this.cuisine = cuisine;
    }

    public Recipe(String ingredients, String method) {
        this.ingredients = ingredients;
        this.method = method;
    }

    public Recipe() {}

    public Integer getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecipeName() { return recipeName;  }
    public void setRecipeName(String recipeName) { this.recipeName = recipeName; }

    public Integer getDuration() { return duration;  }
    public void setDuration(Integer duration) { this.duration = duration; }

    public String getDiet() { return diet; }
    public void setDiet(String diet) { this.diet = diet; }

    public String getCuisine() { return cuisine; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
