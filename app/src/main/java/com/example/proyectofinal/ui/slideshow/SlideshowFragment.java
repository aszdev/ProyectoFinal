package com.example.proyectofinal.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.clases.funciones;
import com.example.proyectofinal.clases.funciones2;
import com.example.proyectofinal.databinding.FragmentHomeBinding;
import com.example.proyectofinal.databinding.FragmentSlideshowBinding;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloProductosTienda;
import com.example.proyectofinal.servicios.wsProductosTienda;
import com.example.proyectofinal.servicios.wsVentasTienda;
import com.example.proyectofinal.ui.gallery.GalleryViewModel;
import com.example.proyectofinal.ui.home.DescriptionActivity;
import com.example.proyectofinal.ui.home.ListAdapter;
import com.example.proyectofinal.ui.home.ListElement;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SlideshowFragment extends Fragment {
    List<ListElement2> elements;
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView tableLayout = binding.listRecyclerview2;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {

                        //textView.setText(s);
                        elements = new ArrayList<>();

                        funciones2 fn = new funciones2();

                        wsVentasTienda wsventas = new wsVentasTienda();

                        List lstiendas = wsventas.listarTiendas();
                        ModeloListarTiendas mprod =null;
                        Iterator<ModeloListarTiendas> itr = lstiendas.iterator();

                        while (itr.hasNext()){
                            mprod = itr.next();


                            View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.table_item2,null,false);
                            TextView direccion  = (TextView) tableRow.findViewById(R.id.direccion);
                            TextView idtienda  = (TextView) tableRow.findViewById(R.id.idtienda);
                            TextView nombre  = (TextView) tableRow.findViewById(R.id.nombre);
                            TextView telefono  = (TextView) tableRow.findViewById(R.id.telefono);

                            direccion.setText(mprod.getDireccion());
                            idtienda.setText(mprod.getIdTienda());
                            nombre.setText(mprod.getNombre());
                            telefono.setText(mprod.getTelefono());
                            tableLayout.addView(tableRow);



                            elements.add(new ListElement2("#009688", "Jesica", "Guatemla", "Inactivo"));
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
