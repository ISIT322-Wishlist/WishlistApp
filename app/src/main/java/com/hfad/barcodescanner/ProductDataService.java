package com.hfad.barcodescanner;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDataService {

    public static final String QUERY_FOR_BARCODE = "https://api.barcodelookup.com/v2/products?barcode=";
    public static final String FORMATTED_OPTION = "&formatted=y";
    public static final String API_KEY = "&key=9fdp5a4r2ylpnpozhpiafu7fu3isw5";

    Context context;

    public ProductDataService(Context context) {
        this.context = context;
    }

    public interface ProductByBarcodeResponse {
        void onError(String message);
        void onResponse(ProductModel productModel);
    }

    public void getProductByBarcode(String barcode, ProductByBarcodeResponse productByBarcodeResponse) {

        List<ProductModel> productModels = new ArrayList<>();

        String url = QUERY_FOR_BARCODE + barcode + FORMATTED_OPTION + API_KEY;


        // get the json object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray products_list = response.getJSONArray("products");

                    ProductModel one_product = new ProductModel();

                    JSONObject product_from_api = (JSONObject) products_list.get(0);

                    // Setting product properties returned from api call to new Product Model
                    one_product.setBarcode_number(product_from_api.getString("barcode_number"));
                    one_product.setBarcode_type(product_from_api.getString("barcode_type"));
                    one_product.setBarcode_formats(product_from_api.getString("barcode_formats"));
                    one_product.setMpn(product_from_api.getString("mpn"));
                    one_product.setModel(product_from_api.getString("model"));
                    one_product.setAsin(product_from_api.getString("asin"));
                    one_product.setProduct_name(product_from_api.getString("product_name"));
                    one_product.setTitle(product_from_api.getString("title"));
                    one_product.setCategory(product_from_api.getString("category"));
                    one_product.setManufacturer(product_from_api.getString("manufacturer"));
                    one_product.setBrand(product_from_api.getString("brand"));
                    one_product.setLabel(product_from_api.getString("label"));
                    one_product.setAuthor(product_from_api.getString("author"));
                    one_product.setPublisher(product_from_api.getString("publisher"));
                    one_product.setArtist(product_from_api.getString("artist"));
                    one_product.setActor(product_from_api.getString("actor"));
                    one_product.setDirector(product_from_api.getString("director"));
                    one_product.setStudio(product_from_api.getString("studio"));
                    one_product.setGenre(product_from_api.getString("genre"));
                    one_product.setAudience_rating(product_from_api.getString("audience_rating"));
                    one_product.setIngredients(product_from_api.getString("ingredients"));
                    one_product.setNutrition_facts(product_from_api.getString("nutrition_facts"));
                    one_product.setColor(product_from_api.getString("color"));
                    one_product.setFormat(product_from_api.getString("format"));
                    one_product.setPackage_quantity(product_from_api.getString("package_quantity"));
                    one_product.setSize(product_from_api.getString("size"));
                    one_product.setLength(product_from_api.getString("length"));
                    one_product.setWidth(product_from_api.getString("width"));
                    one_product.setHeight(product_from_api.getString("height"));
                    one_product.setWeight(product_from_api.getString("weight"));
                    one_product.setRelease_date(product_from_api.getString("release_date"));
                    one_product.setDescription(product_from_api.getString("description"));

                    // Adding new Product Model to callback so data is passed back to MainActivity.java
                    productByBarcodeResponse.onResponse(one_product);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Response error: No data found for barcode: " + barcode, Toast.LENGTH_SHORT).show();
            }
        });

        // Request is added to VolleyRequest queue
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
