package com.example.gottesdiener.projet;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import static com.example.gottesdiener.projet.R.id.fragmentdetail;

/**
 * Created by Gottesdiener on 26/03/2015.
 */
public class ActionBarView extends LinearLayout implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View mConvertVew;
    private ImageView imageView;
    private Spinner spinner;
    private EditText editText;
    private Resources resources;
    private ArrayAdapter arrayAdapter;

    public void setmConvertVew(View mConvertVew) {
        this.mConvertVew = mConvertVew;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setSpinner(Spinner spinner) {
        this.spinner = spinner;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public View getmConvertVew() {

        return mConvertVew;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    public EditText getEditText() {
        return editText;
    }

    public ActionBarView(Context context) {
        super(context);
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        super(context,attrs);

        mConvertVew = LayoutInflater.from(context).inflate(R.layout.action_bar,this);
        spinner = (Spinner) mConvertVew.findViewById(R.id.spinnerab);

        spinner = (Spinner) findViewById(R.id.spinnerab);
        resources = getResources();
        String[] categories = resources.getStringArray(R.array.categorie);
        arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_spinner_dropdown_item,categories);
        spinner.setAdapter(arrayAdapter);
//        spinner.setOnItemSelectedListener(this);

//        imageView.setOnClickListener(this);


    }

    public void showImageView(){
        imageView.setVisibility(VISIBLE);
    }

    public void showSpinner(){
        spinner.setVisibility(VISIBLE);
    }

    public void showEditText(){
        editText.setVisibility(VISIBLE);
    }


    @Override
    public void onClick(View v) {

    }

    public void back(View view){
        //Fragment fragment = (Fragment) findViewById(fragmentdetail);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String categorie = (String) parent.getItemAtPosition(position);
        imageView = (ImageView) findViewById(R.id.star);
        imageView.setVisibility(View.GONE);
        if (!categorie.equalsIgnoreCase("Catégorie")){
            imageView.setBackground(getResources().getDrawable(R.drawable.white_star));
            showImageView();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}