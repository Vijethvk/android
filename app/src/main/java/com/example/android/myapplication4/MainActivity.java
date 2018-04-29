package com.example.android.myapplication4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity extends Activity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        EditText text = (EditText) findViewById(R.id.name);

        String name = text.getText().toString();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.choco_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();



        int price = calculatePrice(hasWhippedCream,hasChocolate);


//        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
//                .putExtra(AlarmClock.EXTRA_MESSAGE, "Morning")
//                .putExtra(AlarmClock.EXTRA_HOUR, "7")
//                .putExtra(AlarmClock.EXTRA_MINUTES, "59")
//                .putExtra(AlarmClock.EXTRA_SKIP_UI,false);
//        Toast.makeText(this,"Alram set",Toast.LENGTH_SHORT).show();
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }


        String message = createOrderSummary(price,hasWhippedCream,hasChocolate,name);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if(intent.resolveActivity(getPackageManager())!=null);{
//            startActivity(intent);
//        }



        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("*/*");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"vijethvk11@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "verification");
        //intent.putExtra(Intent.EXTRA_CC,"vijethshivu@gmail.com");
       // intent.putExtra(Intent.EXTRA_STREAM, "gh");
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(message);

    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate,String name) {
        String priceMessage = "Name: "+name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: "+ NumberFormat.getCurrencyInstance().format(price);
        priceMessage += "\nThank you!";
        return priceMessage;
    }
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)
    {
       int  pricePerCup = 5;
        if(addWhippedCream){
            pricePerCup +=1;
        }
        if (addChocolate){
            pricePerCup +=2;
        }
        return quantity * pricePerCup;
    }

    public void decrement(View view) {
        if (quantity <= 1) {
            quantity = 1;
            Toast.makeText(this,"At least one cup dude",Toast.LENGTH_SHORT).show();
            return;
        }
        else
            quantity -= 1;
        displayMessage(quantity);
    }
    public void increment(View view) {
        quantity += 1;
        displayMessage(quantity);

    }
    private void displayMessage(int value) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + value);
    }

    /*private void displayMessage(String msg) {
        TextView msgTextView = findViewById(R.id.ordrer_summary__text_view);
        msgTextView.setText(msg);
    }*/

}