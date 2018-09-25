package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import audioapk.com.example.android.farmertofarmer.DatePickerFragment;
import audioapk.com.example.android.farmertofarmer.R;

public class AddProcessDialog extends DialogFragment implements DatePickerFragment.OnDatePickListener {


    private EditText titleEdit,descriptionEdit;
    private TextView dateText;
    private String dateString;


    public AddProcessDialog() {}

    public interface ProcessAddListener{
        void newProcessCardAdded(ProcessCard processCard);
    }

    private ProcessAddListener processAddListener;
    public void setProcessAddListener(ProcessAddListener processAddListener) {
        this.processAddListener = processAddListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.process_you_add_dialog,container,false);
        titleEdit = view.findViewById(R.id.process_add_dialog_title_edit);
        descriptionEdit = view.findViewById(R.id.process_add_dialog_description_edit);
        dateText = view.findViewById(R.id.process_add_date_value);



        final Calendar c = Calendar.getInstance();
        final String day_string = Integer.toString(c.get(Calendar.DAY_OF_MONTH));              //day
        String month_string = Integer.toString(c.get(Calendar.MONTH) + 1);     //month
        String year_string = Integer.toString(c.get(Calendar.YEAR));             //year
        dateString = ( day_string +
                "/" + month_string +
                "/" + year_string);
        processDatePickerResult(dateString);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.setDatePickListener(AddProcessDialog.this);
                dateFragment.show(getActivity().getSupportFragmentManager(),"Date Picker");
            }
        });




        view.findViewById(R.id.process_add_action_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add Canceled",Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.process_add_action_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = titleEdit.getText().toString().trim();
                if (title.equals("")){
                    Toast.makeText(getActivity(),"Please Enter title",Toast.LENGTH_SHORT).show();
                    return;
                }
                String description = descriptionEdit.getText().toString().trim();
                if (description.equals("")){
                    Toast.makeText(getActivity(),"Please Enter title",Toast.LENGTH_SHORT).show();
                    return;
                }


                processAddListener.newProcessCardAdded(new ProcessCard(title,dateString,description));
                getDialog().dismiss();

            }
        });


        return view;
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
    public void processDatePickerResult(String date) {
        dateString = date;
        dateText.setText(date);
    }
}
