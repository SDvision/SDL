package audioapk.com.example.android.attendancesdl.Processes.WorldProcessesFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import audioapk.com.example.android.attendancesdl.R;


public class WorldProcess extends Fragment {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private WorldProcessAdapter mAdapter;
    private RecyclerView mRecyclerView;

    public WorldProcess() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.process_world, container, false);



        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Create recycler view.
        mRecyclerView = view.findViewById(R.id.recyclerView3);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WorldProcessAdapter(getActivity(), mWordList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

}
