package com.google.firebase.udacity.whatsapp.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.udacity.whatsapp.Fragments.CallsFragments;
import com.google.firebase.udacity.whatsapp.Fragments.ChatsFragments;
import com.google.firebase.udacity.whatsapp.Fragments.StatusFragments;

public class FragmentsAdapter extends FragmentPagerAdapter {

    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChatsFragments();
                case 1:
                return new StatusFragments();
                case 2:
                return new CallsFragments();
            default:
                return new CallsFragments();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle = null;
        if (position==0){
            tittle="CHATS";
        }
       if (position==1){
           tittle="STATUS";
       }
       if (position==2){
           tittle="CALLS";
       }
        return tittle;
    }
}
