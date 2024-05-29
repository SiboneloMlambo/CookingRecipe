
/*package com.underdogs.cookingreciept.ui.PostPage;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class PostPage {

    private Context context;

    public PostPage(Context context) {
        this.context = context;
    }

    public void sendIngredientsToServer(List<String> selectedIngredients) {
        // Create a request queue using Volley
        RequestQueue queue = Volley.newRequestQueue(context);

        // URL of your PHP script on the server
        String url = "https://example.com/post_ingredients.php"; // Replace with your actual server URL

        // Create a JSON object to hold the ingredients data
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("ingredients", new JSONArray(selectedIngredients));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a request to POST data to the server
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        Log.d("SendIngredients", "Response: " + response.toString());
                        // Optionally handle response UI or actions
                        Toast.makeText(context, "Ingredients sent!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e("SendIngredients", "Error: " + error.toString());
                        Toast.makeText(context, "Error sending ingredients", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queue.add(request);
    }
}
*/