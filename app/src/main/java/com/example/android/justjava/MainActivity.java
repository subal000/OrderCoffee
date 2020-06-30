/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity=2;
    public void submitOrder(View view) {
        EditText name = findViewById(R.id.name);
        String naam = name.getText().toString();
        int price = calculatePrice();
        String priceMessage= createOrderSummary(naam);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order for " + naam);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(naam));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void increment(View view){
        if(quantity==100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        displayQuantity(quantity);
    }
    public void decrement(View view){
        if (quantity==1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity=quantity-1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice() {
        int price=5;
        int whippedCream= 1;
        int choc = 2;
        int addPrice=0;
        CheckBox chocolate = findViewById(R.id.chocolate);
        Boolean isChocolate= chocolate.isChecked();
        CheckBox whippedCreme = findViewById(R.id.whippedCream);
        Boolean whipped = whippedCreme.isChecked();
        if(isChocolate) {
            addPrice=addPrice+1;
        }
        if(whipped){
            addPrice=addPrice+2;
        }
        price = (price+addPrice)*quantity;
        return price;
    }
    private String createOrderSummary(String name){
        CheckBox chocolate = findViewById(R.id.chocolate);
        Boolean isChocolate= chocolate.isChecked();
        CheckBox whippedCream = findViewById(R.id.whippedCream);
        Boolean whipped = whippedCream.isChecked();
        String whi = whipped.toString();
        String priceMessage= "Name: "+ name;
        String cream = "\nAdd whipped cream? " + whi ;
        String choco= "\nAdd chocolate? " + isChocolate;
        priceMessage = priceMessage + cream;
        priceMessage= priceMessage + choco;
        priceMessage=priceMessage+ "\nQuantity: " + quantity;
        priceMessage=priceMessage+"\nTotal: $"+ calculatePrice();
        return priceMessage + "\nThank You!";
    }
}