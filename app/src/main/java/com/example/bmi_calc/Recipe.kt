package com.example.bmi_calc

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Recipe : AppCompatActivity() {
    private lateinit var recipeNameTextView: TextView
    private lateinit var ingredientsTextView: TextView
    private lateinit var directionsTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Get the recipe name, ingredients, and directions from the intent extras
        val intent = intent
        val recipeName = intent.getStringExtra("recipe_name")
        val ingredients = intent.getStringExtra("recipe_ingredients")
        val directions = intent.getStringExtra("recipe_directions")

        // Set up the TextViews to display the recipe name, ingredients, and directions
        recipeNameTextView = findViewById(R.id.textView_recipe_name)
        recipeNameTextView.setText(recipeName)
        ingredientsTextView = findViewById(R.id.textView_recipe_ingredients)
        ingredientsTextView.setText(ingredients)
        directionsTextView = findViewById(R.id.textView_recipe_directions)
        directionsTextView.setText(directions)
    }
}