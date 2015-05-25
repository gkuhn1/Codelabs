package com.example.exand_interfaces_fragment_01;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment3 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
	{
		/*View view, objeto que vai conter o layout_frag_3 definido nos layout
		 * layoutInflater é capaz de converter o XML no objeto(Parametro 1)
		 * null, pois não teremos um PAI nesse momento
		 **/
		
		View view = layoutInflater.inflate(R.layout.layout_frag_3, null);	
		TextView txtView = (TextView)view.findViewById(R.id.txtViewFrag1);
		txtView.setText("Esse é o fragment 3");
				
		return view;
	}
}
