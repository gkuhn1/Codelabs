package com.example.exand_interfaces_fragment_01;

import android.app.Activity;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

/*Anten��o, o import do Fragment deve ser do v4*/

public class Fragment1 extends Fragment
{
	
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
	{
		/*View view, objeto que vai conter o layout_frag_1 definido nos layout
		 * layoutInflater � capaz de converter o XML no objeto(Parametro 1)
		 * null, pois n�o teremos um PAI nesse momento
		 **/
		
		View view = layoutInflater.inflate(R.layout.layout_frag_1, null);	
		TextView txtView = (TextView)view.findViewById(R.id.txtViewFrag1);
		txtView.setText("Esse � o fragment 1");
				
		return view;
	}
	
	public void alterarTextView(String pTexto) {
		//Repare no m�todo getView, ele substitui a view criada no onCreateView
		//atrav�s do Inflate, pois nesse momento j� temos a view criada.
		
		TextView txtView = (TextView)getView().findViewById(R.id.txtViewFrag1);
		txtView.setText(pTexto);
	}
}
