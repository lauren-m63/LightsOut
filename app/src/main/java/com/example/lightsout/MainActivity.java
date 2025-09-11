package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    TextView label;

    Button resetButton;

    Button randomButton;
    private boolean cellState [][];

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button current = (Button) v;
            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                if (gridButton == current) {
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    if (cellState[row][col] == true) {
                        cellState[row][col] = false;
                    } else {
                        cellState[row][col] = true;
                    }
                }
            }
            recolor();
        }
    };

    View.OnClickListener randomButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v1) {
            Button current = (Button) v1;

            current.findViewById(R.id.resetButton);
            randomize();
            recolor();
            lightOn();
        }

    };

    View.OnClickListener resetButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v2) {

            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                // Find the button's row and col
                int row = i / GRID_SIZE;
                int col = i % GRID_SIZE;

                if (cellState[row][col] == true) {
                    gridButton.setBackgroundColor(getColor(R.color.blue_500));
                } else {
                    gridButton.setBackgroundColor(getColor(R.color.blue_500));
                }
            }

        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);

        randomize();

        recolor();

        lightOn();

        for (int i = 0; i < grid.getChildCount(); i++) {
            Button currButton = (Button) grid.getChildAt(i);
            currButton.setOnClickListener(buttonListener);
        }

        resetButton = findViewById(R.id.resetButton); //has to be after the onCreate
        resetButton.setOnClickListener(resetButtonListener); //tie button listener to button

        randomButton = findViewById(R.id.randomButton); //has to be after the onCreate
        randomButton.setOnClickListener(randomButtonListener); //tie button listener to button
    }

    public void recolor(){
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }

    public void randomize(){
        Random random = new Random();
        for(int i =0; i< GRID_SIZE; i++){
            for(int j =0; j< GRID_SIZE; j++){
                cellState[i][j] = random.nextBoolean();
            }
        }
    }

    public void lightOn(){
        int lights= 0;
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                lights++;
            }
        }
        label = findViewById(R.id.ScoreText);
        label.setText("Score: " + lights);

    }



}