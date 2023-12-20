package com.example.laboratorywork_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> productList;
    private Runnable updateTotalQuantityCallback;

    public CustomAdapter(Context context, ArrayList<Product> productList, Runnable updateTotalQuantityCallback) {
        this.context = context;
        this.productList = productList;
        this.updateTotalQuantityCallback = updateTotalQuantityCallback;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemIdTextView = convertView.findViewById(R.id.itemIdTextView);
            viewHolder.itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
            viewHolder.itemPriceTextView = convertView.findViewById(R.id.itemPriceTextView);
            viewHolder.quantityEditText = convertView.findViewById(R.id.quantityEditText);
            viewHolder.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);

        viewHolder.itemIdTextView.setText(String.valueOf(product.getId()));
        viewHolder.itemNameTextView.setText(product.getName());
        viewHolder.itemPriceTextView.setText(String.valueOf(product.getPrice()));
        viewHolder.quantityEditText.setText(String.valueOf(product.getQuantity()));
        viewHolder.checkBox.setChecked(product.isSelected());

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.toggleSelected();
                notifyDataSetChanged(); // Notify the adapter that the data has changed
                updateTotalQuantity(); // Update the total quantity display
            }
        });

        return convertView;
    }

    private void updateTotalQuantity() {
        if (updateTotalQuantityCallback != null) {
            updateTotalQuantityCallback.run();
        }
    }

    private static class ViewHolder {
        TextView itemIdTextView;
        TextView itemNameTextView;
        TextView itemPriceTextView;
        EditText quantityEditText;
        CheckBox checkBox;
    }
}


