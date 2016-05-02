package edu.suffolk.jsimons3.randommath;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView plusView;
    private TextView minusView;
    private TextView timesView;
    private TextView numbercorrectView;

    private EditText pluseditText;
    private EditText minuseditText;
    private EditText timeseditText;

    private ImageView starView1, starView2, starView3;

    private int first, second, plusanswer, minusanswer, timesanswer, plusattempt, minusattempt, timesattempt;
    private int amountcorrect = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plusView =
                (TextView) findViewById(R.id.plusView);

        minusView =
                (TextView) findViewById(R.id.minusView);

        timesView =
                (TextView) findViewById(R.id.timesView);

        numbercorrectView =
                (TextView) findViewById(R.id.numbercorrectView);

        pluseditText =
                (EditText) findViewById(R.id.pluseditText);

        minuseditText =
                (EditText) findViewById(R.id.minuseditText);

        timeseditText =
                (EditText) findViewById(R.id.timeseditText);

        Button submitbutton =
                (Button) findViewById(R.id.submitbutton);
        submitbutton.setOnClickListener(submitbuttonListener);

        Button resetbutton =
                (Button) findViewById(R.id.resetbutton);
        resetbutton.setOnClickListener(resetbuttonListener);

        starView1 =
                (ImageView)findViewById(R.id.starView1);

        starView2 =
                (ImageView)findViewById(R.id.starView2);

        starView3 =
                (ImageView)findViewById(R.id.starView3);


        generateNumbers();


    }

    private void generateNumbers() //Generates two random numbers between 1 and 10.
    {
        Random r = new Random();
        int max = 11;
        int min = 0;

        first = (r.nextInt(max - min) ) + min;
        second = (r.nextInt(max - min) ) + min;

        plusanswer = first + second;
        minusanswer = first - second;
        timesanswer = first * second;

        numbercorrectView.setText("");
        amountcorrect = 0; //Reset these values.

        starView1.setVisibility(View.INVISIBLE);
        starView2.setVisibility(View.INVISIBLE);
        starView3.setVisibility(View.INVISIBLE); //Hide stars

        updateViews();
    }

    private void updateViews() //Updates views
    {
        plusView.setText(first + " + " + second + "= ");
        minusView.setText(first + " - " + second + "= ");
        timesView.setText(first + " * " + second + "= ");
    }


    public View.OnClickListener resetbuttonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            generateNumbers(); //Resets numbers/views/answers.
        }
    };

    public View.OnClickListener submitbuttonListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            try {
                plusattempt = Integer.parseInt(pluseditText.getText().toString());
                }
            catch (Exception e)
            {
                plusattempt = 101;
            }

            try {
                minusattempt = Integer.parseInt(minuseditText.getText().toString());
            }
            catch (Exception e)
            {
                minusattempt = 101;
            }

            try {
                timesattempt = Integer.parseInt(timeseditText.getText().toString());
            }
            catch (Exception e)
            {
                timesattempt = 101;
            } //Get attempt values, sets them to 101 if empty to prevent a crash and ensure answer is wrong.

            if(plusattempt == plusanswer) amountcorrect++;
            if(minusattempt == minusanswer) amountcorrect++;
            if(timesattempt == timesanswer) amountcorrect++; //Increment if correct

            numbercorrectView.setText("You got " + amountcorrect + " question(s) correct!");//Display message

            //Display star(s)

            if(amountcorrect == 1) starView1.setVisibility(View.VISIBLE);

            if(amountcorrect == 2)
            {
                starView1.setVisibility(View.VISIBLE);
                starView2.setVisibility(View.VISIBLE);
            }

            if(amountcorrect == 3)
            {
                starView1.setVisibility(View.VISIBLE);
                starView2.setVisibility(View.VISIBLE);
                starView3.setVisibility(View.VISIBLE);
            }



        }
    };

}
