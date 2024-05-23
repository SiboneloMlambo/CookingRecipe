package com.underdogs.cookingreciept.ui.home;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.underdogs.cookingreciept.CreatePost;
import com.underdogs.cookingreciept.Helper.Recipe;
import com.underdogs.cookingreciept.Helper.RecyclerViewAdapter;
import com.underdogs.cookingrecipe.R;
import com.underdogs.cookingrecipe.databinding.FragmentHomeBinding;
import com.underdogs.cookingrecipe.databinding.FragmentNotificationsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView response_recycler_view;
    private List<Recipe> jsonResponses = new ArrayList<>();

    private RecyclerViewAdapter adapter;

    public Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.PostRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.PostRecyclerView.setAdapter(new RecyclerViewAdapter(homeViewModel.getRecipes()));
        binding.PostRecyclerView.setHasFixedSize(true);

        Log.d("Response", homeViewModel.getRecipes().toString());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePost.class);
                startActivity(intent);

            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
//        https://nabeelj.medium.com/populating-a-recyclerview-with-volley-response-data-b205ee4e647d
    }
}