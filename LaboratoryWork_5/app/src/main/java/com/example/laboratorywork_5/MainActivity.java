package com.example.laboratorywork_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> productList;
    private CustomAdapter customAdapter;
    private TextView totalQuantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = generateSampleData();
        ListView listView = findViewById(R.id.listView);
        totalQuantityTextView = findViewById(R.id.totalQuantityTextView);
        Button showCheckedItemsButton = findViewById(R.id.showCheckedItemsButton);

        customAdapter = new CustomAdapter(this, productList, this::updateTotalQuantity);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggleProductSelection(position);
            }
        });

        showCheckedItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCheckedItems();
            }
        });
    }

    private void toggleProductSelection(int position) {
        Product selectedProduct = productList.get(position);
        selectedProduct.toggleSelected();
        customAdapter.notifyDataSetChanged();
        updateTotalQuantity();
    }

    private void updateTotalQuantity() {
        int totalQuantity = 0;

        for (Product product : productList) {
            if (product.isSelected()) {
                totalQuantity += product.getQuantity();
            }
        }

        totalQuantityTextView.setText("Общее количество: " + totalQuantity);
    }

    private void showCheckedItems() {
        Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
        intent.putParcelableArrayListExtra("selectedProducts", getSelectedProducts());
        startActivity(intent);
    }

    private ArrayList<Product> getSelectedProducts() {
        ArrayList<Product> selectedProducts = new ArrayList<>();

        for (Product product : productList) {
            if (product.isSelected()) {
                selectedProducts.add(product);
            }
        }

        return selectedProducts;
    }

    private ArrayList<Product> generateSampleData() {
        // Sample data generation (replace with your data source)
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Клавиатура", 673.99));
        products.add(new Product(2, "Компьютерная мышь", 486.99));
        products.add(new Product(3, "Монитор", 10439.99));
        products.add(new Product(4, "Системный блок", 2452.99));
        products.add(new Product(5, "Принтер", 13425.99));
        products.add(new Product(6, "Сканер", 7546.99));
        products.add(new Product(7, "Аудио-колонки", 350.99));
        products.add(new Product(8, "Геймпад", 1877.99));
        products.add(new Product(9, "Наушники", 2399.99));
        products.add(new Product(10, "Фотоаппарат", 44783.99));
        return products;
    }
}

