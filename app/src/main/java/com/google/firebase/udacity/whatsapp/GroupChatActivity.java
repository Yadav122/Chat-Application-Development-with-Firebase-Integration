package com.google.firebase.udacity.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.udacity.whatsapp.Adapter.ChatAdapter;
import com.google.firebase.udacity.whatsapp.Models.MessageModel;
import com.google.firebase.udacity.whatsapp.databinding.ActivityChatsDetailsBinding;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {
    ActivityChatsDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChatsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        final FirebaseDatabase database =FirebaseDatabase.getInstance();
        final ArrayList<MessageModel> messageModels= new ArrayList<>();
        final  String senderId = FirebaseAuth.getInstance().getUid();
        binding.userName.setText("Friend Chat");

        final ChatAdapter adapter= new ChatAdapter(messageModels,this);
        binding.chatsRecyclerVierw.setAdapter(adapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.chatsRecyclerVierw.setLayoutManager(layoutManager);




         database.getReference().child("Group Chat")
                 .addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         messageModels.clear();
                         for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                             MessageModel model= dataSnapshot.getValue(MessageModel.class);
                             messageModels.add(model);

                         }
                         adapter.notifyDataSetChanged();

                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {

                     }
                 });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etMessage.getText().toString().isEmpty()
                ){
                    binding.etMessage.setError("Enter your message ");

                }
                final  String message = binding.etMessage.getText().toString();
                final MessageModel model = new MessageModel(senderId,message);
                model.setTimeStamp(new Date().getTime());
                binding.etMessage.setText("");

                database.getReference().child("Group Chat")
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });

    }
}