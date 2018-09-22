package audioapk.com.example.android.attendancesdl.Farms.YourFarmFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import audioapk.com.example.android.attendancesdl.R;

class AddFarmDialog extends DialogFragment {


    public interface OnInputListener {
        void setValues();
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

        cancel = view.findViewById(R.id.action_cancel);
        ok = view.findViewById(R.id.action_ok);

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
                Toast.makeText(getActivity(),"Farm Added",Toast.LENGTH_SHORT).show();
                onInputListener.setValues();
                getDialog().dismiss();
            }
        });


        return view;
    }


}
