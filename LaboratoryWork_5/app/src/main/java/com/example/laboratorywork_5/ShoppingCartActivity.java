package com.example.laboratorywork_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {

    private ArrayList<Product> selectedProducts;
    private CustomAdapter shoppingCartAdapter;
    private TextView totalQuantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");

        ListView listView = findViewById(R.id.shoppingCartListView);
        totalQuantityTextView = findViewById(R.id.totalQuantityTextView);

        shoppingCartAdapter = new CustomAdapter(this, selectedProducts, this::updateTotalQuantity);
        listView.setAdapter(shoppingCartAdapter);

        Button orderButton = findViewById(R.id.orderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Обработка события нажатия на кнопку "Заказать"
                placeOrder();
            }
        });

        // Initial update of total quantity
        updateTotalQuantity();
    }

    private void placeOrder() {
        // Действия при заказе, например, отправка данных на сервер

        // Отображение уведомления
        Toast.makeText(this, "Заказ принят в работу. Ожидайте!", Toast.LENGTH_SHORT).show();
    }

    private void updateTotalQuantity() {
        int totalQuantity = 0;

        // Iterate through selected products and sum up their quantities
        for (Product product : selectedProducts) {
            totalQuantity += product.getQuantity();
        }

        // Update the total quantity display
        totalQuantityTextView.setText("Total Quantity: " + totalQuantity);
    }
}


