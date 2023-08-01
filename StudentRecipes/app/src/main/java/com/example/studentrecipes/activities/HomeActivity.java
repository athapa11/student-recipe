package com.example.studentrecipes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentrecipes.R;
import com.example.studentrecipes.adapters.MainRecyclerAdapter;
import com.example.studentrecipes.models.Categories;
import com.example.studentrecipes.models.Recipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mainCategoryRecycler;
    MainRecyclerAdapter mainRecyclerAdapter;
    List<Categories> allCategoriesList = new ArrayList<>();

    private DatabaseReference rootRef;
    private DatabaseReference recipesRef;
    private final static String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        // Write recipes to Firebase
        rootRef = FirebaseDatabase.getInstance().getReference();
        recipesRef = rootRef.child("RECIPES");
        writeNewRecipe("Sesame Ramen", R.drawable.seasame_ramen, 15, "Vegetarian", "Asian");
        writeNewRecipe("Nasi Goreng", R.drawable.nasi_goreng, 20, "Vegetarian", "Asian");
        writeNewRecipe("Enchilada Pie", R.drawable.enchilada_pie, 25, "Vegetarian", "Latin");
        writeNewRecipe("Chorizo Hummus", R.drawable.chorizo_hummus, 15, "Contains meat", "European");
        writeNewRecipe("Spanish Sardines", R.drawable.spanish_sardines, 10, "Pescatarian", "European");
        writeNewRecipe("Pulled Chicken & Beans", R.drawable.pulled_chicken, 80, "Gluten-free", "Latin");
        writeNewRecipe("Double Bean & Roasted Pepper Chili", R.drawable.bean_and_chili, 75, "Vegetarian", "Latin");
        writeNewRecipe("Chicken Curry", R.drawable.chicken_curry, 70, "None", "Asian");
        writeNewRecipe("Piri-piri Chicken & Spicy Rice", R.drawable.piripiri, 65, "Contains meat", "African");

        // Reading data from Firebase to populate recyclerview:
        // SELECT * FROM RECIPES WHERE duration <= 30
        Query quickRecipesQuery = FirebaseDatabase.getInstance().getReference("RECIPES")
                .orderByChild("duration")
                .endAt(30);

        quickRecipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Recipe> recipeList = new ArrayList<>();
                recipeList.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Recipe recipe = child.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                allCategoriesList.add(new Categories("Under Half an Hour", recipeList));
                setMainCategoryRecycler(allCategoriesList);

                Log.d("Recipe List", recipeList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        // SELECT * FROM RECIPES WHERE duration >= 60
        Query longRecipesQuery = FirebaseDatabase.getInstance().getReference("RECIPES")
                .orderByChild("duration")
                .startAt(60);

        longRecipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Recipe> recipeList = new ArrayList<>();
                recipeList.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Recipe recipe = child.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                allCategoriesList.add(new Categories("Over an Hour to Prep", recipeList));
                setMainCategoryRecycler(allCategoriesList);

                Log.d("Recipe List", recipeList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        // SELECT * FROM RECIPES WHERE diet = "Vegetarian"
        Query vegRecipesQuery = FirebaseDatabase.getInstance().getReference("RECIPES")
                .orderByChild("diet")
                .equalTo("Vegetarian");

        vegRecipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Recipe> recipeList = new ArrayList<>();
                recipeList.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Recipe recipe = child.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                allCategoriesList.add(new Categories("Vegetarian", recipeList));
                setMainCategoryRecycler(allCategoriesList);

                Log.d("Recipe List:", recipeList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        // SELECT * FROM RECIPES WHERE cuisine = "Asian"
        Query asianRecipesQuery = FirebaseDatabase.getInstance().getReference("RECIPES")
                .orderByChild("cuisine")
                .equalTo("Asian");

        asianRecipesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Recipe> recipeList = new ArrayList<>();
                recipeList.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Recipe recipe = child.getValue(Recipe.class);
                    recipeList.add(recipe);
                }
                allCategoriesList.add(new Categories("Asian Cuisine", recipeList));
                setMainCategoryRecycler(allCategoriesList);
                Log.d("Recipe List:", recipeList.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });

        setMainCategoryRecycler(allCategoriesList);

        // Bottom Navigation Bar Functionality
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Menu menu = bottomNav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_home:
                        break;
                    case R.id.nav_guide:
                        startActivity(new Intent(HomeActivity.this, UserGuideActivity.class));
                        finish();
                        break;
                    case R.id.nav_saved:
                        startActivity(new Intent(HomeActivity.this, SavedActivity.class));
                        finish();
                        break;
                    case R.id.nav_settings:
                        Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    // Create recyclerview
    private void setMainCategoryRecycler(List<Categories> allCategoryList) {

        // Pass the username for the current session for saving recipes
        Bundle bundle = getIntent().getExtras();
        String username = null;
        if (bundle != null) {
            username = bundle.getString("username");
        } else {
            Log.d(TAG, "Bundle is empty");
        }
        Log.d(TAG, "username is: " + username);

        mainCategoryRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allCategoryList, username);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);
    }

    // Add recipe object to firebase
    public void writeNewRecipe(String recipeName, Integer imageUrl, Integer duration, String diet, String cuisine) {
        Recipe recipe = new Recipe(recipeName, imageUrl, duration, diet, cuisine);
        recipesRef.child(recipeName).setValue(recipe);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG,"onPause");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Log.v(TAG,"onBackPressed");
    }
}