package com.underdogs.cookingreciept.ui.home;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.underdogs.cookingreciept.Helper.Recipe;
import com.underdogs.cookingreciept.Helper.VolleyHelper;
import com.underdogs.cookingreciept.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private List<Recipe> recipes;
    private Context context;

    public HomeViewModel(Application application) {
        super(application);

        fetchData();

        recipes = new ArrayList<>();
    }

    public void fetchData() {
        String url = "https://khethelihle-ireland.s3.eu-west-1.amazonaws.com/api/recipe.json?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIA6LADIETN7XWC5OF3%2F20240523%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Date=20240523T061634Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=a33b2579448ce3317690a91d9293259682c1e3eb39945c5a3900a38509380e8b";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> parseData(response),
                Throwable::printStackTrace);

        Volley.newRequestQueue(getApplication()).add(request);
    }

    private void parseData(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Recipe recipe = new Recipe(
                        jsonObject.getString("email"),
                        jsonObject.getString("first_name"),
                        jsonObject.getString("name"),
                        Uri.parse(jsonObject.getString("image")),
                        jsonObject.getString("ingredients"),
                        jsonObject.getString("instructions"),
                        jsonObject.getString("time")
                );
                recipes.add(recipe);
            }
            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }


}