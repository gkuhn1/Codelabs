package com.eduardoptorezan.exand_bibliotecamusical;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemMusicoAdapter extends BaseAdapter{
	private Context context;    
	private List<Musico> itemList;
	
	public ItemMusicoAdapter(Context context, List<Musico> itemlist){        
		this.context = context;        
		this.itemList = itemlist;    
	}        
	
	public int getCount() {      
		//retorna o tamanho.
			return itemList.size();    
	}    
    
	public Object getItem(int position) {
		//retorna o item ENTIDADE
		return itemList.get(position);    
	}     
    
	public long getItemId(int position) {  
		//Em uma lista com identificadores inteiros, permite obter o número de cada item.
		return position;   
	}     
  
	public View getView(int position, View convertView, ViewGroup parent) {
		       
		//Constrói a relação ENTIDADE X ITEM DA LISTA
		
		//obtem o objeto Musico
		Musico item = itemList.get(position);
		
		//Serve para pegar a VIEW (Acitivy Listar_CDS), obter seu XML e identificar como é a sua visualização
		//Por isso o XML do listar_cds não tem uma lista implementada, na verdade ele tem o layout de um
		//item da lista e o LayoutInflater dá condições de carregar isso para um objeto VIEW
		LayoutInflater inflater = (LayoutInflater) 	context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
		View view = inflater.inflate(R.layout.activity_listar_musicos, null);                
		        
		TextView textNome = (TextView)view.findViewById(R.id.lblNomeDoMusico);        
		textNome.setText(item.getsNome()); 	
				         
		return view;
	}

}