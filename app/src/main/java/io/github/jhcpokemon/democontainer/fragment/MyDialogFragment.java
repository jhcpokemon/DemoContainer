package io.github.jhcpokemon.democontainer.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import io.github.jhcpokemon.democontainer.R;

/**
 * Created by jhcpokemon on 09/02/15.
 */
public class MyDialogFragment extends DialogFragment {
    public static final String dialog_type = "DIALOG_TYPE";
    public static final int alert_dialog = 0;
    public static final int date_picker_dialog = 1;
    public static final int time_picker_dialog = 2;
    public TextView textView;

    public static MyDialogFragment newInstance(int dialogType) {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(dialog_type, dialogType);
        myDialogFragment.setArguments(bundle);
        return myDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        textView = (TextView) getActivity().findViewById(R.id.text_view);
        int type = getArguments().getInt(dialog_type);
        switch (type) {
            case alert_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getTag())
                        .setIcon(R.drawable.moon)
                        .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText("点击了OK按钮");
                            }
                        })
                        .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textView.setText("点击了取消按钮");
                            }
                        });
                return builder.create();
            case date_picker_dialog:
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = (calendar.get(Calendar.MONTH) + 1) % 12;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        textView.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
                    }
                }, year, month, day);
            case time_picker_dialog:
                final Calendar calendar1 = Calendar.getInstance();
                int hour = calendar1.get(Calendar.HOUR);
                int minute = calendar1.get(Calendar.MINUTE);
                return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textView.setText(hourOfDay + "时" + minute + "分");
                    }
                }, hour, minute, true);
        }
        return null;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
