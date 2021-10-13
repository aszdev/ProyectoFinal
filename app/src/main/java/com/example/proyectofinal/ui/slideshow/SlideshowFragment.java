package com.example.proyectofinal.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.R;
import com.example.proyectofinal.databinding.FragmentSlideshowBinding;
import com.example.proyectofinal.modelos.ModeloProductosTienda;
import com.example.proyectofinal.servicios.wsProductosTienda;
import com.example.proyectofinal.servicios.wsVentasTienda;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Iterator;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        final TextInputLayout editText = binding.FechafinTextField;

        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);


                wsVentasTienda wsventas = new wsVentasTienda();
                List lstiendas = wsventas.listarTiendas();
                ModeloProductosTienda mprod =null;
                Iterator<ModeloProductosTienda> itr = lstiendas.iterator();

                while (itr.hasNext()){
                    mprod = itr.next();


                    View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.table_item,null,false);
                    TextView nit  = (TextView) tableRow.findViewById(R.id.nittienda);
                    TextView nombretienda  = (TextView) tableRow.findViewById(R.id.nombretienda);
                    TextView history_display_orderid  = (TextView) tableRow.findViewById(R.id.history_display_orderid);
                    TextView history_display_quantity  = (TextView) tableRow.findViewById(R.id.history_display_quantity);

                    nit.setText(mprod.getNit());
                    nombretienda.setText(mprod.getNomTienda());
                    history_display_orderid.setText("S0");
                    history_display_quantity.setText("");
                   // tableLayout.addView(tableRow);


                }







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

