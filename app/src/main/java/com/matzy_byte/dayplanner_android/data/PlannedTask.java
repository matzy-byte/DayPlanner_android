package com.matzy_byte.dayplanner_android.data;

public class PlannedTask {
    private boolean status;
    private String text;

    public PlannedTask(String text) {
        this.status = false;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void switchStatus() {
        this.status = !this.status;
    }

    public boolean getStatus() {
        return this.status;
    }
}
