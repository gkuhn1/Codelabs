package com.example.exand_webservice_02;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.os.Build;

//Para herdar MapActivity, precisa alterar a biblitoeca para Google API

public class MainActivity extends MapActivity implements View.OnClickListener, View.OnKeyListener  {

	ViewFlipper flipper; 
	EditText etCEP;
	TextView tvEstado, tvCidade, tvBairro, tvTipoLograd, tvLograd, tvComplemento;
	ImageButton imgBtnPesquisar;
	MapView mapView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

		flipper.setInAnimation(in);
		flipper.setOutAnimation(out);
		flipper.startFlipping();
		
		etCEP = (EditText) findViewById(R.id.etCEP);
		tvEstado = (TextView) findViewById(R.id.tvEstado);
		tvCidade = (TextView) findViewById(R.id.tvCidade);
		tvBairro = (TextView) findViewById(R.id.tvBairro);
		tvTipoLograd = (TextView) findViewById(R.id.tvTipoLogradouro);		
		tvLograd = (TextView) findViewById(R.id.tvLogradouro);
		tvComplemento = (TextView) findViewById(R.id.tvComplemento);
		imgBtnPesquisar = (ImageButton) findViewById(R.id.imgBtnPesquisar);
		mapView = (MapView) findViewById(R.id.map);
		
		LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.map_zoom);
		zoomLayout.addView(mapView.getZoomControls(),new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		imgBtnPesquisar.setOnClickListener(this);
		etCEP.setOnKeyListener(this);
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		SubMenu subMenu = menu.addSubMenu(0,1,2, "Modo de Mapa").setIcon(android.R.drawable.ic_menu_mapmode);
		subMenu.add(0,2,1,"Tráfego");
		subMenu.add(0,3,2,"Satélite");
		return true;
	}	

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 2:
				mapView.setTraffic(true);
				mapView.setSatellite(false);
				break;
			case 3:
				mapView.setTraffic(false);
				mapView.setSatellite(true);
				break;
		}
		return true;
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	

}
