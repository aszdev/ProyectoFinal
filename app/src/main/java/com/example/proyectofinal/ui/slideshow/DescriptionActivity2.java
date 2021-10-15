package com.example.proyectofinal.ui.slideshow;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.modelos.ModeloListarTiendas;
import com.example.proyectofinal.modelos.ModeloReporteVentas;
import com.example.proyectofinal.servicios.wsVentasTienda;
import com.example.proyectofinal.ui.home.ListAdapter;
import com.example.proyectofinal.ui.home.ListElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class DescriptionActivity2 extends AppCompatActivity {
    TextView titleDescriptionTextview;
    TextView cityDescriptionTextview;
    TextView statusDescriptionTextview;
    TextView statusDescriptionID;
    TextView tvDate;
    TextView etDate;
    DatePickerDialog.OnDateSetListener setListener;
    Button btnGenerar;
    List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description2);

//contiene la infor de la lista que presionamos
        ListElement element = (ListElement)  getIntent().getSerializableExtra("ListElement2");
        titleDescriptionTextview = findViewById(R.id.titleDescriptionTextview);
        cityDescriptionTextview = findViewById(R.id.cityDescriptionTextview);
        statusDescriptionTextview = findViewById(R.id.statusDescriptionTextview);
        statusDescriptionID= findViewById(R.id.statusDescriptionID);
        titleDescriptionTextview.setText(element.getName());
        titleDescriptionTextview.setTextColor(Color.parseColor(element.getColor()));


        cityDescriptionTextview.setText(element.getCity());

        statusDescriptionTextview.setText(element.getStatus());
        statusDescriptionTextview.setTextColor(Color.GRAY);

        statusDescriptionID.setText(element.getId());

        tvDate = findViewById(R.id.tv_date);
        etDate = findViewById(R.id.et_Date);
        btnGenerar = findViewById(R.id.GenerarReporte);
        RecyclerView tableLayout = findViewById(R.id.listRecyclerview2);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        DescriptionActivity2.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;

                        String date = year+"/"+month+"/"+day;
                        tvDate.setText(date);

                    }
                }, year,month,day);
                datePickerDialog.show();

            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicketDialog = new DatePickerDialog(
                        DescriptionActivity2.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = year+"/"+month+"/"+day;
                        etDate.setText(date);

                    }
                }, year,month,day);
                datePicketDialog.show();


            }
        });



        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                elements = new ArrayList<>();


                wsVentasTienda wsventas = new wsVentasTienda();

                List lstiendas = wsventas.listarVentas("2021-01-01","2021-10-25","0");
                ModeloReporteVentas mprod = null;
                Iterator<ModeloReporteVentas> itr = lstiendas.iterator();


                while (itr.hasNext()) {
                    mprod = itr.next();

                    elements.add(new ListElement("#EB4F15", "No Doc: " + mprod.getNumero_doc(),"Fecha Venta: " + mprod.getFecha_venta(),"Precio: " + String.valueOf(mprod.getTotal_venta()),mprod.getNumero_doc()));
                    // elements.add(new ListElement("#009688", mprod.getNombre(), mprod.getDireccion(),"Tel: " +  mprod.getTelefono(),mprod.getIdTienda()));
                    System.out.println("No Doc: " + mprod.getNumero_doc() + " Fecha Venta: " + mprod.getFecha_venta());



                }

                ListAdapter listAdapter = new ListAdapter(elements,DescriptionActivity2.this, new ListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ListElement item) {
                       // moveToDescription(item); //le pasamos el item que le diamos click
                    }
                });

                tableLayout.setHasFixedSize(true);
                tableLayout.setLayoutManager(new LinearLayoutManager(DescriptionActivity2.this));
                tableLayout.setAdapter(listAdapter);





            }
        });

    }
}