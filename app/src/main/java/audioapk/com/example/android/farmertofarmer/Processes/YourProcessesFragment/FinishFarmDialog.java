package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import audioapk.com.example.android.farmertofarmer.R;

public class FinishFarmDialog extends DialogFragment{

    private ProfitListener profitListener;
    interface ProfitListener{ void profitListen(int profit);}
    public void setProfitListener(ProfitListener profitListener ) { this.profitListener = profitListener; }

    public FinishFarmDialog() {
    }

    private EditText profitEdit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.process_you_finish_farm_dialog,container,false);

        profitEdit = view.findViewById(R.id.process_finish_dialog_profit_edit);

        view.findViewById(R.id.process_finish_action_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String profit = profitEdit.getText().toString().trim();
                if (profit.equals("")){
                    Toast.makeText(getActivity(),"Please enter your profit",Toast.LENGTH_SHORT).show();
                    return;
                }
                profitListener.profitListen(Integer.parseInt(profit));
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.process_finish_action_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Finish Canceled",Toast.LENGTH_SHORT).show();
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
}
