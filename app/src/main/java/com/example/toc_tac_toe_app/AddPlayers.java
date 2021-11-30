package com.example.toc_tac_toe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        final EditText PlayerOne = findViewById(R.id.PLayerOneName);
        final EditText PlayerTwo = findViewById(R.id.PLayerTwoName);
        final Button startGameBtn = findViewById(R.id.startGameBtn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String getPlayerOneName = PlayerOne.getText().toString();
                final String getPlayerTwoName = PlayerOne.getText().toString();

                if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this , "Please entre player names" , Toast.LENGTH_SHORT);
                }else{
                    Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                    intent.putExtra("PlayerOne",getPlayerOneName);
                    intent.putExtra("PlayerTwo",getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });
    }

}