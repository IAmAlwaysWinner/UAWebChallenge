package com.example.android.uawebchallenge;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.uawebchallenge.ArraySnake.Run;

import org.w3c.dom.Text;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    double[][] mainGameArray;

    static int AMOUNT_OF_SYMBOLS_FOR_ROW;
    static int AMOUT_OF_ROWS;
    static int DISPLAY_WIDTH;
    static int DISPLAY_HEIGHT;

    static int SINGLE_A_SYMBOL_WIDTH;
    static int SINGLE_LINE_HEIGHT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.start_game_button);
        button.setOnClickListener(startGameClickListener);
    }

    View.OnClickListener startGameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Run run = new Run();
            run.startGame();
        }
    };

    public void getMeasuring(){
        TextView maintTextView = (TextView) findViewById(R.id.main_text_view);
        SINGLE_LINE_HEIGHT = maintTextView.getLineHeight();

        maintTextView.setText("@");
        SINGLE_A_SYMBOL_WIDTH = maintTextView.getWidth();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        DISPLAY_WIDTH = size.x;
        DISPLAY_HEIGHT = size.y;

        AMOUNT_OF_SYMBOLS_FOR_ROW = DISPLAY_WIDTH/SINGLE_A_SYMBOL_WIDTH;
        AMOUT_OF_ROWS = DISPLAY_HEIGHT/SINGLE_LINE_HEIGHT;
        System.out.println(AMOUNT_OF_SYMBOLS_FOR_ROW);
        System.out.println(AMOUT_OF_ROWS);
    }
}