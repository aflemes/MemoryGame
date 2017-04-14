package com.aula_android.views_basicas_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context contexto;
	//vetor de inteiros (objetos) com a refer�ncia das imagens (pasta Drawable)
	private Integer[] imagens = {
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3,            
			R.drawable.sample_4, R.drawable.sample_5,            
			R.drawable.sample_6, R.drawable.sample_7,            
			R.drawable.sample_0, R.drawable.sample_1,            
			R.drawable.sample_2, R.drawable.sample_3,            
			R.drawable.sample_4, R.drawable.sample_5,            
			R.drawable.sample_6, R.drawable.sample_7,            
			R.drawable.sample_0, R.drawable.sample_1,            
			R.drawable.sample_2, R.drawable.sample_3,            
			R.drawable.sample_4, R.drawable.sample_5,            
			R.drawable.sample_6, R.drawable.sample_7
	};
	public ImageAdapter(Context c) {
		contexto = c;    
	}    
	public int getCount() {   //m�todo abstrato     
		return imagens.length;    
	}
	public Object getItem(int posicao) {//m�todo abstrato
		return imagens[posicao];
	}    
	public long getItemId(int posicao) { //m�todo abstrato         
		return 0;    
	}    
	//Cria um novo ImageView para cada item referenciado pelo Adapter
	public View getView(int posicao, View convertView, ViewGroup parent) {//m�todo abstrato
			ImageView imageView;
			if (convertView == null) {             
				imageView = new ImageView(contexto);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //escala a imagem - largura e altura
				imageView.setPadding(8, 8, 8, 8);  //espaço entre as imagens
			} else {            
				imageView = (ImageView) convertView;
			}        
			imageView.setImageResource(imagens[posicao]);        
			return imageView;    
		}    		
}
