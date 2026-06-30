package com.example.pr19fedotov;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    public interface DialogListener {
        void onDialogPositiveClick(DialogFragment dialog, String result);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private DialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DialogListener) {
            listener = (DialogListener) context;
        } else {
            throw new ClassCastException(context.toString() + " должен реализовывать DialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        // Получаем данные, переданные из MainActivity
        String passedData = "";
        if (getArguments() != null) {
            passedData = getArguments().getString("current_time", "Данные не переданы");
        }

        // Поле ввода внутри диалога
        final EditText input = new EditText(getActivity());
        input.setHint("Введите ваш текст");

        builder.setTitle("Кастомный диалог")
                .setMessage("Дата:\n" + passedData)
                .setView(input)
                .setPositiveButton("Отправить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onDialogPositiveClick(CustomDialogFragment.this, input.getText().toString());
                        }
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onDialogNegativeClick(CustomDialogFragment.this);
                        }
                    }
                });

        return builder.create();
    }
}
