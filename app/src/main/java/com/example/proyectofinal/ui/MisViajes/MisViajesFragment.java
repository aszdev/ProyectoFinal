package com.example.proyectofinal.ui.MisViajes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.clases.Globales;
import com.example.proyectofinal.databinding.FragmentHomeBinding;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloTiendaItem;
import com.example.proyectofinal.servicios.wsVentasTienda;
import com.example.proyectofinal.ui.gallery.GalleryViewModel;
import com.example.proyectofinal.ui.home.DescriptionActivity;
import com.example.proyectofinal.ui.home.ListAdapter;
import com.example.proyectofinal.ui.home.ListElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MisViajesFragment extends Fragment {

    List<ListElement> elements;
    private MisViajesViewModel homeViewModel;
    private FragmentHomeBinding binding;
    String loginid="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

try{
    SharedPreferences sharedPref = getActivity().getSharedPreferences("shared_login_data",Context.MODE_PRIVATE);
     loginid = sharedPref.getString("loginid","");
    System.out.println("Login ID FORMA 1: " + loginid);

}catch (Exception ex){

}

        System.out.println("Varible Global user: "+ Globales.gidusuario);


        homeViewModel =
                new ViewModelProvider(this).get(MisViajesViewModel.class);

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


