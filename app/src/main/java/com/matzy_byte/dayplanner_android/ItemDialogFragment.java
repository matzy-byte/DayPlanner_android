package com.matzy_byte.dayplanner_android;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.matzy_byte.dayplanner_android.data.TaskInterface;

public class ItemDialogFragment extends AppCompatDialogFragment {
    private TextView inputTask;
    private TaskInterface listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_item_dialog, null);
        builder.setView(dialogView);

        ImageButton btnClose = dialogView.findViewById(R.id.btn_close);
        inputTask = dialogView.findViewById(R.id.input_item_task);
        Button btnAccept = dialogView.findViewById(R.id.btn_accept_new_item);

        AlertDialog alertDialog = builder.create();

        btnClose.setOnClickListener(v -> dismiss());
        btnAccept.setOnClickListener(v -> {
            listener.onConfirm(inputTask.getText().toString());
            dismiss();
        });

        return alertDialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskInterface) {
            listener = (TaskInterface) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement DialogListener");
        }
    }
}
