package com.onepiece_eren.mailuygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;


public class MailListesiFragment extends ListFragment {

    private boolean ekran_yataymi;
    private MailIcerikFragment mailIcerikFragment;

    //ListFragment kullandıgımız icin create gerek yok
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        String[] mail_listesi=getActivity().getResources().getStringArray(R.array.mail_listesi);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,mail_listesi);
        setListAdapter(adapter);

        FrameLayout container = (FrameLayout) getActivity().findViewById(R.id.icerik_fragment_container);

        ekran_yataymi=container!=null && container.getVisibility()== View.VISIBLE; //ekran doluysa ve görünür durumdaysa ekran yatay.

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (ekran_yataymi){

            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE); // listViewde sectigimiz eleman belirli oluyor choicemode ile

            mailIcerikFragment= (MailIcerikFragment) getFragmentManager().findFragmentById(R.id.icerik_fragment_container);


            //yataysa
            if (mailIcerikFragment==null || mailIcerikFragment.getPozisyon()!=position){
                mailIcerikFragment = new MailIcerikFragment(position);
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.icerik_fragment_container,mailIcerikFragment);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.commit();

            }


        }

        // düşeyse yapılacak işlemler
        else{
            Intent intent = new Intent(getActivity(),ActivityIcreik.class);
            intent.putExtra("pozisyon",position);
            startActivity(intent);


        }

    }

    // yataydan düşeye gecerken oluşturdugumuz containeri yok etmemiz lazım . yok etmezsek hata verir kapanır uygulamamız.
    @Override
    public void onPause() {
        super.onPause();

        if(mailIcerikFragment !=null){
            FragmentTransaction transaction=getFragmentManager().beginTransaction();
            transaction.remove(mailIcerikFragment);

        }
    }
}
