package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Gottesdiener on 26/03/2015.
 */
public class FragmentDetail extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onStart() {
        super.onStart();
        Bundle bundle= this.getArguments();
        if (bundle!=null) {
            TextView textView = (TextView) getActivity().findViewById(R.id.titre);
            TextView textView2 = (TextView) getActivity().findViewById(R.id.auteur);
            TextView textView3 = (TextView) getActivity().findViewById(R.id.categorie);
            TextView textView4 = (TextView) getActivity().findViewById(R.id.resume);
            TextView textView5 = (TextView) getActivity().findViewById(R.id.annee);

            textView.setText(bundle.getString("titre"));
            textView2.setText(bundle.getString("auteur"));
            textView3.setText(bundle.getString("categorie"));
            textView4.setText(bundle.getString("resume"));
            textView5.setText(bundle.getString("annee"));
            //imageView1.setBackground(imageView.getBackground());
        }
    }



}
