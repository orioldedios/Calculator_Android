package com.elloiro.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String num = "";
    private TextView num_view;

    private enum operations
    {
        PLUS,
        MINUS,
        MULTIPLICATION,
        DIVISION,
        NONE
    }
    private operations operation_saved = operations.NONE;
    private float first_number_saved = 0.0f;
    private  boolean operand_clicked_before = false;
    private  boolean equal_button_clicked_before = false;
    private  boolean dot_set_once = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_view = findViewById(R.id.current_num);
        num_view.setText(num);
    }

    public void OnClickDigit(View view) {
        if (num_view.getText().toString() != "ERROR")
        {
            if (operand_clicked_before) {
                num_view.setText("");
                operand_clicked_before = false;
                num = "";
            }
            Button b = (Button) view;
            num += b.getText().toString().charAt(0);
            num_view.setText(num);
        }
    }
    public void OnClickOperand (View view)
    {
        if (equal_button_clicked_before || operation_saved != operations.NONE || num == "")
        {
            num_view.setText("ERROR");
            num = "";
        }
        else
        {
            Button b = (Button)view;
            char switch_case = b.getTag().toString().charAt(0);
            switch (switch_case)
            {
                case 'p':
                    operation_saved = operations.PLUS;
                    break;
                case  'r':
                    operation_saved = operations.MINUS;
                    break;
                case 'm':
                    operation_saved = operations.MULTIPLICATION;
                    break;
                case 'd':
                    operation_saved = operations.DIVISION;
                    break;
            }
            first_number_saved = Float.parseFloat(num);
            operand_clicked_before = true;
            dot_set_once = false;
            num = "";
        }
    }

    public void OnClickEqual (View view)
    {
        if (num_view.getText().toString() != "ERROR" && operation_saved != operations.NONE)
        {
            Operate();
            operand_clicked_before = false;
            equal_button_clicked_before = true;
            num = num_view.getText().toString();
        }
    }

    public void OnClickDot(View view)
    {
        if (num_view.getText().toString() != "ERROR" && !dot_set_once && !equal_button_clicked_before)
        {
            num += ".";
            dot_set_once = true;
        }
    }

    public void OnClickCE(View view)
    {
        num = "";
        operation_saved = operations.NONE;
        first_number_saved = 0.0f;
        operand_clicked_before = false;
        equal_button_clicked_before = false;
        dot_set_once = false;
        num_view.setText(num);
    }

    public  void Operate ()
    {
        float second_number_saved = Float.parseFloat(num);
            switch (operation_saved) {
                case NONE:
                    break;
                case PLUS:
                    num_view.setText(Float.toString(first_number_saved + second_number_saved));
                    break;
                case MINUS:
                    num_view.setText(Float.toString(first_number_saved - second_number_saved));
                    break;
                case DIVISION:
                    num_view.setText(Float.toString(first_number_saved / second_number_saved));
                    break;
                case MULTIPLICATION:
                    num_view.setText(Float.toString(first_number_saved * second_number_saved));
                    break;
            }
        }
}
