package com.example.proyectofinal.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.proyectofinal.R;

public class DescriptionActivity extends AppCompatActivity {
    TextView titleDescriptionTextview;
    TextView cityDescriptionTextview;
    TextView statusDescriptionTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description2);

//contiene la infor de la lista que presionamos
        ListElement element = (ListElement)  getIntent().getSerializableExtra("ListElement");
        titleDescriptionTextview = findViewById(R.id.titleDescriptionTextview);
        cityDescriptionTextview = findViewById(R.id.cityDescriptionTextview);
        statusDescriptionTextview = findViewById(R.id.statusDescriptionTextview);

        titleDescriptionTextview.setText(element.getName());
        titleDescriptionTextview.setTextColor(Color.parseColor(element.getColor()));

        cityDescriptionTextview.setText(element.getCity());

        statusDescriptionTextview.setText(element.getStatus());
        statusDescriptionTextview.setTextColor(Color.GRAY);
    }
}