package com.hfad.wishlist;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {
    public ArrayList<Item> items = new ArrayList<Item>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        EditText et_itemName = (EditText) findViewById(R.id.et_itemName);
        EditText et_manufacturer = (EditText) findViewById(R.id.et_manufacturer);
        EditText et_price = (EditText) findViewById(R.id.et_price);
        EditText et_barcode = (EditText) findViewById(R.id.et_barcode);
        ArrayList<Item> existingItems = (ArrayList<Item>) getIntent().getSerializableExtra("existingitems");
        Item scannedItem = (Item) getIntent().getSerializableExtra("scanneditem");

        if (scannedItem != null) {
            et_itemName.setText(scannedItem.ItemName);
            et_manufacturer.setText(scannedItem.Manufacturer);
            et_price.setText(scannedItem.Price);
            et_barcode.setText(scannedItem.Barcode);
        }

        customStatusBar();

        Button btn_add_new_item = findViewById(R.id.btn_add_new_item);
        btn_add_new_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // no list has been created
                if (existingItems == null) {

                    if(scannedItem == null){
                        items.add(new Item(
                                et_itemName.getText().toString().trim(),
                                et_manufacturer.getText().toString().trim(),
                                et_price.getText().toString().trim(),
                                et_barcode.getText().toString().trim()));
                    }
                    else {
                        items.add(scannedItem);
                    }

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("items",items);
                    startActivity(intent);
                }
                else {

                    if(scannedItem == null){
                        existingItems.add(new Item(
                                et_itemName.getText().toString().trim(),
                                et_manufacturer.getText().toString().trim(),
                                et_price.getText().toString().trim(),
                                et_barcode.getText().toString().trim()));
                    }
                    else {
                        existingItems.add(scannedItem);
                    }

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("items", existingItems);
                    startActivity(intent);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void customStatusBar(){
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.coral));
    }
}
