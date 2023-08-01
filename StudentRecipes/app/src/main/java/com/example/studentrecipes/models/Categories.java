package com.example.studentrecipes.models;

import java.util.List;

public class Categories {
    String categoryTitle;
    List<Recipe> categoryRecipeList;

    public Categories(String categoryTitle, List<Recipe> categoryRecipeList) {
        this.categoryTitle = categoryTitle;
        this.categoryRecipeList = categoryRecipeList;
    }

    public List<Recipe> getCategoryRecipeList() {
        return categoryRecipeList;
    }

    public void setCategoryRecipeList(List<Recipe> categoryRecipeList) {
        this.categoryRecipeList = categoryRecipeList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
