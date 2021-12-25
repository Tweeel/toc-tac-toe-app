package com.example.toc_tac_toe_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView playerOne, PlayerTwo;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    LinearLayout player1_corners, player2_corners;

    List<Integer> player1 = new ArrayList<>();
    List<Integer> player2 = new ArrayList<>();
    List<Integer> full = new ArrayList<>();

    private int turn = 0;
    int places_taken = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = findViewById(R.id.playerone);
        PlayerTwo = findViewById(R.id.playertwo);
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("PlayerOne");
        playerOne.setText(name1);
        String name2 = intent.getStringExtra("PlayerTwo");
        PlayerTwo.setText(name2);

        player1_corners = findViewById(R.id.player1_corners);
        player2_corners = findViewById(R.id.player2_corners);
        player1_corners.setBackgroundResource(R.drawable.round_back_red);
        player2_corners.setBackgroundResource(R.drawable.round_back_blue_border);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        image1.setOnClickListener(v -> onclick(1, image1));
        image2.setOnClickListener(v -> onclick(2, image2));
        image3.setOnClickListener(v -> onclick(3, image3));
        image4.setOnClickListener(v -> onclick(4, image4));
        image5.setOnClickListener(v -> onclick(5, image5));
        image6.setOnClickListener(v -> onclick(6, image6));
        image7.setOnClickListener(v -> onclick(7, image7));
        image8.setOnClickListener(v -> onclick(8, image8));
        image9.setOnClickListener(v -> onclick(9, image9));

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

        for (List l : winning) if (player.containsAll(l)) return true;

        return false;
    }

    boolean check_full(int position) {
        for (int l : full) if (l == position) return false;
        return true;
    }

    void onclick(int position, ImageView image) {
        Log.d("test", String.valueOf(turn));
        if (turn == 0) {
            Log.d("test", "i'm in the turn 0");
            Log.d("info", "\n");
            if (check_full(position)) {
                image.setImageResource(R.drawable.x);
                Log.d("info", "image added");
                player1.add(position);
                Log.d("info", "player 1 : " + String.valueOf(player1));
                Log.d("info", "position added");
                turn = 1;
                player2_corners.setBackgroundResource(R.drawable.round_back_blue);
                player1_corners.setBackgroundResource(R.drawable.round_back_blue_border);
                Log.d("info", "turn changed");
                places_taken++;
                Log.d("info", "increment the places taken");
                full.add(position);
                Log.d("info", "full : " + String.valueOf(full));
                Log.d("info", "add the position to list");
                if (places_taken == 9) {
                    Log.d("info", "places are full");
                    if (!check_winner(player1)) {
                        Log.d("info", "draw!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, "Draw!!", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    } else {
                        Log.d("info", "player 1 win!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, playerOne.getText().toString() + " is the winner !", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    }
                } else {
                    Log.d("info", "there is more places");
                    if (check_winner(player1)) {
                        Log.d("info", "player win!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, playerOne.getText().toString() + " is the winner !", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    } else Log.d("info", "keep playing!");
                }
            }
        } else {
            Log.d("test", "i'm in the turn 1");
            Log.d("info", "\n");
            if (check_full(position)) {
                Log.d("test", "i'm in the turn 1 in the second if");
                image.setImageResource(R.drawable.o);
                Log.d("info", "image added!");
                player2.add(position);
                Log.d("info", "position added!");
                Log.d("info", "player2 : " + String.valueOf(player2));
                turn = 0;
                player1_corners.setBackgroundResource(R.drawable.round_back_red);
                player2_corners.setBackgroundResource(R.drawable.round_back_blue_border);
                Log.d("info", "turn changed");
                places_taken++;
                Log.d("info", "increment the places taken");
                full.add(position);
                Log.d("info", "full : " + String.valueOf(full));
                Log.d("info", "add the position to list");
                if (places_taken == 9) {
                    Log.d("info", "places are full");
                    if (!check_winner(player2)) {
                        Log.d("info", "draw!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, "Draw!!", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    } else {
                        Log.d("info", "player 1 win!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, PlayerTwo.getText().toString() + " is the winner !", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    }
                } else {
                    Log.d("info", "there is more places");
                    if (check_winner(player2)) {
                        Log.d("info", "player win!");
                        WinDialog winDialog = new WinDialog(MainActivity.this, PlayerTwo.getText().toString() + " is the winner !", MainActivity.this);
                        winDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        winDialog.setCancelable(false);
                        winDialog.show();
                    } else Log.d("info", "keep playing!");

                }
            }
        }
    }

    public void restart_match() {
        image1.setImageResource(R.drawable.transparent);
        image2.setImageResource(R.drawable.transparent);
        image3.setImageResource(R.drawable.transparent);
        image4.setImageResource(R.drawable.transparent);
        image5.setImageResource(R.drawable.transparent);
        image6.setImageResource(R.drawable.transparent);
        image7.setImageResource(R.drawable.transparent);
        image8.setImageResource(R.drawable.transparent);
        image9.setImageResource(R.drawable.transparent);

        player1_corners.setBackgroundResource(R.drawable.round_back_red);
        player2_corners.setBackgroundResource(R.drawable.round_back_blue_border);

        turn = 0;
        places_taken = 0;

        player1.clear();
        player2.clear();
        full.clear();
    }

}