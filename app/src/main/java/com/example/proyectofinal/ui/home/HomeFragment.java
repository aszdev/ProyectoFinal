package com.example.proyectofinal.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.R;
import com.example.proyectofinal.clases.funciones;
import com.example.proyectofinal.databinding.FragmentHomeBinding;
import com.example.proyectofinal.ui.gallery.GalleryViewModel;

public class HomeFragment extends Fragment {

    List<ListElement> elements;
    private GalleryViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.listRecyclerview;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);

                elements = new ArrayList<>();

                funciones fn = new funciones();

                elements.add(new ListElement("#775447", "Pedro", "Mexico", "Activo"));
                elements.add(new ListElement("#607d8b", "Julio", "Mixco", "Activo"));
                elements.add(new ListElement("#f44336", "Jose", "VillaNueva", "Activo"));
                elements.add(new ListElement("#009688", "Jesica", "Guatemla", "Inactivo"));
                elements.add(new ListElement("#775447", "Pedro", "Mexico", "Activo"));
                elements.add(new ListElement("#607d8b", "Julio", "Mixco", "Activo"));
                elements.add(new ListElement("#f44336", "Jose", "VillaNueva", "Activo"));
                elements.add(new ListElement("#009688", "Jesica", "Guatemla", "Inactivo"));
                elements.add(new ListElement("#775447", "Pedro", "Mexico", "Activo"));
                elements.add(new ListElement("#607d8b", "Julio", "Mixco", "Activo"));
                elements.add(new ListElement("#f44336", "Jose", "VillaNueva", "Activo"));
                elements.add(new ListElement("#009688", "Jesica", "Guatemla", "Inactivo"));
                elements.add(new ListElement("#775447", "Pedro", "Mexico", "Activo"));
                elements.add(new ListElement("#607d8b", "Julio", "Mixco", "Activo"));
                elements.add(new ListElement("#f44336", "Jose", "VillaNueva", "Activo"));
                elements.add(new ListElement("#009688", "Jesica", "Guatemla", "Inactivo"));
                ListAdapter listAdapter = new ListAdapter(elements, getContext(), new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ListElement item) {
                        moveToDescription(item); //le pasamos el item que le diamos click
                    }
                });

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(listAdapter);


            }
        });

        return root;
    }
    //damos click y que nos pase a la otra actividad con los valores asigandos
    public void moveToDescription(ListElement item){
        Intent intent = new Intent(getContext(), DescriptionActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}


