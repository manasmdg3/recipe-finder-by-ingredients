package com.example.chefatyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class recipeOutput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_output);

        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.KEY);

        String url = "http://www.recipepuppy.com/api/?i="+msg;

        TextView textView = findViewById(R.id.result_output);
        //textView.setText(msg);

        //ListView myListView = findViewById(R.id.myList);


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    int len = jsonArray.length();
                    //ArrayList<String> recipes = new ArrayList<>();
                    String recipes[] = new String[len];
                    String str;

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject recipe = jsonArray.getJSONObject(i);
                        recipes[i] = recipe.getString("title");

                        str = recipe.getString("title");
                        textView.setText(str +"\n");
                    }

                  // textView.setText(recipes[0]);
                    //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes);
                    //myListView.setAdapter(arrayAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    textView.setText("Something went wrong");
                }
            }
             }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp", error.toString());
                textView.setText(error.toString());
            }



        });

        requestQueue.add(jsonObjectRequest);

    }
}