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
        DIVISION
    }
    private operations operation_saved;
    private int first_number_saved;
    private  boolean operand_clicked_before = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_view = findViewById(R.id.current_num);
        num_view.setText(num);
    }

    public void OnClickDigit(View view)
    {
        if(operand_clicked_before)
        {
            num_view.setText("");
            operand_clicked_before = false;
            num = "";
        }
        Button b = (Button)view;
        num += b.getText().toString().charAt(0);
        num_view.setText(num);
    }

    public void OnClickOperand (View view)
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
        first_number_saved = Integer.parseInt(num);
        operand_clicked_before = true;
    }
}
