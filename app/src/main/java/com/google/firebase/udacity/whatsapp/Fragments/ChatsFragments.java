package com.google.firebase.udacity.whatsapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.udacity.whatsapp.Adapter.UserAdapter;
import com.google.firebase.udacity.whatsapp.Models.Users;
import com.google.firebase.udacity.whatsapp.R;
import com.google.firebase.udacity.whatsapp.databinding.FragmentChatsFragmentsBinding;

import java.util.ArrayList;

public class ChatsFragments extends Fragment {


    public ChatsFragments() {
        // Required empty public constructor
    }
  FragmentChatsFragmentsBinding binding;
    ArrayList<Users>list= new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      binding=FragmentChatsFragmentsBinding.inflate(inflater, container, false);
      database=FirebaseDatabase.getInstance();


        UserAdapter adapter= new UserAdapter(list,getContext());
        binding.chatsRecyclerVierw.setAdapter(adapter);

      LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
      binding.chatsRecyclerVierw.setLayoutManager(layoutManager);

      database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
          list.clear();
          for (DataSnapshot dataSnapshot : snapshot.getChildren()){
            Users users =dataSnapshot.getValue(Users.class);
//            users.getUserId(dataSnapshot.getKey());
            users.setUserId(dataSnapshot.getKey());
            if (!users.getUserId().equals(FirebaseAuth.getInstance().getUid())){
            list.add(users);}
          }
          adapter.notifyDataSetChanged();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
      });
      return binding.getRoot();
    }
}