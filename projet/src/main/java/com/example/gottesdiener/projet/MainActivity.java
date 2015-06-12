package com.example.gottesdiener.projet;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TranslateAnimation translateAnimation = new TranslateAnimation(1100,0,0,0);
        translateAnimation.setStartOffset(1100);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(1000);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        linearLayout.startAnimation(translateAnimation);

    }


    public void editTex(View view){
        EditText editText = (EditText) view;
        editText.setText("");
        if (editText.getId() == R.id.editText) editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
    }

    public void renvoi(View view) {
        switch (view.getId()){
            case R.id.connec : {
                String mail  = ((EditText) findViewById(R.id.editText1)).getText().toString();
                int pass  = ((EditText) findViewById(R.id.editText)).getText().toString().hashCode();
                try{
                    int cpass =Integer.parseInt(new GetHashedPassword(this, mail).execute().get());
                    if (pass==cpass && pass!=0){
                        if (getResources().getBoolean(R.bool.isSmartphone) || getResources().getBoolean(R.bool.isTabletPort)){
                            Intent intent = new Intent(this,ListBooksActivity.class);
                            this.startActivityForResult(intent,1000);
                        }else{
                            Intent intent = new Intent(this,FragmentActivity.class);
                            this.startActivityForResult(intent,1000);
                        }
                    }else ((EditText) findViewById(R.id.editText)).setError("Adresse mail ou mot de passe incorrect");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.d("erreur","1");
                } catch (ExecutionException e) {
                    e.printStackTrace();Log.d("erreur", "2");
                }catch (Exception e){
                    Toast.makeText(this, "Dispositif hors connexion! ", Toast.LENGTH_SHORT);
                    e.printStackTrace();Log.d("erreur", "3");
                }

                /**/

                break;
            }
            case R.id.oublier : break;
            case R.id.creer : {
                Intent intent = new Intent(this,Creation.class);
                this.startActivityForResult(intent,1000);

                break;
            }
        }
    }

    public void password (View v){
        Intent intent = new Intent(this,MdpActivity.class);
        startActivity(intent);
    }

}
