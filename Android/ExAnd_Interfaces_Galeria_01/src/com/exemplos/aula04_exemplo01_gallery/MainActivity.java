package com.exemplos.aula04_exemplo01_gallery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
    Integer[] imageIDs = {
            R.drawable.lancaster,
            R.drawable.lightning,
            R.drawable.spitfire,
            R.drawable.stuka,
            R.drawable.tomcat,
            R.drawable.zero
    };
    
    Gallery gallery;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery = (Gallery) findViewById(R.id.gallery1);        
        gallery.setAdapter(new ImageAdapter(this));        
        gallery.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view, int pos,long id) {
				// TODO Auto-generated method stub
				ImageView imageView = (ImageView) findViewById(R.id.image1);                
                imageView.setImageResource(imageIDs[pos]);
			}
        	
        });

        
    }
    
    //Reparar onde está a declaração desta classe, não é em outro arquivo
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
        private int itemBackground;
 
        public ImageAdapter(Context c){
            context = c;
        }
 
        //---returns the number of images---
        public int getCount(){
            return imageIDs.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position){
            return position;
        }            
 
        public long getItemId(int position){
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ImageView imageView = new ImageView(context);
            
            imageView.setImageResource(imageIDs[position]);
            
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 150));
            
           // imageView.setBackgroundResource(itemBackground);
            return imageView;
        }
    }
}

