package com.matzy_byte.dayplanner_android.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matzy_byte.dayplanner_android.R;

import java.util.List;

public class DayPlannerAdapter extends RecyclerView.Adapter<DayPlannerAdapter.ViewHolder> {
    private List<PlannedTask> itemList;

    public DayPlannerAdapter(List<PlannedTask> itemList) {
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItem;
        ImageButton btnCheck;
        ImageButton btnDelete;

        public ViewHolder(View view) {
            super(view);
            txtItem = view.findViewById(R.id.txt_item);
            btnCheck = view.findViewById(R.id.btn_item_check);
            btnDelete = view.findViewById(R.id.btn_item_delete);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtItem.setText(itemList.get(position).getText());
        if (itemList.get(position).getStatus()) {
            holder.btnCheck.setImageResource(androidx.constraintlayout.widget.R.drawable.abc_btn_check_to_on_mtrl_015);
        }
        holder.btnDelete.setOnClickListener(v -> removeItem(position));
        holder.btnCheck.setOnClickListener(v -> switchStatus(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addItem(String text) {
        itemList.add(new PlannedTask(text));
        notifyItemInserted(itemList.size() - 1);
    }

    public void switchStatus(int position) {
        if (position < itemList.size()) {
            itemList.get(position).switchStatus();
            notifyItemChanged(position);
        }
    }

    public void removeItem(int position) {
        if (position < itemList.size()) {
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());
        }
    }
}
