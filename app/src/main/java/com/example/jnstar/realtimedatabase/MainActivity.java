package com.example.jnstar.realtimedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void saveRecord(View view){
        EditText edUsername = (EditText) findViewById(R.id.ed_username);
        EditText edPassword = (EditText) findViewById(R.id.ed_password);
        String strUsername = edUsername.getText().toString();
        String strPassword = edPassword.getText().toString();
        if(strUsername.isEmpty()||strPassword.isEmpty()){
            Toast.makeText(MainActivity.this,"Empty",Toast.LENGTH_LONG).show();
            return;
        }
        DatabaseReference mNameRef = mRootRef.child("Username").child("username");
        DatabaseReference mPassRef = mRootRef.child("Password").child("password");
        mNameRef.setValue(strUsername);
        mPassRef.setValue(strPassword);
    }
    public void displayMsg(View view){
        final TextView shUser = (TextView) findViewById(R.id.tv_display);
        mRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String strUname = dataSnapshot.child("Username").child("username").getValue().toString();
                String strPword = dataSnapshot.child("Password").child("password").getValue().toString();
                shUser.setText("username : " + strUname + '\n' + "password : " + strPword);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
