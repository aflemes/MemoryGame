package com.aula_android.memoryGame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    private int combinacoes = 0;
    private boolean lFlag = false;
    private int qtdeTentativas = 0;

    private Runnable runDesvirarCartas = new Runnable() {
        @Override
        public void run(){
            desvirar_cartas(); //<-- put your code in here.
        }
    };

	public ImageAdapter(Context c) {
		contexto = c;

        iniciar_jogo();
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

	public void setImagens(){
        for (int i=0;i < imagens.length;i++){
            imagens[i] = R.drawable.sample_blank;
        }
        this.notifyDataSetChanged();
    }

    public int virar_carta(int posicao){
        if (lFlag)
            return qtdeTentativas;

        if (imagens[posicao] != R.drawable.sample_blank)
            return qtdeTentativas;

        lstPosicao = posicao;

        imagens[lstPosicao] = imagens_original[lstPosicao];
        this.notifyDataSetChanged();

        //acertou
        if (imagens[lstPosicao].intValue() == imagens[lstChoise].intValue()){
            lstChoise  = 0;
            lstPosicao = 0;
            combinacoes-=1;

            if (combinacoes == -1){
                winner_message();
            }
        }
        else{
            if (lstChoise != 0) {
                lFlag  = true;
                Handler handlerDesvirarCartas = new Handler();
                handlerDesvirarCartas.postDelayed(runDesvirarCartas, 1000);
                qtdeTentativas++;
            }
            else lstChoise = lstPosicao;
        }

        return qtdeTentativas;
    }

    private void desvirar_cartas(){
        imagens[lstPosicao] = R.drawable.sample_blank;
        imagens[lstChoise]  = R.drawable.sample_blank;
        this.notifyDataSetChanged();
        lstChoise = 0;
        lFlag = false;
    }

    public void reiniciar(){
        for (int i=0;i<imagens_original.length;i++){
            imagens[i] = imagens_original[i];
        }

        iniciar_jogo();
    }
    private void iniciar_jogo(){
        // variaveis
        Random rdnNumber = new Random();
        int value = 0;
        int img_aux = 0;
        int qtdeTentativas = 0;

        imagens_original = new Integer[imagens.length];

        for (int i=0;i<imagens.length;i++){
            value = (rdnNumber.nextInt(imagens.length - 0));
            img_aux = imagens[value];
            imagens[value] = imagens[i];
            imagens[i] = img_aux;
        }

        for (int i=0;i<imagens.length;i++)
            imagens_original[i] = imagens[i];

        combinacoes = imagens.length / 2;
        this.notifyDataSetChanged();
    }

    private void winner_message(){
        AlertDialog.Builder alert = new AlertDialog.Builder(contexto);
        alert.setTitle("Você venceu!");
        // alert.setMessage("Message");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });
        alert.show();
    }
}
