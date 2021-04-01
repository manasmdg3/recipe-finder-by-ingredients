package com.example.chefatyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY ="com.chefatyourservice.sayan.mdg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
    }

    public void getRecipe(View view)
    {
        Intent intent = new Intent(this, recipeOutput.class);

        EditText editText = findViewById(R.id.editTextTextPersonName);
        String ingredients = editText.getText().toString();
        intent.putExtra(KEY, ingredients);
        startActivity(intent);
    }

    public void about(View view)
    {
        Intent intent2 = new Intent(this, About.class);
        intent2.putExtra(KEY, " ");
        startActivity(intent2);
    }
}