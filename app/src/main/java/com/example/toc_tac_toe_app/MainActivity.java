package com.example.toc_tac_toe_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    TextView playerOne,PlayerTwo;
    ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    List<Integer> player1 = new ArrayList<>();
    List<Integer> player2 = new ArrayList<>();
    List<Integer> full = new ArrayList<>();

    int turn = 0;
    int places_taken=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne= findViewById(R.id.playerone);
        PlayerTwo= findViewById(R.id.playertwo);
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("PlayerOne");
        playerOne.setText(name1);
        String name2 = intent.getStringExtra("PlayerTwo");
        PlayerTwo.setText(name2);

        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);

        image1.setOnClickListener(v -> onclick(1,image1));
        image2.setOnClickListener(v -> onclick(2,image2));
        image3.setOnClickListener(v -> onclick(3,image3));
        image4.setOnClickListener(v -> onclick(4,image4));
        image5.setOnClickListener(v -> onclick(5,image5));
        image6.setOnClickListener(v -> onclick(6,image6));
        image7.setOnClickListener(v -> onclick(7,image7));
        image8.setOnClickListener(v -> onclick(8,image8));
        image9.setOnClickListener(v -> onclick(9,image9));
    }

    boolean check_winner(List<Integer> player) {

        List<Integer> toprow = Arrays.asList(1, 2, 3);
        List<Integer> midrow = Arrays.asList(4, 5, 6);
        List<Integer> botrow = Arrays.asList(7, 8, 9);
        List<Integer> leftcol = Arrays.asList(1, 4, 7);
        List<Integer> midcol = Arrays.asList(2, 5, 8);
        List<Integer> rightcol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(toprow);
        winning.add(midrow);
        winning.add(botrow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning)
            if(player.containsAll(l)) return true;

        return false;
    }

    boolean check_full(int position) {
        for(int l: full) if(l==position) return false;
        return true;
    }

    void onclick(int position,ImageView image){
        if(turn==0){
            if(check_full(position)){
                image.setImageResource(R.drawable.x);
                player1.add(position);
                turn=1;
                places_taken++;
                full.add(position);
                if(places_taken==9)
                    if(!check_winner(player1))
                        Toast.makeText(MainActivity.this , "DRAW" , Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this , "Player1 Win" , Toast.LENGTH_SHORT).show();
                else
                    if(check_winner(player1))
                        Toast.makeText(MainActivity.this , "Player1 Win" , Toast.LENGTH_SHORT).show();
            }
        }else{
            if(check_full(1)){
                image.setImageResource(R.drawable.o);
                player2.add(position);
                turn=0;
                places_taken++;
                full.add(position);
                if(places_taken==9)
                    if(!check_winner(player2))
                        Toast.makeText(MainActivity.this , "DRAW" , Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this , "Player2 Win" , Toast.LENGTH_SHORT).show();
                else
                    if(check_winner(player2))
                        Toast.makeText(MainActivity.this , "Player2 Win" , Toast.LENGTH_SHORT).show();
            }
        }
    }

}