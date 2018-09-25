package audioapk.com.example.android.farmertofarmer;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment.AddFarmDialog;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public interface OnDatePickListener {
        void processDatePickerResult(String date);
    }
    private OnDatePickListener datePickListener;

    public void setDatePickListener(OnDatePickListener datePickListener) {
        this.datePickListener = datePickListener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        if (datePickListener != null && datePickListener instanceof AddFarmDialog){

            String day_string = Integer.toString(day);              //day
            String month_string = Integer.toString(month + 1);     //month
            String year_string = Integer.toString(year);             //year
            String dateMessage = ( day_string +
                    "/" + month_string +
                    "/" + year_string);

            datePickListener.processDatePickerResult(dateMessage);
        }
    }
}

