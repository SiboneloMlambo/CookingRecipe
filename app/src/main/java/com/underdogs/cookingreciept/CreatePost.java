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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationBarView;
import com.underdogs.cookingreciept.Helper.TimePickerFragment;
import com.underdogs.cookingrecipe.R;

import java.io.IOException;
import java.util.Calendar;

public class CreatePost extends AppCompatActivity {

    EditText title;
    EditText description;
    Spinner ingredients;
    Spinner food_category;
    Button submit;
    ImageButton select_image;
    Button set_time;
    private int  mHour, mMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_post);

        //title = (EditText) findViewById(R.id.name);
        //description = (EditText) findViewById(R.id.Instructions);
        //submit = (Button) findViewById(R.id.submit);
        select_image = (ImageButton) findViewById(R.id.add_photo_button);
        set_time = (Button) findViewById(R.id.Time_button);

       set_time.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SelectTime();
           }

       });

        select_image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ImageSelector();
            }
        });

        Spinner ingredients = (Spinner) findViewById(R.id.ingredients);
            // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Ingredients_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        ingredients.setAdapter(adapter);



        Spinner food_category = (Spinner) findViewById(R.id.CategoryList);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> food_category_adapter = ArrayAdapter.createFromResource(
                this,
                R.array.food_category_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        food_category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        food_category.setAdapter(food_category_adapter);


    }

    private void SelectTime() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        set_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }

    //source :- https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
    private void ImageSelector() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }
    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null && data.getData() != null) {
                        ImageButton imageView = (ImageButton) findViewById(R.id.add_photo_button);
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap = null;

                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                        }
                        catch (IOException e) {
                            String message = e.getMessage();
                        }
                        imageView.setImageBitmap(selectedImageBitmap);


                    }
                }
            });
}