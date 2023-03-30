package com.example.bmi_calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Recipe extends AppCompatActivity {

    private TextView recipeNameTextView;
    private TextView ingredientsTextView;
    private TextView directionsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Get the recipe name, ingredients, and directions from the intent extras
        Intent intent = getIntent();
        String recipeName = intent.getStringExtra("recipe_name");
        String ingredients = intent.getStringExtra("recipe_ingredients");
        String directions = intent.getStringExtra("recipe_directions");

        // Set up the TextViews to display the recipe name, ingredients, and directions
        recipeNameTextView = findViewById(R.id.textView_recipe_name);
        recipeNameTextView.setText(recipeName);

        ingredientsTextView = findViewById(R.id.textView_recipe_ingredients);
        ingredientsTextView.setText(ingredients);

        directionsTextView = findViewById(R.id.textView_recipe_directions);
        directionsTextView.setText(directions);
    }
}

