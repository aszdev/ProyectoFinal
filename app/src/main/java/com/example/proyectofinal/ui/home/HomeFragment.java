package com.example.proyectofinal.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal.R;
import com.example.proyectofinal.clases.funciones;
import com.example.proyectofinal.databinding.FragmentHomeBinding;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloTiendaItem;
import com.example.proyectofinal.servicios.wsVentasTienda;
import com.example.proyectofinal.ui.gallery.GalleryViewModel;

public class HomeFragment extends Fragment {

    List<ListElement> elements;
    private GalleryViewModel homeViewModel;
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String loginid = sharedPref.getString("loginid","0");


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


                ArrayList<ModeloTiendaItem> tiendasList = new ArrayList<>();
                //Add countries

                wsVentasTienda wsTienda = new wsVentasTienda();
                List lsti =wsTienda.listarTiendas();
                ModeloListarTiendas mtienda =null;
                Iterator<ModeloListarTiendas> itr = lsti.iterator();

                while (itr.hasNext()){
                    mtienda = itr.next();

                    elements.add(new ListElement("#607d8b", mtienda.getNombre(), mtienda.getDireccion(), "Tel: " + mtienda.getTelefono(), mtienda.getIdTienda()));

                }




                ListAdapter listAdapter = new ListAdapter(elements, getContext(), new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ListElement item) {
                        moveToDescription(item,loginid); //le pasamos el item que le diamos click
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
    public void moveToDescription(ListElement item,String idloging){
        Intent intent = new Intent(getContext(), DescriptionActivity.class);
        intent.putExtra("ListElement", item);
        intent.putExtra("idlogin", idloging);
        startActivity(intent);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}


