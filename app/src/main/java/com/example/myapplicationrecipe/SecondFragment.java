package com.example.myapplicationrecipe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationrecipe.Adapter.FoodRecyclAdapter;
import com.example.myapplicationrecipe.DAO.FoodDAO;
import com.example.myapplicationrecipe.Database.AppDataBase;
import com.example.myapplicationrecipe.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
   AppDataBase appDataBase;
RecyclerView recyclerView;
FoodDAO foodDAO ;
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
recyclerView = view.findViewById(R.id.recyclerList);
foodDAO = appDataBase.getAppDatabase(getContext()).foodDAO() ;
        System.out.println("jjsjjs"+foodDAO.getAll().size());

FoodRecyclAdapter foodRecyclAdapter = new FoodRecyclAdapter(foodDAO.getAll());
recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
recyclerView.setAdapter(foodRecyclAdapter);
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container , FirstFragment.class,null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}