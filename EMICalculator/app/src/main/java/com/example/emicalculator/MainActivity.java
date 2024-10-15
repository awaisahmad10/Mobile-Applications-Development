package com.example.emicalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // initialize variables
    int principal_amount;
    double interest_rate;
    int term;
    double monthlyPayment;
    EditText editPrincipal, editRate, editTerm;
    TextView EMIOutput;
    Button calculate_btn;

    // function to calculate the user's EMI
    public static double calculateEMI(int principal, double rate, int term) {
        // convert user input to month values
        double monthlyRate = (rate / 100) / 12;
        double numMonths = term * 12;

        double EMI = (principal * monthlyRate * Math.pow(1 + monthlyRate, numMonths)) /
                (Math.pow(1 + monthlyRate, numMonths) - 1);

        return EMI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the elements and get the users inputs
        EMIOutput = findViewById(R.id.EMIOutput);
        calculate_btn = findViewById(R.id.calculateEMI);
        editPrincipal = findViewById(R.id.editPrincipalAmount);
        editRate = findViewById(R.id.editRate);
        editTerm = findViewById(R.id.editTerm);

        // listen for when button is clicked
        calculate_btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // convert user input from view to number
                principal_amount = Integer.parseInt(editPrincipal.getText().toString());
                interest_rate = Double.parseDouble(editRate.getText().toString());
                term = Integer.parseInt(editTerm.getText().toString());

                // check to ensure fields were filled in correctly
                if (editPrincipal.getText().toString().isEmpty() || editRate.getText().toString().isEmpty() || editTerm.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please ensure all fields are filled!", Toast.LENGTH_SHORT).show();
                } else if (principal_amount <= 0 || interest_rate <= 0 || term <= 0) {
                    Toast.makeText(MainActivity.this, "Please ensure all values are greater than 0!", Toast.LENGTH_SHORT).show();
                } else {
                    // calculate EMI
                    monthlyPayment = calculateEMI(principal_amount, interest_rate, term);

                    // output to user
                    EMIOutput.setText("$" + String.format("%.2f", monthlyPayment));
                }
            }
        });
    }
}