package com.example.studentrecipes.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentrecipes.R;
import com.example.studentrecipes.data.SQLiteDatabaseHelper;
import com.example.studentrecipes.models.Recipe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RecipeDetailsActivity extends AppCompatActivity {

    private final static String TAG = "RecipeDetailsActivity";
    private DatabaseReference rootRef;
    private DatabaseReference recipesRef;
    private SQLiteDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Use local database
        db = new SQLiteDatabaseHelper(this);

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        ImageView recipeImageView = (ImageView) findViewById(R.id.recipe_image2);

        TextView durationTextView = (TextView) findViewById(R.id.duration_textview);
        TextView cuisineTextView = (TextView) findViewById(R.id.cuisine_textview);
        TextView dietTextView = (TextView) findViewById(R.id.diet_textview);

        TextView ingredientsTextView = (TextView) findViewById(R.id.ingredients_textview);
        TextView methodTextView = (TextView) findViewById(R.id.method_textview);

        // Write recipe ingredients and method to Firebase
        rootRef = FirebaseDatabase.getInstance().getReference();
        recipesRef = rootRef.child("RECIPES");
        writeRecipeDetails("Sesame Ramen", "Instant noodles\nSpring onions\nPak choi\nAn egg\nSesame seeds\nChilli sauce", "STEP 1\nCook the noodles with the sachet of flavouring provided (or use stock instead of the sachet, if you have it). Add the spring onions and pak choi for the final min.\n\nSTEP 2\nMeanwhile, simmer the egg for 6 mins from boiling, run it under cold water to stop it cooking, then peel it. Toast the sesame seeds in a frying pan.\n\nSTEP 3\nTip the noodles and greens into a deep bowl, halve the boiled egg and place on top. Sprinkle with sesame seeds, then drizzle with the sauce or sesame oil provided with the noodles, and chilli sauce, if using.\n");
        writeRecipeDetails("Nasi Goreng", "Oil\nOnion\nCrushed garlic\nGrated carrot\nShredded savoy cabbage\nCooked Brown rice\nSoy sauce\nAn egg\nSriracha sauce", "STEP 1\nHeat the oil in a wok over a high heat. Add the onion and cook for 3-4 mins until softened and slightly caramelised. Add the garlic and stir for 1 min.\n\nSTEP 2\nToss in the carrot and cabbage, then cook for 1-2 mins. Add the rice and stir to warm through. Pour in the fish sauce, soy sauce and some seasoning. Make a well in the centre of the wok and crack in the egg. Fry until the white is nearly set.\n\nSTEP 3\nServe the rice in a large bowl, topped with the fried egg and drizzled with chilli sauce.\n");
        writeRecipeDetails("Enchilada Pie","Rapeseed oil\nPeppers\nRed onion\nMixed beans\nFajita spice mix\nCanned Tomatoes\nCoriander\nCorn tortillas\nLow-fat soured cream\nGrated cheddar", "STEP 1\nHeat the oil in a pan. Fry the peppers and onion until soft, about 10 mins. Add the beans, fajita spice mix, chopped tomatoes and some seasoning. Bubble for 5 mins to reduce the tomatoes a little, then stir in most of the coriander. Heat the grill and warm the tortillas in the microwave for 30 seconds.\n\nSTEP 2\nSpread a quarter of the pepper and onion mixture over the base of an ovenproof dish (a round one, if possible) or frying pan. Top with some of the soured cream and a warm tortilla, then repeat the layers three more times, finishing off with a final layer of soured cream. Sprinkle over the cheese and grill for 5 mins, or until golden and bubbling. Scatter with the remaining coriander before serving.\n");
        writeRecipeDetails("Chorizo Hummus", "Chickpeas\nOlive oil\nLemon\nChorizo\nKale\nflatbread", "STEP 1\nWarm the chickpeas in a microwave or frying pan in their liquid. Drain and reserve the liquid. Tip half the chickpeas into a small food processor with 1 tbsp oil, the lemon juice and a splash of the liquid from the tin and whizz to a paste. Season.\n\nSTEP 2\nPut the chorizo in a small frying pan and cook over a low heat until it starts to release its oils, then turn up the heat and continue cooking until the chorizo starts to crisp. Add the remaining chickpeas and stir for a couple of mins. Stir in the kale and cook until it wilts.\n\nSTEP 3\nSpoon the warm hummus into a bowl and tip the chorizo, chickpeas and kale on top. Drizzle over the remaining oil, season well and serve with flatbread for scooping up.\n");
        writeRecipeDetails("Spanish Sardines", "Olive oil\nGarlic\nRed chili\nLemon\nCanned sardines\nBrown bread\nParsley", "STEP 1\nHeat the olive oil in a frying pan, then sizzle the garlic clove and red chilli. Add the lemon zest.\n\nSTEP 2\nAdd the sardines and heat through for a few mins until warm.\n\nSTEP 3\nToast the brown bread. Stir the parsley into the sardines, add a squeeze of lemon juice, then divide between the toast and serve.\n");
        writeRecipeDetails("Pulled Chicken & Beans", "Chicken thighs\nChipotle paste\nCrushed garlic cloves\nCanned tomatoes\nOnion\nBlack beans\nKidney beans\n Parsley, Coriander, Mint\nIceberg lettuce\nCucumber\ndrizzle of olive oil", "STEP 1\nHeat oven to 180C/160C fan/gas 4. Rub the chicken thighs with 2 tbsp of the chipotle paste. Put the rest in a medium bowl with the garlic, tomatoes, onion and some seasoning. Stir to combine, then tip into a large roasting tin. Sit the chicken thighs close together on top of the sauce. Cover with foil and bake for 1 hr.\nSTEP 2\nRemove the roasting tin from the oven, add all the beans and stir into the tomato mixture around the chicken. Put back in the oven, uncovered, for 20 mins or until the chicken is tender and the beans are hot.\nSTEP 3\nMeanwhile, mix the herbs, lettuce and cucumber with a drizzle of olive oil and set aside.\n\nSTEP 4\nShred the chicken using a knife and fork, and discard the bones. Mix the chicken through the sauce and beans. Serve with the salad, tortilla chips and lime wedges.\n");
        writeRecipeDetails("Double Bean & Roasted Pepper Chili", "2 onions, chopped\n" + "2 celery sticks, finely chopped\n" + "2 yellow or orange peppers, finely chopped\n" + "2 tbsp sunflower oil or rapeseed oil\n" + "2 x 460g jars roasted red peppers\n" + "2 tsp chipotle paste\n" + "2 tbsp red wine vinegar\n" + "1 tbsp cocoa powder\n" + "1 tbsp dried oregano\n" + "1 tbsp sweet smoked paprika\n" + "2 tbsp ground cumin\n" + "1 tsp ground cinnamon\n" + "2 x 400g cans chopped tomatoes\n" + "400g can refried beans\n" + "3 x 400g cans kidney beans, drained and rinsed\n" + "2 x 400g cans black beans, drained and rinsed", "STEP 1\n" + "Put the onions, celery and chopped peppers with the oil in your largest flameproof casserole dish or heavy-based saucepan, and fry gently over a low heat until soft but not coloured.\n" + "\n" + "STEP 2\n" + "Drain both jars of peppers over a bowl to catch the juices. Put a quarter of the peppers into a food processor with the chipotle paste, vinegar, cocoa, dried spices and herbs. Whizz to a purée, then stir into the softened veg and cook for a few mins.\n" + "\n" + "STEP 3\n" + "Add the tomatoes and refried beans with 1 can water and the reserved pepper juice. Simmer for 1 hr until thickened, smoky and the tomato chunks have broken down to a smoother sauce.\n" + "\n" + "STEP 4\n" + "At this stage you can cool and chill the sauce if making ahead. Otherwise add the kidney and black beans, and the remaining roasted peppers, cut into bite-sized pieces, then reheat. (This makes a large batch, so once the sauce is ready it might be easier to split it between two pans when you add the beans and peppers.) Once bubbling and the beans are hot, season to taste and serve.\n");
        writeRecipeDetails("Chicken Curry", "6 tbsp tandoori masala powder\n" + "2 tbsp ginger paste\n" + "2 tbsp garlic paste\n" + "500g pot coconut yogurt, plus extra to serve\n" + "4 onions , 2 cut into wedges, 2 chopped\n" + "2 peppers , cut into large chunks\n" + "1 ¼kg skinless chicken thigh , cut into large chunks\n" + "1 tsp olive oil\n" + "big bunch coriander , stalks chopped\n" + "2-3 tsp hot chilli powder\n" + "680ml jar passata\n" + "160ml can coconut cream\n" + "rice and naan bread , to serve", "STEP 1\n" + "In a very large bowl, mix half the spice mix, 1 tbsp each of the pastes, three-quarters of the yogurt and some salt. Stir in the onion wedges, peppers and chicken, then leave to marinate overnight in the fridge.\n" + "\n" + "STEP 2\n" + "Fry the chopped onions in the oil until really soft. Add the remaining spice mix and pastes, the coriander stalks and chilli powder. Cook for 1 min, then stir in 100ml water and the passata. Simmer for 15 mins, then add the coconut cream and remaining yogurt. Leave chunky or blitz with a blender.\n" + "\n" + "STEP 3\n" + "Heat oven to 220C/200C fan/gas 7. Spread the chicken and veg on a baking tray. Roast for 15-20 mins until cooked and slightly charred. Tip into the sauce. Serve with rice, naan, extra yogurt and coriander leaves.\n" + "\n");
        writeRecipeDetails("Piri-piri Chicken & Spicy Rice", "4 skin-on chicken thighs and 4 drumsticks- buy a mixed pack\n" + "6 tbsp piri-piri marinade (you can buy mild, medium or hot)\n" + "1 tbsp sunflower oil\n" + "2 peppers , any colour, deseeded and finely chopped\n" + "½ bunch spring onions , sliced, white and green parts separated\n" + "4 tbsp tomato purée\n" + "1 tbsp sweet smoked paprika\n" + "250g cooked rice\n" + "vegetables or salad , to serve (optional)", "STEP 1\n" + "Heat oven to 200C/180C fan/gas 6. Slash each piece of chicken 3 times, so the marinade can really flavour the meat. Pour over the sauce and leave in the fridge to marinate, if you have time. If not, mix well and arrange, skin-side up, in a roasting tin. Cook for 30 mins, then increase heat to 220C/200C fan/gas 7 and cook for about 15 mins more until the skin is crispy and golden.\n" + "\n" + "STEP 2\n" + "When the chicken is almost ready, heat the oil in a frying pan. Cook the peppers and white parts of the spring onions for 5 mins. Tip in the purée and paprika, stir, then add the rice, breaking up with a wooden spoon so all the grains are coated well. Use a high heat and scrape any that sticks off the bottom so you get some soft and some crispy parts. Heat until piping hot. Scatter the green parts of the spring onion on top and serve with the chicken, and some vegetables or salad, if you like.\n");

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            // Retrieve info of selected recipe and username of the current session
            String username = bundle.getString("username");
            String recipeName = bundle.getString("recipeName");
            Integer recipeUrl = bundle.getInt("recipeUrl");
            Integer duration = bundle.getInt("duration");
            String cuisine = bundle.getString("cuisine");
            String diet = bundle.getString("diet");

            // Show recipe info
            nameTextView.setText(recipeName);
            recipeImageView.setImageResource(recipeUrl);
            durationTextView.setText(duration.toString());
            cuisineTextView.setText(cuisine);
            dietTextView.setText(diet);

            // Query selected recipe's ingredients and method
            Query recipeDetailsQuery = FirebaseDatabase.getInstance().getReference("RECIPES")
                    .child(recipeName).child("recipeDetails");

            // Read ingredients from the database
            recipeDetailsQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String ingredients = dataSnapshot.child("ingredients").getValue(String.class);
                    ingredientsTextView.setText(ingredients);
                    Log.d(TAG, "Ingredients are: " + ingredients);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            // Read method from the database
            recipeDetailsQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String method = dataSnapshot.child("method").getValue(String.class);
                    methodTextView.setText(method);
                    //setShareActionIntent(method);

                    Log.d(TAG, "Ingredients are: " + method);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

            // Set save recipe button
            final Button buttonSaveRecipe = findViewById(R.id.button_save_recipe);
            buttonSaveRecipe.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    db.saveRecipe(username, recipeName);
                    Log.d(TAG, recipeName + " saved to username: " + username);
                }
            });
        }
        else{ Log.d(TAG, "Bundle is empty"); }
    }

    // Add recipe's ingredients and method to Firebase
    public void writeRecipeDetails(String recipeName, String ingredients, String method) {
        Recipe recipe = new Recipe(ingredients, method);
        recipesRef.child(recipeName).child("recipeDetails").setValue(recipe);
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

    public void goToTesco(View view) {
        Uri uriUrl = Uri.parse("https://www.tesco.com/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
