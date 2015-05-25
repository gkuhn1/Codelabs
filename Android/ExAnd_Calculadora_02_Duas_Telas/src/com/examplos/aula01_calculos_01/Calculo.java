package com.examplos.aula01_calculos_01;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculo extends Activity {
	Button btnSoma;
	Button btnSub;
	EditText txtVal1;
	EditText txtVal2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculo);
        txtVal1 = (EditText) findViewById(R.id.txtNum1);
        txtVal2 = (EditText) findViewById(R.id.txtNum2);
        
        btnSoma = (Button) findViewById(R.id.btnSoma);
        btnSub = (Button) findViewById(R.id.btnSub);
        
        btnSoma.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			int num1 = Integer.parseInt(txtVal1.getText().toString());
    			int num2 = Integer.parseInt(txtVal2.getText().toString());
    			int res = num1 + num2;
    			
    			Intent dados = new Intent();
    			dados.setData(Uri.parse(Integer.toString(res)));
    			setResult(RESULT_OK,dados);
    			
    			finish();
    			//startActivity(new Intent(getApplicationContext(), Calculo.class));
    		}
    	});
        btnSub.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			int num1 = Integer.parseInt(txtVal1.getText().toString());
    			int num2 = Integer.parseInt(txtVal2.getText().toString());
    			int res = num1 - num2;
    			
    			Intent dados = new Intent();
    			dados.setData(Uri.parse(Integer.toString(res)));
    			setResult(RESULT_OK,dados);
    			
    			finish();
    			//startActivity(new Intent(getApplicationContext(), Calculo.class));
    		}
    	});
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 */
}
