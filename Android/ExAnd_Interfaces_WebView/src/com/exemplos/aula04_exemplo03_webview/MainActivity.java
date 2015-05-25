package com.exemplos.aula04_exemplo03_webview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        WebView wv = (WebView) findViewById(R.id.webview1);        
       
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        
     // O método loadUrl carrega a página de uma determinada URL.
        //https://developers.google.com/chart/image/docs/making_charts?hl=pt-BR
        wv.loadUrl(
            "http://chart.apis.google.com/chart" +
            "?chs=300x225" + //tamanho
            "&cht=p3" + //tipo de gráfico, testar com v
            "&chco=FF6342,ADDE63,63C6DE" + //Cores
            "&chd=t:300,80,60" + //Valores
            "&chdl=A|B|C"); //Nomes, devem ser iguais aos valores
        
        
        
        /* ****************************************************************
         * É possível inserir uma tag html no componente WebView.
         * O exemplo abaixo mostra como fazer isso.
         * *****************************************************************
         */
        
       /* WebView wv = (WebView) findViewById(R.id.webview1);        
        
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = "<H1>Página HTML Simples</H1><body>" +
            "<p>Carros esporte vermelhos são velozes</p>"; 
 
        wv.loadDataWithBaseURL("", html, mimeType, encoding, "");
        */
        
        /* ****************************************************************
         * É possível carregar um arquivo html local no componente WebView.
         * O exemplo abaixo mostra como fazer isso.
         * O arquivo HTML deve estar na pasta \assets do seu projeto
         * *****************************************************************
         */
        /*
        WebView wv = (WebView) findViewById(R.id.webview1);	    
	    wv.loadUrl("file:///android_asset/Index.html");
	    */
            
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}

