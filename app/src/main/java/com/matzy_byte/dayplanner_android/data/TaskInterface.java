package com.matzy_byte.dayplanner_android.data;

public interface TaskInterface {
    void onConfirm(String task);
    void onChange(String change, int position);
}
