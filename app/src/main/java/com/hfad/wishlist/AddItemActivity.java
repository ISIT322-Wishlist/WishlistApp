package com.hfad.wishlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class AddItemActivity extends Activity {
    private ArrayList<Item> items;
    private ArrayAdapter<Item> itemsAdapter;
    private ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        // ADD HERE
        /*lvItems = (ListView) findViewById(R.id.lvItems);*/
        items = new ArrayList<Item>();
        itemsAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        /*setupListViewListener();*/
    }

    /*private void setupListViewListener() {
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
    }*/

    public void onAddNewItem(View view) {

        // Add item fields
        EditText et_itemName = (EditText) findViewById(R.id.et_itemName);
        EditText et_manufacturer = (EditText) findViewById(R.id.et_manufacturer);
        EditText et_price = (EditText) findViewById(R.id.et_price);
        EditText et_barcode = (EditText) findViewById(R.id.et_barcode);

        // Converts EditText fields to proper types
        String sItem = et_itemName.getText().toString().trim();
        String sManufacturer = et_manufacturer.getText().toString().trim();
        Double sPrice = Double.parseDouble(et_price.getText().toString());
        String sBarcode = et_barcode.getText().toString().trim();

        // Prevents a null item
        if (sItem.isEmpty()) {
            et_itemName.setError("First name is required.");
            et_itemName.requestFocus();
            return;
        }

        /*EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();*/
        Item newItem = new Item(sItem, sManufacturer, sPrice, sBarcode);
        itemsAdapter.add(newItem);

        // Resets EditText fields to empty
        et_itemName.setText("");
        et_manufacturer.setText("");
        et_price.setText("");
        et_barcode.setText("");

        // Returns User to ListView
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
