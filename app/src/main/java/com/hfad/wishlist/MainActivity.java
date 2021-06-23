package com.hfad.wishlist;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.DialogInterface;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {

    DatabaseReference dbReff;
    TextView nameHeader;
    ArrayList<Item> itemsList = new ArrayList<Item>();
    ArrayAdapter itemsAdapter;


    //public ArrayAdapter<Item> itemsAdapter;

    //Button scanBtn;

    private TextView productModelText;

    final ProductDataService productDataService = new ProductDataService(MainActivity.this);

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameHeader = (TextView) findViewById(R.id.welcome_header);
        // grab the current logged in user
        dbReff = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        dbReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameHeader.setText(snapshot.child("firstName").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        itemsAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, itemsList);

        // Gets list of items from AddItemActivity
        itemsList = (ArrayList<Item>) getIntent().getSerializableExtra("items");

        if(itemsList != null)
        {
            ListView listView = findViewById(R.id.listView);
            itemsAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, itemsList);
            listView.setAdapter(itemsAdapter);
        }

        Button addItemBtn = findViewById(R.id.addItem);
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemsAdapter.isEmpty()){
                    // ListView is Empty, creating new list
                    Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                    startActivity(intent);
                }
                else
                {
                    // Send existing list to add more items
                    Intent intent = new Intent(getApplicationContext(),AddItemActivity.class);
                    intent.putExtra("existingitems", itemsList);
                    startActivity(intent);
                }
            }
        });;

        // Binding views
        //productModelText = findViewById(R.id.product_model_text);
        /*scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);*/

        customStatusBar();
    }

    /*@Override
    public void onClick(View v) {
        scanCode();
    }*/

    //Scans the code by setting an integrator to the capture and having the code make sure it can be sued in any orientation
    public void scanCode(View view){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    //Checks if the barcode you scanned is valid and then show what the barcode represents
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents()!= null){

                String returnedBarcode = result.getContents();
                displayBarcodeInfo(returnedBarcode);
            }
            else{
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void displayBarcodeInfo(String returnedBarcode) {

        productDataService.getProductByBarcode(returnedBarcode, new ProductDataService.ProductByBarcodeResponse() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(ProductModel productModel) {
                productModelText.setText(productModel.toString());
            }
        });
    }

//    public void onAddItem(View view){
//        Intent intent = new Intent(this, AddItemActivity.class);
//        startActivity(intent);
//    }

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
