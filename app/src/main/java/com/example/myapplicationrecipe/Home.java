package com.example.myapplicationrecipe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplicationrecipe.Adapter.RandomRecipeAdapter;
import com.example.myapplicationrecipe.Listener.RandomRecipeResponseListener;
import com.example.myapplicationrecipe.Listener.RecipeClickListener;
import com.example.myapplicationrecipe.Models.Recipe;
import com.example.myapplicationrecipe.Models.Root;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressDialog dialog ;
    RequestManager manager ;
    RandomRecipeAdapter randomRecipeAdapter ;
    RecyclerView recyclerView ;
    List<Recipe> recipes =  new ArrayList<>();
    List<String> tags= new ArrayList<>();
    Spinner spinner ;
    SearchView searchView ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_main, null);
        // setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loding ....");
        manager = new RequestManager(getActivity());
        //manager.getRandomRecipes(randomRecipeResponseListener,tags);
        searchView = mView.findViewById(R.id.searchView_home);
        searchView.setQueryHint("search for something");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {tags.clear();
                tags.add(query);
                manager.getRandomRecipes(randomRecipeResponseListener, tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        recyclerView=(RecyclerView) mView.findViewById(R.id.recyclerList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        randomRecipeAdapter = new RandomRecipeAdapter(getActivity(),recipes,recipeClickListener);
        System.out.println(recipes.size());
        recyclerView.setAdapter(randomRecipeAdapter);
        //spinner selected
        spinner = mView.findViewById(R.id.spinner_tags);
        spinner.setOnItemSelectedListener(sprinnerSelectedListe);
        //dialog.show();
        return  mView;
    }
    private  final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(Root response, String mesg) {
            recipes = response.recipes;
            randomRecipeAdapter = new RandomRecipeAdapter(getActivity(),recipes,recipeClickListener);
            System.out.println(recipes.size());
            recyclerView.setAdapter(randomRecipeAdapter);
            dialog.dismiss();
        }

        @Override
        public void didError(String mesg) {
            Toast.makeText(getActivity(),mesg,Toast.LENGTH_LONG);
        }

    };
    //    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Loding ....");
//        manager = new RequestManager(this);
//        //manager.getRandomRecipes(randomRecipeResponseListener,tags);
//        searchView = findViewById(R.id.searchView_home);
//        searchView.setQueryHint("search for something");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {tags.clear();
//                tags.add(query);
//                manager.getRandomRecipes(randomRecipeResponseListener, tags);
//                dialog.show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        recyclerView=(RecyclerView) findViewById(R.id.recyclerList);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,1));
//        randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this,recipes,recipeClickListener);
//        System.out.println(recipes.size());
//        recyclerView.setAdapter(randomRecipeAdapter);
//        //spinner selected
//        spinner = findViewById(R.id.spinner_tags);
//        spinner.setOnItemSelectedListener(sprinnerSelectedListe);
//        //dialog.show();
//
//
//    }
    private final AdapterView.OnItemSelectedListener sprinnerSelectedListe= new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tags.clear();
            tags.add(parent.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListener,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {
            startActivity(new Intent(getActivity(),RecipeDetailsActivity.class).putExtra("id",id) );
        }
    };
}