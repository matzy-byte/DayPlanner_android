package com.matzy_byte.dayplanner_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.matzy_byte.dayplanner_android.data.DayPlannerAdapter;
import com.matzy_byte.dayplanner_android.data.PlannedTask;
import com.matzy_byte.dayplanner_android.data.TaskInterface;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskInterface {
    private ImageButton btnAdd;
    private RecyclerView list;
    private DayPlannerAdapter adapter;
    private List<PlannedTask> itemList;
    private TextView txtEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();

        adapter = new DayPlannerAdapter(itemList);
        list.setAdapter(adapter);

        btnAdd = findViewById(R.id.btn_add_item);
        btnAdd.setOnClickListener(v -> {
            ItemDialogFragment dialog = new ItemDialogFragment();
            dialog.show(getSupportFragmentManager(), "NewItemDialog");
        });

        txtEmpty = findViewById(R.id.txt_empty);
        if (!itemList.isEmpty()) {
            txtEmpty.setVisibility(View.GONE);
        }

    }

    @Override
    public void onConfirm(String task) {
        adapter.addItem(task);
        txtEmpty.setVisibility(View.GONE);
    }
}
