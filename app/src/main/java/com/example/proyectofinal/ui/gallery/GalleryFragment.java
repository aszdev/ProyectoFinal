package com.example.proyectofinal.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentGalleryBinding;
import com.example.proyectofinal.modelos.ModeloProductosTienda;
import com.example.proyectofinal.servicios.wsProductosTienda;

import java.util.Iterator;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
              new ViewModelProvider(this).get(GalleryViewModel.class);

       binding = FragmentGalleryBinding.inflate(inflater, container, false);
       View root = binding.getRoot();


     //  final TextView tfirstname = binding.textGallery;
       final TableLayout tableLayout = binding.tableLayout;
       final Spinner tiendasSpines = binding.tiendasSpinner;
      galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //tfirstname.setText(s);

            wsProductosTienda wsprod = new wsProductosTienda();
            List lsprod = wsprod.produtosTienda("0","");
            ModeloProductosTienda mprod =null;
            Iterator<ModeloProductosTienda> itr = lsprod.iterator();

            while (itr.hasNext()){
                mprod = itr.next();


                View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.table_item,null,false);
               // TextView nit  = (TextView) tableRow.findViewById(R.id.nittienda);
              //  TextView nombretienda  = (TextView) tableRow.findViewById(R.id.nombretienda);
               // TextView dirtienda  = (TextView) tableRow.findViewById(R.id.dirtienda);
                TextView codprod  = (TextView) tableRow.findViewById(R.id.codprod);
                TextView nomprod  = (TextView) tableRow.findViewById(R.id.descripcion);
                TextView stock  = (TextView) tableRow.findViewById(R.id.stock);
                TextView pcompra  = (TextView) tableRow.findViewById(R.id.preciocompra);
                TextView pventa  = (TextView) tableRow.findViewById(R.id.precioventa);

                //nit.setText(mprod.getNit());
              //  nombretienda.setText(mprod.getNomTienda());
               // dirtienda.setText(mprod.getDirTienda());
                codprod.setText(mprod.getCodprod());
                nomprod.setText(mprod.getDescripcion());
                stock.setText(String.valueOf(mprod.getStock()));
                pcompra.setText(String.valueOf(mprod.getPrecioCompra()));
                pventa.setText(String.valueOf(mprod.getPrecioVenta()));

                tableLayout.addView(tableRow);


            }

                tiendasSpines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });


            }
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}