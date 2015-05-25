package com.examplos.aula01_calculos_01;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnResultado;
	EditText txtResultado;
	
	int codigoRequisicao=1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnResultado = (Button) findViewById(R.id.btnCalculo);
        txtResultado = (EditText) findViewById(R.id.txtResultado);
        
        btnResultado.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			//startActivity(new Intent(getApplicationContext(), Calculo.class));
    			startActivityForResult(new Intent(getApplicationContext(), Calculo.class),
    								   codigoRequisicao);
    		}
    	});
    }
    
    public void onActivityResult(int codRequisicao, int codResultado, Intent dados){
    	if(codRequisicao == codigoRequisicao){
    		if(codResultado == RESULT_OK){
    			txtResultado.setText(dados.getData().toString());
    			
    			//Toast.makeText(this, dados.getData().toString(),Toast.LENGTH_LONG).show();
    		}
    	}
    }
    
}
