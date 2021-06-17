package com.hfad.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {
    public ArrayList<Item> items;
    public ArrayAdapter<Item> itemsAdapter;
    public ListView lvItems;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        customStatusBar();

        View inflatedView = getLayoutInflater().inflate(R.layout.activity_main, null);
        ListView list = inflatedView.findViewById(R.id.listView);

        // THIS IS CAUSING EXCEPTION
        //ADD HERE
        /*items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        itemsAdapter = new ArrayAdapter<>();
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();*/
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        items.remove(pos);
                        // Refresh the adapter
                        itemsAdapter.notifyDataSetChanged();
                        // Return true consumes the long click event (marks it handled)
                        return true;
                    }
                });
    }

    public void onAddNewItem(View view) {

        // Add item fields
        EditText et_itemName = (EditText) findViewById(R.id.et_itemName);
        EditText et_manufacturer = (EditText) findViewById(R.id.et_manufacturer);
        EditText et_price = (EditText) findViewById(R.id.et_price);
        EditText et_barcode = (EditText) findViewById(R.id.et_barcode);

        // Converts EditText fields to proper types
        String sItem = et_itemName.getText().toString().trim();
        String sManufacturer = et_manufacturer.getText().toString().trim();
        String sPrice = et_price.getText().toString().trim();
        String sBarcode = et_barcode.getText().toString().trim();

        // Prevents a null item
        if (sItem.isEmpty()) {
            et_itemName.setError("Item is required.");
            et_itemName.requestFocus();
            return;
        }

        /*EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();*/
        Item newItem = new Item(sItem, sManufacturer, sPrice, sBarcode);
        try {
            itemsAdapter.add(newItem);
            Toast.makeText(this, "Item added.", Toast.LENGTH_LONG).show();

        }catch (Exception ex){
            System.out.println(ex);
            Toast.makeText(this, "Failed to add item.", Toast.LENGTH_LONG).show();
        }

        // Resets EditText fields to empty
        et_itemName.setText("");
        et_manufacturer.setText("");
        et_price.setText("");
        et_barcode.setText("");

        // Returns User to ListView
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
