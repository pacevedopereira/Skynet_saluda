package com.example.frpinkl.skynetsaluda;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by frpinkl on 06/11/2014.
 */
public class Salutation extends Activity{
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salutation);
        String salutation = getIntent().getExtras().getString("salutation");
        TextView out = (TextView)findViewById(R.id.out);
        out.setText(salutation);


    }

}
