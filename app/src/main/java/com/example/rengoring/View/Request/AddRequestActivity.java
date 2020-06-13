package com.example.rengoring.View.Request;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.rengoring.Model.Request;
import com.example.rengoring.Model.Supply;
import com.example.rengoring.R;
import com.example.rengoring.ViewModel.Supply.AddRequestViewModel;

import java.util.ArrayList;


public class AddRequestActivity extends AppCompatActivity {
    AddRequestViewModel addRequestViewModel;
    EditText requestName;
    Button saveRequest;
    AutoCompleteTextView textIn, quantity;
    Button buttonAdd;
    LinearLayout container;
    ArrayList<Supply> supplies = new ArrayList<>();
    ArrayAdapter<String> adapter;

    //Array of strings for adapter is needed.
    private static final String[] NUMBER = new String[]{
            "One", "Two", "Three"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);
        setViewModel();
        bindViews();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, NUMBER);
        quantity.setAdapter(adapter);
        textIn.setAdapter(adapter);
        saveRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupplies();
                addRequestViewModel.addRequest(new Request(requestName.getText().toString(), supplies));
                finish();
                Toast.makeText(getBaseContext(), "Request " + requestName.getText().toString() + " added successful.", Toast.LENGTH_LONG).show();
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.add_request_list, null);
                AutoCompleteTextView textOut = (AutoCompleteTextView) addView.findViewById(R.id.textOutName);
                textOut.setAdapter(adapter);
                textOut.setText(textIn.getText().toString());
                AutoCompleteTextView textOutQuantity = (AutoCompleteTextView) addView.findViewById(R.id.textOutQuantity);
                textOutQuantity.setAdapter(adapter);
                textOutQuantity.setText(quantity.getText().toString());
                Button buttonRemove = (Button) addView.findViewById(R.id.remove);

                final View.OnClickListener thisListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                };
                buttonRemove.setOnClickListener(thisListener);
                container.addView(addView);
                textIn.setText("");
                quantity.setText("");
            }
        });
    }

    private void getSupplies() {
        int childCount = container.getChildCount();
        TextView supplyName;
        TextView quantityNumber;
        for (int i = 0; i < childCount; i++) {
            View thisChild = container.getChildAt(i);
            supplyName = thisChild.findViewById(R.id.textOutName);
            quantityNumber = thisChild.findViewById(R.id.textOutQuantity);

            supplies.add(new Supply(supplyName.getText().toString(), Integer.parseInt(quantityNumber.getText().toString())));
        }
    }


    private void setViewModel() {
        addRequestViewModel = new ViewModelProvider(this).get(AddRequestViewModel.class);
    }

    private void bindViews() {
        requestName = (EditText) findViewById(R.id.editTextNameRequest);
        saveRequest = (Button) findViewById(R.id.addRequestButton);
        buttonAdd = (Button) findViewById(R.id.add);
        container = (LinearLayout) findViewById(R.id.container);
        quantity = (AutoCompleteTextView) findViewById(R.id.quantity);
        textIn = (AutoCompleteTextView) findViewById(R.id.nameProduct);
    }
}
