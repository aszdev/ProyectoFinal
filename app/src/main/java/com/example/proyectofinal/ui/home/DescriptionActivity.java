package com.example.proyectofinal.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.R;
import com.example.proyectofinal.clases.Globales;
import com.example.proyectofinal.modelos.ModeloPedidosVentas;
import com.example.proyectofinal.servicios.wsVentasTienda;


import org.apache.http.io.SessionOutputBuffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity {
    TextView titleDescriptionTextview;
    TextView cityDescriptionTextview;
    TextView statusDescriptionTextview;
    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description3);

//contiene la infor de la lista que presionamos
        ListElement element = (ListElement)  getIntent().getSerializableExtra("ListElement");

        String idlogin = (String)  getIntent().getSerializableExtra("idlogin");

        System.out.println("ID LOGIN 1: "+idlogin);

        System.out.println("Varible Global user: "+ Globales.gidusuario);

        titleDescriptionTextview = findViewById(R.id.titleDescriptionTextview);
        cityDescriptionTextview = findViewById(R.id.cityDescriptionTextview);
        statusDescriptionTextview = findViewById(R.id.statusDescriptionTextview);

        titleDescriptionTextview.setText(element.getName());
        titleDescriptionTextview.setTextColor(Color.parseColor(element.getColor()));

        cityDescriptionTextview.setText(element.getCity());

        statusDescriptionTextview.setText(element.getStatus());
        statusDescriptionTextview.setTextColor(Color.GRAY);


        iniciarPedidos(element);


    }

    private void iniciarPedidos(ListElement element){
        RecyclerView tableLayout = findViewById(R.id.listRecyclerview2);

        elements = new ArrayList<>();


        wsVentasTienda wsventas = new wsVentasTienda();

        List lstiendas = wsventas.listarVentasPendiente(element.getId());
        ModeloPedidosVentas mprod = null;
        Iterator<ModeloPedidosVentas> itr = lstiendas.iterator();


        while (itr.hasNext()) {
            mprod = itr.next();

            elements.add(new ListElement("#EB4F15", "Nombre: " + mprod.getNOMBRE(),"Direccion: " + mprod.getDIRECCION() + " Telefono: " + mprod.getTELEFONO(),"Importe: " + String.valueOf(mprod.getIMPORTERECIBIDO()),String.valueOf(mprod.getIDVENTA())));
            // elements.add(new ListElement("#009688", mprod.getNombre(), mprod.getDireccion(),"Tel: " +  mprod.getTelefono(),mprod.getIdTienda()));
            System.out.println("ID VENTA: " + mprod.getIDVENTA() + " Nombre: " + mprod.getNOMBRE());



        }

        ListAdapter listAdapter = new ListAdapter(elements, DescriptionActivity.this, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                actualizarSeleccionado(item,element); //le pasamos el item que le diamos click
            }
        });

        tableLayout.setHasFixedSize(true);
        tableLayout.setLayoutManager(new LinearLayoutManager(DescriptionActivity.this));
        tableLayout.setAdapter(listAdapter);


    }

    private void actualizarSeleccionado(ListElement item,ListElement element){

        wsVentasTienda wsventas = new wsVentasTienda();

        boolean retorna = wsventas.actualizar(item.getId(),String.valueOf(Globales.gidusuario));

        Toast.makeText(getApplicationContext(), "ITEM SELECCIONADO >>> ID VENTA: " + item.getId() + " IDMENSAJERO: " + Globales.gidusuario, Toast.LENGTH_SHORT).show();


        iniciarPedidos(element);

    }

}