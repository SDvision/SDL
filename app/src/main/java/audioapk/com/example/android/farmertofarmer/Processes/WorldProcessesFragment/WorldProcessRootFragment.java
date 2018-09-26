package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import audioapk.com.example.android.farmertofarmer.R;

public class WorldProcessRootFragment extends Fragment {


    private String title;
    private int img;

    public void setCardValues(String title,int img){
        this.img = img;
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.world_root_fragment, container, false);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        WorldProcess worldProcess = new WorldProcess();
        worldProcess.setCardValues(title,img);

        transaction.replace(R.id.world_root_frame, worldProcess);

        transaction.commit();

        return view;
    }

}
