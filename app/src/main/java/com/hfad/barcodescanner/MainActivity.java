package com.hfad.barcodescanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button scanBtn;
    private TextView productModelText;

    final ProductDataService productDataService = new ProductDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding views
        productModelText = findViewById(R.id.product_model_text);
        scanBtn = findViewById(R.id.scanBtn);
        scanBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    //Scans the code by setting an integrator to the capture and having the code make sure it can be sued in any orientation
    private  void scanCode(){
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

//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setMessage(result.getContents());
//                builder.setTitle("Scanning Result");
//                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        scanCode();
//                    }
//                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();

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
}