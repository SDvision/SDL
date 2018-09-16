package audioapk.com.example.android.attendancesdl.Farms;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import audioapk.com.example.android.attendancesdl.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FarmList extends Fragment {


    public FarmList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.farm_you_farms, container, false);
    }

}
