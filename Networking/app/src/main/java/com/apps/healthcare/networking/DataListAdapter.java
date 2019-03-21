package com.apps.healthcare.networking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<String> keys;
    private ArrayList<String> values;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    public DataListAdapter(Context context, ArrayList<String> keys, ArrayList<String> values) {
        mContext = context;
        this.keys = keys;
        this.values = values;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.key.setText(keys.get(position));
        holder.value.setText(values.get(position));
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView key;
        TextView value;
        public ViewHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
        }
    }
}
