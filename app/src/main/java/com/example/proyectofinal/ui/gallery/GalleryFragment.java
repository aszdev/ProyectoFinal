package com.example.proyectofinal.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentGalleryBinding;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloProductosTienda;
import com.example.proyectofinal.modelos.ModeloTiendaItem;
import com.example.proyectofinal.servicios.wsProductosTienda;
import com.example.proyectofinal.servicios.wsVentasTienda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    private String itemSelect;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
              new ViewModelProvider(this).get(GalleryViewModel.class);

       binding = FragmentGalleryBinding.inflate(inflater, container, false);
       View root = binding.getRoot();


     //  final TextView tfirstname = binding.textGallery;
       final TableLayout tableLayout = binding.tableLayout;
       final Spinner tiendasSpines = binding.tiendasSpinner;
       final Button btnGen = binding.btnGenear;

      galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //tfirstname.setText(s);


                ArrayList<ModeloTiendaItem> tiendasList = new ArrayList<>();
                //Add countries

                wsVentasTienda wsTienda = new wsVentasTienda();
                List lsti =wsTienda.listarTiendas();
                ModeloListarTiendas mtienda =null;
                Iterator<ModeloListarTiendas> itr = lsti.iterator();

                while (itr.hasNext()){
                    mtienda = itr.next();

                    tiendasList.add(new ModeloTiendaItem(mtienda.getIdTienda(), mtienda.getNombre()));

                }

                //fill data in spinner
                ArrayAdapter<ModeloTiendaItem> adapter = new ArrayAdapter<ModeloTiendaItem>(getContext(), android.R.layout.simple_spinner_dropdown_item, tiendasList);
                tiendasSpines.setAdapter(adapter);
                //tiendasSpines.setSelection(adapter.getPosition(myItem));//Optional to set the selected item.
                tiendasSpines.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ModeloTiendaItem tienda = (ModeloTiendaItem) parent.getSelectedItem();
                       Toast.makeText(getContext(), "Tienda ID: "+tienda.getId()+",  Tienda Name : "+tienda.getName(), Toast.LENGTH_SHORT).show();
                        itemSelect = tienda.getId();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                btnGen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        wsProductosTienda wsprod = new wsProductosTienda();
                        List lsprod = wsprod.produtosTienda(itemSelect,"");
                        ModeloProductosTienda mprod =null;
                        Iterator<ModeloProductosTienda> itr = lsprod.iterator();

                        tableLayout.removeAllViews();

                        //se agrega el header de la tabla
                        View tableRowHeder = LayoutInflater.from(getContext()).inflate(R.layout.table_item_header,null,false);
                        tableLayout.addView(tableRowHeder);


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


                        Toast.makeText(getContext(), "Reporte Generado", Toast.LENGTH_SHORT).show();
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