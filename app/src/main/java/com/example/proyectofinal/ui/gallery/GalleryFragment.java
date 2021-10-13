package com.example.proyectofinal.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
      galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //tfirstname.setText(s);



                for (int i=0;i<5;i++){
                    View tableRow = LayoutInflater.from(getContext()).inflate(R.layout.table_item,null,false);
                    TextView history_display_no  = (TextView) tableRow.findViewById(R.id.nittienda);
                    TextView history_display_date  = (TextView) tableRow.findViewById(R.id.nombretienda);
                    TextView history_display_orderid  = (TextView) tableRow.findViewById(R.id.history_display_orderid);
                    TextView history_display_quantity  = (TextView) tableRow.findViewById(R.id.history_display_quantity);

                    history_display_no.setText("25689789654");
                    history_display_date.setText("Tienda 001");
                    history_display_orderid.setText("S0"+(i+1));
                    history_display_quantity.setText(""+(20+(i+1)));
                    tableLayout.addView(tableRow);
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