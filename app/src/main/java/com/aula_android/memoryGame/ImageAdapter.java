package com.aula_android.memoryGame;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImageAdapter extends BaseAdapter {
	private Context contexto;
	//vetor de inteiros (objetos) com a refer�ncia das imagens (pasta Drawable)
	private Integer[] imagens = {
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,            
			R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_2, R.drawable.sample_3,
	};

	private Integer[] imagens_original;
    private int lstChoise = 0;
    private int lstPosicao = 0;

    private Runnable runDesvirarCartas = new Runnable() {
        @Override
        public void run(){
            desvirar_cartas(); //<-- put your code in here.
        }
    };

	public ImageAdapter(Context c) {
		contexto = c;

        imagens_original = new Integer[imagens.length];

        for (int i=0;i<imagens.length;i++)
		    imagens_original[i] = imagens[i];
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
				imageView.setLayoutParams(new GridView.LayoutParams(220, 220));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); //escala a imagem - largura e altura
				imageView.setPadding(4, 4, 4, 4);  //espaço entre as imagens
			} else {            
				imageView = (ImageView) convertView;
			}

			Log.d("GetView","setImageResource");
			imageView.setImageResource(imagens[posicao]);
			return imageView;    
		}

	public void initImagens(){
		/*Random r = new Random();
		int i = 0;
		Integer[] array_imgs = {R.drawable.sample_0, R.drawable.sample_1,
					 			R.drawable.sample_2, R.drawable.sample_3,
					 			R.drawable.sample_4, R.drawable.sample_5,
					 			R.drawable.sample_6, R.drawable.sample_7};
		Integer[] imgs_inits = {0,0,0,0,0,0,0,0};
		ArrayList<Integer> imagens_aux = new ArrayList<>();

		while (true){
			if (i >= 16)
				break;

			int nextImage = r.nextInt(0 - 7);

			if (imgs_inits[nextImage] != 2){
				imagens_aux.add(i,imgs_inits[nextImage]);
				imgs_inits[nextImage]++;
				i++;
                Log.d("Incluído registro","");
            }
		}
        Log.d("Sai","");*/

		//imagens = imagens_aux;
	}

	public void setImagens(){
        for (int i=0;i < imagens.length;i++){
            imagens[i] = R.drawable.sample_blank;
        }
        this.notifyDataSetChanged();
    }

    public void virar_carta(int posicao){
        lstPosicao = posicao;

        imagens[lstPosicao] = imagens_original[lstPosicao];
        this.notifyDataSetChanged();

        //acertou
        if (imagens[lstPosicao].intValue() == imagens[lstChoise].intValue()){
            lstChoise  = 0;
            lstPosicao = 0;
        }
        else{
            if (lstChoise != 0) {
                Handler handlerDesvirarCartas = new Handler();
                handlerDesvirarCartas.postDelayed(runDesvirarCartas, 1000);
            }
            else lstChoise = lstPosicao;
        }
    }

    private void desvirar_cartas(){
        imagens[lstPosicao] = R.drawable.sample_blank;
        imagens[lstChoise]  = R.drawable.sample_blank;
        this.notifyDataSetChanged();
        lstChoise = 0;
    }

}
