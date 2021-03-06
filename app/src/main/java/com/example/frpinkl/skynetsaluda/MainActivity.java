package com.example.frpinkl.skynetsaluda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    //creo un arraylist para el spinner que utilizaré posteriormente
    ArrayList<String> spinnerArrayList;

    RadioButton botonHola, botonAdios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // botonHola = (RadioButton) findViewById(R.id.radioHola);
       // botonAdios = (RadioButton) findViewById(R.id.radioAdios);




        //llamo al spinner
        final Spinner spinner = (Spinner)findViewById(R.id.spinner);


        //creo un arraylist
        spinnerArrayList = new ArrayList<String>();

        //añado el HOLA y ADIÓS al arraylist. Serán los valores a escoger dentro del spinner
        spinnerArrayList.add("HOLA");
        spinnerArrayList.add("ADIÓS");


        //creo el adaptador del arraylist
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerArrayList);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);









        //recogemos elementos del layout y les damos funcionalidad
        //hay que recordar hacer el cast (en este caso a tipo Button)
        Button boton = (Button)findViewById(R.id.btnHola);

        //una vez tenemos la referencia hecha ahora pondremos el código a ejecutar
        //cuando pulsemos el botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //código que se ejecuta cuando se pulse
                EditText text = (EditText)findViewById(R.id.entry);

                //vamos a ver si se introdujo nombre

                if("".equals(text.getText().toString().trim())){
                    //showAlert();
                    showToast();
                    return;

                }
                //ahora que tenemos el objeto EditText podemos recuperar su valor
                String saludo="";
                String nombreIntroducido = text.getText().toString();
                saludo = saludo + spinner.getSelectedItem()+" ";

                /*if(botonHola.isChecked()){
                    saludo = "HOLA ";
                } else if (botonAdios.isChecked()){
                    saludo = "ADIÓS ";
                }
*/


                //String saludo = getResources().getString(R.string.saludoSkynet) + " " + nombreIntroducido;
                //ahora empezamos con el RadioGroup
                RadioGroup radio = (RadioGroup)findViewById(R.id.RadioGroup01);
                if (R.id.radioSr == radio.getCheckedRadioButtonId()){
                    saludo=saludo+ "Señor "+text.getText();
                }
                else{
                    saludo=saludo+ "Señora "+text.getText();
                }
                //saludo=getResources().getString(R.string.saludoSkynet)+" "+saludo+" "+nombreIntroducido;

                //vamos a por la hora y la fecha
                CheckBox timeCheckBox = (CheckBox)findViewById(R.id.checkBox);
                if (timeCheckBox.isChecked()){
                    DatePicker date = (DatePicker)findViewById(R.id.datePicker);
                    String dateToShow = date.getDayOfMonth()+"/"+(date.getMonth()+1)+"/"+date.getYear();
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    dateToShow += " "+time.getCurrentHour()+":"+time.getCurrentMinute();
                    saludo +=" "+dateToShow;
                }


                //ahora escribimos el saludo en el TextView de salida
                //TextView out = (TextView)findViewById(R.id.out);
                //out.setText(saludo);
                Intent intent = new Intent(MainActivity.this, Salutation.class);
                intent.putExtra("salutation", saludo);
                startActivity(intent);
            }
        });



        //para habilitar las fechas
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visivility = isChecked?View.VISIBLE:View.GONE;
                View view = findViewById(R.id.timePicker);
                view.setVisibility(visivility);
                view = findViewById(R.id.datePicker);
                view.setVisibility(visivility);
            }
        });

    }
    protected void showToast(){
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.noHayNombre);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



//Listo para corregir







//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
