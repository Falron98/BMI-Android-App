package com.example.bmi_calc

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class Recipes : AppCompatActivity() {
    private lateinit var recipeNames: Array<String>
    private var recipeMap: MutableMap<String, Array<String>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        // Get the recipe names and their corresponding ingredients and directions from the string resources
        recipeNames = resources.getStringArray(R.array.list_of_recipes)
        val ingredients = resources.getStringArray(R.array.recipe_ingredients)
        val directions = resources.getStringArray(R.array.recipe_directions)

        // Create a map that links each recipe name to its corresponding ingredients and directions
        recipeMap = HashMap()
        for (i in recipeNames.indices) {
            val recipeName = recipeNames[i]
            val recipeIngredients = ingredients[i].split("\\r?\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val recipeDirections = directions[i].split("\\r?\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            (recipeMap as HashMap<String, Array<String>>)[recipeName] = arrayOf(TextUtils.join("\n", recipeIngredients), TextUtils.join("\n", recipeDirections))
        }

        // Set up the ListView to display the recipe names
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeNames)
        val listView = findViewById<ListView>(R.id.ListView_recipes)
        listView.adapter = adapter

        // Set an item click listener for the ListView to launch the Recipe activity and pass the selected recipe's ingredients and directions
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val recipeName = recipeNames[position]
            val recipeData = (recipeMap as HashMap<String, Array<String>>).get(recipeName)!!
            val intent = Intent(this@Recipes, Recipe::class.java)
            intent.putExtra("recipe_name", recipeName)
            intent.putExtra("recipe_ingredients", recipeData[0])
            intent.putExtra("recipe_directions", recipeData[1])
            startActivity(intent)
        }
    }
}