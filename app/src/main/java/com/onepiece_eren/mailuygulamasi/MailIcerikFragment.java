package com.onepiece_eren.mailuygulamasi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//po
public class MailIcerikFragment extends Fragment {

    private int pozisyon=0;

    public MailIcerikFragment(int pozisyon){ this.pozisyon=pozisyon; }  // yapıcı method pozisyon bilgisi alan

    public int getPozisyon(){ return pozisyon; }   // yapıcı method atama yapan

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.icerik,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView icerik_tv = (TextView) getActivity().findViewById(R.id.tv_icerik);

        String[] icerik = getActivity().getResources().getStringArray(R.array.mail_icerik);
        icerik_tv.setText(icerik[pozisyon]);
    }
}
