package com.underdogs.cookingreciept;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.underdogs.cookingrecipe.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.android.volley.toolbox.JsonArrayRequest;


public class CreatePost extends AppCompatActivity {

    private Spinner food_category;
    private ImageButton select_image;
    private Spinner ingredientsSpinner;
    private List<String> ingredientsList = new ArrayList<>();
    private List<String> selectedIngredients;
    private Button set_time;
    private AutoCompleteTextView ingredientsAutoComplete;
    private ChipGroup chipGroup;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // Bind UI elements
        select_image = findViewById(R.id.add_photo_button);
        chipGroup = findViewById(R.id.chip_group);
        food_category = findViewById(R.id.CategoryList);
        ingredientsAutoComplete = findViewById(R.id.ingredients_multi);
        set_time = findViewById(R.id.Time_button);
        ingredientsSpinner = findViewById(R.id.ingredients);
        submit = findViewById(R.id.posts_button);

        // Set up Image Selector
        select_image.setOnClickListener(v -> ImageSelector());

        selectedIngredients = new ArrayList<>();

        // Set up the Time Picker
        set_time.setOnClickListener(v -> SelectTime());

        // Fetch ingredients from the server
        fetchIngredients();

        // Submit button click listener
        submit.setOnClickListener(v -> {
            if (!selectedIngredients.isEmpty()) {
                sendIngredientsToServer(selectedIngredients);
            } else {
                Toast.makeText(CreatePost.this, "Please select ingredients", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up initial UI state
        setupSpinners();
    }

    private void fetchIngredients() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lamp.ms.wits.ac.za/~s886289/get_ingredients.php?query=oi";

        // Request a JSON response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ingredientsList.clear(); // Clear previous data

                            // Loop through the JSON array and add ingredient names to ingredientsList
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject ingredientObject = response.getJSONObject(i);
                                String ingredientName = ingredientObject.getString("Ingredient_Name");
                                ingredientsList.add(ingredientName);
                            }

                            // Update UI with the fetched ingredients
                            updateUIWithIngredients();

                        } catch (JSONException e) {
                            Log.e("FetchIngredients", "JSONException: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error cases
                        Log.e("FetchIngredients", "VolleyError: " + error.getMessage());
                        // Optionally, you can display an error message to the user
                        // or retry the request.
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }

    private void updateUIWithIngredients() {
        // Check if ingredientsList is not null and not empty
        if (ingredientsList != null && !ingredientsList.isEmpty()) {
            // Set up the Spinner for ingredients
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    ingredientsList
            );
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ingredientsSpinner.setAdapter(spinnerAdapter);

            // Set up AutoCompleteTextView for multi-select ingredients
            ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ingredientsList);
            ingredientsAutoComplete.setAdapter(autoCompleteAdapter);

            ingredientsAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
                String ingredient = autoCompleteAdapter.getItem(position);
                if (ingredient != null && !ingredient.isEmpty()) {
                    addChipToGroup(ingredient);
                    ingredientsAutoComplete.setText(""); // Clear the input
                }
            });

            // Set up the Spinner for food category
            ArrayAdapter<CharSequence> foodCategoryAdapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.food_category_array,
                    android.R.layout.simple_spinner_item
            );
            foodCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            food_category.setAdapter(foodCategoryAdapter);
        } else {
            // Handle the case where ingredientsList is empty or null
            Toast.makeText(this, "Ingredients list is empty", Toast.LENGTH_SHORT).show();
            // Optionally, you might fetch ingredients again or show a message to the user
        }
    }

    private void setupSpinners() {
        // Set up the Spinner for food category
        ArrayAdapter<CharSequence> foodCategoryAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.food_category_array,
                android.R.layout.simple_spinner_item
        );
        foodCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        food_category.setAdapter(foodCategoryAdapter);
    }

    private void SelectTime() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> set_time.setText(hourOfDay + ":" + minute),
                mHour, mMinute, true);
        timePickerDialog.show();
    }

    private void ImageSelector() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        ImageButton imageView = findViewById(R.id.add_photo_button);
                        Uri selectedImageUri = data.getData();
                        try {
                            Bitmap selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            imageView.setImageBitmap(selectedImageBitmap);
                        } catch (IOException e) {
                            Log.e("ImageSelector", "Error selecting image", e);
                        }
                    }
                }
            });

    private void addChipToGroup(String text) {
        Chip chip = new Chip(this);
        chip.setText(text);
        chip.setCloseIconVisible(true);
        chip.setOnCloseIconClickListener(view -> chipGroup.removeView(chip));
        chipGroup.addView(chip);
        selectedIngredients.add(text); // Add to selected ingredients list
    }

    private void sendIngredientsToServer(List<String> selectedIngredients) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://lamp.ms.wits.ac.za/home/s536187/ingredients1.php"; // Replace with your actual server URL

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("ingredients", new JSONArray(selectedIngredients));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("SendIngredients", "Response: " + response.toString());
                        Toast.makeText(CreatePost.this, "Ingredients sent!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("SendIngredients", "Error: " + error.toString());
                        Toast.makeText(CreatePost.this, "Error sending ingredients", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }
}
