package com.stichoza.usa2georgia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.stichoza.usa2georgia.data.FlightDataHolder;

import java.util.List;

/**
 * Created by Stichoza on 12/11/13.
 */
public class ListAdapter extends ArrayAdapter<FlightDataHolder> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
    }

    private List<FlightDataHolder> items;

    public ListAdapter(Context context, int resource, List<FlightDataHolder> items) {

        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        TextView tvDate = null;
        TextView tvName = null;
        TextView tvText = null;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.main_results_flights, null);

            tvName = (TextView) v.findViewById(R.id.name);
            tvDate = (TextView) v.findViewById(R.id.date);
            tvText = (TextView) v.findViewById(R.id.text);
        }

        FlightDataHolder p = items.get(position);

        if (p != null) {
            if (tvName != null) {
                tvName.setText("" + p.getName());
            }
            if (tvDate != null) {
                tvDate.setText("" + p.getDate());
            }
            if (tvText != null) {
                tvText.setText("" + p.getText());
            }

        }

        return v;
    }
}
