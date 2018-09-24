package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import audioapk.com.example.android.farmertofarmer.DatePickerFragment;
import audioapk.com.example.android.farmertofarmer.R;

public class AddFarmDialog extends DialogFragment implements DatePickerFragment.OnDatePickListener {


    private Spinner selectFarmSpinner;
    private EditText landEditText;
    private TextView dateText;
    private int[] dateInt;


    public interface OnInputListener {
        void setValues(FarmCard farmCard);
    }


    private OnInputListener onInputListener;
    private TextView cancel,ok;

    public void setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
    }

    public AddFarmDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farm_you_farms_add_dialog,container,false);

        cancel = view.findViewById(R.id.farm_add_action_cancel);
        ok = view.findViewById(R.id.farm_add_action_ok);

        selectFarmSpinner = view.findViewById(R.id.farm_add_select_farm_spinner);
        landEditText = view.findViewById(R.id.farm_add_land_edit);


        dateText = view.findViewById(R.id.farm_add_date_value);//dateText.setText(new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()));
        final Calendar c = Calendar.getInstance();
        dateInt = new int[]{c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)};
        setDateText(dateInt);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.setDatePickListener(AddFarmDialog.this);
                dateFragment.show(getActivity().getSupportFragmentManager(),"Date Picker");


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add Canceled",Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectFarmSpinner.getSelectedItem() == null){
                    Toast.makeText(getActivity(),"Please Select Farm",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (landEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Please Select Land",Toast.LENGTH_SHORT).show();
                    return;
                }

                TypedArray farmImageResources = getResources().obtainTypedArray(R.array.sports_images);
                FarmCard newFarmCard = new FarmCard(selectFarmSpinner.getSelectedItem().toString(),
                        farmImageResources.getResourceId(selectFarmSpinner.getSelectedItemPosition(), 0),
                        Double.parseDouble(landEditText.getText().toString()),dateInt);
                farmImageResources.recycle();

                onInputListener.setValues(newFarmCard);

                getDialog().dismiss();
            }
        });


        return view;
    }

    public void setDateText(int[] date){
        dateInt = date;
        String day_string = Integer.toString(date[2]);              //day
        String month_string = Integer.toString(date[1] + 1);     //month
        String year_string = Integer.toString(date[0]);             //year
        String dateMessage = ( day_string +
                "/" + month_string +
                "/" + year_string);
        dateText.setText(dateMessage);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width,height);
        }
    }

    @Override
    public void processDatePickerResult(int[] date) {
        setDateText(date);
    }

}
