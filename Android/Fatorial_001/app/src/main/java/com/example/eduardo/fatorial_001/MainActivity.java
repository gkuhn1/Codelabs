package com.example.eduardo.fatorial_001;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button btnCalcular;
    EditText edtValor;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        edtValor = (EditText)findViewById(R.id.edtValor);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0;
                int iValor = 0;
                int iResultado = 1;

                iValor = Integer.parseInt(edtValor.getText().toString());

                for (i = iValor; i > 0; i-- ) {
                    iResultado = iResultado * i;
                }

                //ExibeMensagem(String.valueOf(iResultado));

                Toast.makeText(getApplication().getApplicationContext(), String.valueOf(iResultado), Toast.LENGTH_LONG);

                edtValor.setText(String.valueOf(iResultado));

            }
        });
    }

    private void ExibeMensagem(String psMensagem)
    {
        Toast.makeText(getApplicationContext(), psMensagem, Toast.LENGTH_SHORT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatementedtValor
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
