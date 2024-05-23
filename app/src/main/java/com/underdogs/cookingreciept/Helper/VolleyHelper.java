package com.underdogs.cookingreciept.Helper;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.underdogs.cookingreciept.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VolleyHelper {

    private Context context;

//    private void fetchData() {
//        String url = "https://khethelihle-ireland.s3.eu-west-1.amazonaws.com/api/recipe.json?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjELT%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCmFwLXNvdXRoLTEiRjBEAiAnXqczZIpjeNgSz3FGNenS8TpQsE1As21w4%2BxrSKa7%2BAIgdazWF88lJsxE1ddTuvH6oDp0sphptbsX4CIf82aTQVoq5gIILRAEGgw5ODU3MDE4MTk2MTEiDHYUXabm6IjTya3L8SrDAn%2Bev0I%2B%2FEylFCrfgEgzg3ZGeI8tY%2BtYfG8qhOVCyDl9t1d4xnb3wq6klWAoJCb9ZrB8Se2c9rF3eCEd6qMAetzDCgQMRb9KZjOTrq9AjQF30uKOldwupYPYoHRMwBd%2FA0mlUKlTEGke6pLPI79xmZ1YZO%2FkUZqCoop7pubWyp9j9tiRfLwGcR1lHEIHWnG4SUTGaJXOzq45f8ctvuW24EAhr9vrd%2FYyxBC7w3epIKpRFLLGPSErf3Qlw6YfMMhVcWWihWrTG3Y7PftP7EsWnTaPM125H4gZwSSiXifKRD92iKB9ocfdESrAhWdfKYNHQPjYSfUbVY3Zn8tpbonlwkTKPpLcK74GjVJHwEp%2BiVr0zQlWyTMYQj7%2Fz4lFakD505zFLgnq%2FjPGkXQ7BM4%2Bxw80sd6rPGyRHKCXg%2FrGeyIMaAy4MPf3rLIGOogCRrlABREwHRvUI2b0OuA4oty4azVsJmkWOTDUEHhpdZdE6GQ9CwP1u09E3vdaiD6K7Pp3gRnnRlKM%2BH97qcZHlzKIR6Obg4dMWtCrBBYDZheneSHZjTmk2DMVClA4dkEUuMmvPeQyp7V6X6Oi9C08pDLT1vUnRxOr84wLNfgoNs6QMtjqj6IphWMA%2FNaK6fGXa2EfHED9Tkbbr6oJyW5jiH1uLHT8O5wRyi5WcBMRxA8dISuujfuoMY3TkbTKMOk5MHJnJ%2FUn0DcDX2MVis6o8K6CWJR9inARFKUrrAA3KwROLPquF1T%2F4kwM%2FcowEC4JkSIRRl08nIUDimWB3HfWmJLDvzjI9w5s&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240520T120421Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIA6LADIETNY6L5HZJC%2F20240520%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=20bf4af0ae0630bb06c846cf1d555e21f8fdbda0e59ce51725b69fd9675786b9";
//
//        StringRequest request = new StringRequest(Request.Method.GET, url,
//                response -> parseData(response),
//                error -> {
//                    VolleyError error1 = error;
//                    error1.printStackTrace();
//                });
//
//        Volley.newRequestQueue(getContext()).add(request);
//    }
//
//    private void parseData(String response) {
//        try {
//            JSONArray jsonArray = new JSONArray(response);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                Recipe recipe = new Recipe(
//                        jsonObject.getString("email"),
//                        jsonObject.getString("first_name"),
//                        jsonObject.getString("name"),
//                        Uri.parse(jsonObject.getString("image")),
//                        jsonObject.getString("ingredients"),
//                        jsonObject.getString("instructions"),
//                        jsonObject.getString("time")
//                );
//                recipes.add(recipe);
//            }
//            //adapter.notifyDataSetChanged();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }


}
