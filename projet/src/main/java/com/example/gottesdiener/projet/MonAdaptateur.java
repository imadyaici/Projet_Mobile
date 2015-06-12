package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Gottesdiener on 24/03/2015.
 */
public class MonAdaptateur extends BaseAdapter implements View.OnClickListener{
    private ArrayList<Livre> listeLivres;
    private LayoutInflater monInflateur;
    private Context monContext;
    private int currentPage;
    private int pagination;


    public MonAdaptateur(Context context, ArrayList<Livre> listeLivres, int pagination) {
        this.monInflateur = LayoutInflater.from(context);
        this.monContext = context;
        this.listeLivres = listeLivres;
        this.pagination = pagination;
        this.currentPage = 1;
    }

    @Override
    public int getCount() {
        return Math.min(this.listeLivres.size(), this.pagination * this.currentPage);
    }

    @Override
    public Object getItem(int position) {
        return this.listeLivres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = monInflateur.inflate(R.layout.listbooks,null);
            viewHolder = new ViewHolder();
            viewHolder.setAuteur((TextView) convertView.findViewById(R.id.auteurLivre));
            viewHolder.setTitre((TextView) convertView.findViewById(R.id.titreLivre));
            viewHolder.setResume((TextView) convertView.findViewById(R.id.resumeLivre));
            viewHolder.setImageView((ImageView) convertView.findViewById(R.id.imageLivre));
            viewHolder.setCategorie((TextView) convertView.findViewById(R.id.categorieLivre));
            viewHolder.setAnneeEdition((TextView) convertView.findViewById(R.id.anneeEditionLivre));
            convertView.setTag(viewHolder);
        }else{
            viewHolder = new ViewHolder((ViewHolder) convertView.getTag());
        }

        viewHolder.getAuteur().setText("Auteur : "+listeLivres.get(position).getAuteur());
        viewHolder.getTitre().setText("Titre : "+listeLivres.get(position).getTitre());
        viewHolder.getResume().setText("Résumé : "+listeLivres.get(position).getResume());
        viewHolder.getCategorie().setText("Catégorie : "+listeLivres.get(position).getCategorie());
        viewHolder.getAnneeEdition().setText("Année d'édition : "+listeLivres.get(position).getAnneeEdition());

        //image
        byte[] image = listeLivres.get(position).getImageView();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        viewHolder.getImageView().setImageBitmap(theImage);

        if (position == this.currentPage * this.pagination-1){
            TextView textView = (TextView) convertView.findViewById(R.id.plus);
            textView.setVisibility(View.VISIBLE);
            textView.setOnClickListener(this);
        }
        return convertView;
    }

    public void onClick(View view){
        currentPage++;
        notifyDataSetChanged();
        view.setVisibility(View.GONE);
    }

}
