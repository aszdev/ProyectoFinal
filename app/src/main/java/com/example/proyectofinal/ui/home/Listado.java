package com.example.proyectofinal.ui.home;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.proyectofinal.R;

import java.util.ArrayList;
import java.util.List;

public class Listado extends AppCompatActivity {
    List<ListElement> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        init();
    }

    public void init(){
        elements = new ArrayList<>();
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
        ListAdapter listAdapter = new ListAdapter(elements, this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                moveToDescription(item); //le pasamos el item que le diamos click
            }
        });
        RecyclerView recyclerView = findViewById(R.id.listRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

    }
    //damos click y que nos pase a la otra actividad con los valores asigandos
    public void moveToDescription(ListElement item){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("ListElement", item);
        startActivity(intent);

    }
}
