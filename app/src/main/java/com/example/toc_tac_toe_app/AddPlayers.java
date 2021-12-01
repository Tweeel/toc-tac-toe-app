package com.example.toc_tac_toe_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlayers extends AppCompatActivity {

    String getPlayerTwoName=null;
    String getPlayerOneName=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        final EditText PlayerOne = findViewById(R.id.PLayerOneName);
        final EditText PlayerTwo = findViewById(R.id.PLayerTwoName);
        final Button startGameBtn = findViewById(R.id.startGameBtn);


        startGameBtn.setOnClickListener(v -> {
            getPlayerOneName = PlayerOne.getText().toString();
            getPlayerTwoName = PlayerTwo.getText().toString();

            if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                Toast.makeText(AddPlayers.this , "Please entre player names" , Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                intent.putExtra("PlayerOne",getPlayerOneName);
                intent.putExtra("PlayerTwo",getPlayerTwoName);
                startActivity(intent);
            }
        });
    }

}