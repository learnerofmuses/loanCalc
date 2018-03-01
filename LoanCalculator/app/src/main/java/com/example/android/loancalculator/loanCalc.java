package com.example.android.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class loanCalc extends AppCompatActivity {
    private EditText Cost;
    private EditText Payment;
    private EditText APR;
    private TextView output;
    private TextView barLabel;
    private SeekBar seekBar;
    private RadioButton radioLoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_loan_calc);

        Cost = findViewById(R.id.Cost);
        Payment = findViewById(R.id.Payment);
        APR = findViewById(R.id.APR);
        output = findViewById(R.id.output);
        barLabel = findViewById(R.id.barLabel);
        seekBar = findViewById(R.id.seekBar);
        radioLoan = findViewById(R.id.radioLoan);

        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        barLabel.setText("Loan Length (in months)" + i + "");
                        buttonPressed(seekBar);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
        APR.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        buttonPressed(textView);
                        return false;
                    }

                }
        );
        radioLoan.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        buttonPressed(compoundButton);
                    }
                }
        );

        if (savedInstanceState != null) {
            output.setText(savedInstanceState.getString("Payment Cost:"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Payment Cost:", output.getText().toString());
    }

    public void buttonPressed(View v) {
        String inputOfCost = Cost.getText().toString();
        double cost = Double.parseDouble(inputOfCost);

        String userInputPayment = Payment.getText().toString();
        double payment = Double.parseDouble(userInputPayment);

        String userInputAPR = APR.getText().toString();
        double apr = Double.parseDouble(userInputAPR);

        int i = seekBar.getProgress();
        double mr = (apr / 100) / 12;
        double L = cost - payment;
        double n = i;
        double cost2;
        double P;

        if (radioLoan.isChecked()) {
            P = mr * L / (1 - Math.pow(1 + mr, -n));
        } else {
            cost2 = cost / 3;
            L = cost2 - payment;
            P = mr * L/(1 - Math.pow(1 + mr, -36));

        }
        String tD = String.format("%.2f", P);
        output.setText("$" + tD + "");
    }
}