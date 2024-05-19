package com.underdogs.cookingreciept.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.underdogs.cookingreciept.CreatePost;
import com.underdogs.cookingreciept.Helper.Recipe;
import com.underdogs.cookingreciept.Helper.RecyclerViewAdapter;
import com.underdogs.cookingrecipe.R;
import com.underdogs.cookingrecipe.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView response_recycler_view;

    private RecyclerViewAdapter adapter;
    private LinearLayoutManager llm;;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //ArrayList<Recipe> recipeArrayList = volleyGet();


        //RecyclerView.Adapter itemAdapter = new RecyclerView.Adapter();

        response_recycler_view = root.findViewById(R.id.Post_Recycler_view);
        response_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        volleyGet();


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePost.class);
                startActivity(intent);

            }
        });

        return root;
    }

    private void volleyGet() {
            String url = "https://khethelihle-ireland.s3.eu-west-1.amazonaws.com/api/recipes.json?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJb%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCWV1LXdlc3QtMSJIMEYCIQDAfOriEh0iJJp2Nz994WUYvh%2FSpl3MTioipbkRtbs5mAIhAIUau7DFgkCG0qQinrEZ%2FHeR%2FnP4E9MOswNd%2BZ2ZPw7xKu8CCP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQBBoMOTg1NzAxODE5NjExIgwbn6DughJGp6lxLgEqwwLmV26CWWtKNH%2BPGAZKCpfB64qmvayS%2Bimn1Pr8b2WSnLQ1OEMad1jf0B%2Fan2rYpzXBkHIl17KkiqajzZZrY1KUCNJtGicbTe%2Fi3PWxvq7TzMPsgCtlFFnBqL4CkKOL8Ke5AtsSSrvTU5BxZYhestg7G%2B7MXmu5QudrifHi1i84ZP7PmxqiaHVF0OCj2pf8m5B4Ft73XmN0TuDIvERG%2Fm8RBrOmjXE9yloOB04oJjR3o%2FExRRBv%2B5QMqgctWt4ti6HsIIuawqC4rwNJlXKkQ8U1mGVuXEfrgvDmK0JrKmwhNmvQ65ZRpj436FBSrkiT%2FXbYn7oTCl7QJ4gwl3FM06NeBph3zBNr%2FG9jhT93WHoT1CiZyrUBlrIJIVnph%2BNN9FFL7CSKI9AQuocwSh2KhlHYDYvwfGATVN0lWSGxX67juOM%2FSTCSnaSyBjqGAg%2Bt5rIK7EAxwDQsxfQWHNTE6Rvzs5bp%2Bg7%2B0D19Z8vj0%2F%2BEiPFH9SVVddyOJcadccBHeY8LD%2BHeuUASQeOuz778dpZI7R125YwjujS3DpqF7OutNJnhvzmlQ0l8aqtji2XY9y10RPn0lBOmepP8Wn9eEWMi2MGyM3zk%2BahVLvzeSdSu9ppNpWVga2Vxh9q%2BqoC8RMiWmiXAk%2Be%2BWEzPMtNh83RGNrzbGodjnXRzBXaJtDESpOJuOWkP8DWfI76aKzDkmWk3GuP3s2Gvg9Qs4dxNFUTRQqz7JgfmC86vIO1qJ%2FDkydFfRWn%2BjJLfZ24DuEmScCOJYC5v5qG%2BuCP7A%2BPpM9Mg%2BeU%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20240519T060647Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43200&X-Amz-Credential=ASIA6LADIETNXKL46QMI%2F20240519%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=d87caa5153928e44ec2ada3fb8bcd5b3c40c241f5e0d861c2f4633955e129722";
            List<Recipe> jsonResponses = new ArrayList<>();

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String email = jsonObject.getString("email");
                            String firstName = jsonObject.getString("first_name");
                            Uri image = Uri.parse(jsonObject.getString("image"));
                            String recipeName= jsonObject.getString("name");
                            String recipeIngredients= jsonObject.getString("ingredients");
                            String recipeInstructions= jsonObject.getString("instructions");
                            String time= jsonObject.getString("time");

                            jsonResponses.add(new Recipe(email,firstName,recipeName,image, recipeIngredients, recipeInstructions, time));

//                            response_recycler_view.setAdapter(new RecyclerViewAdapter(jsonResponses));
//                            adapter = new RecyclerViewAdapter(jsonResponses);
//                            response_recycler_view.setAdapter(adapter);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            requestQueue.add(jsonObjectRequest);
            adapter = new RecyclerViewAdapter(jsonResponses);
            response_recycler_view.setAdapter(adapter);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
//        https://nabeelj.medium.com/populating-a-recyclerview-with-volley-response-data-b205ee4e647d
    }
}