package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import audioapk.com.example.android.farmertofarmer.R;

public class WorldRootFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.world_root_fragment, container, false);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.world_root_frame, new World());

        transaction.commit();

        return view;
    }

}
