package com.onepiece_eren.mailuygulamasi;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class ActivityIcreik extends AppCompatActivity{


    MailIcerikFragment mailIcerikFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            //yatayda ise
            finish(); // bir önceki sınıfa gönder
            return;
        }

        int pozisyon = getIntent().getIntExtra("pozisyon",0);
        mailIcerikFragment=new MailIcerikFragment(pozisyon);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content,mailIcerikFragment);
        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();


        if(mailIcerikFragment !=null){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.remove(mailIcerikFragment);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        //dikeyde sectikten sonra ana ekrana geri dönmek icin


        //androide bulunan anasayfa özel gösterim
        if(item.getItemId()==android.R.id.home){

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            NavUtils.navigateUpTo(this,intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
