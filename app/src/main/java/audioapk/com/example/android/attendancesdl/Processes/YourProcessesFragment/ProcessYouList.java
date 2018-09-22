package audioapk.com.example.android.attendancesdl.Processes.YourProcessesFragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import audioapk.com.example.android.attendancesdl.R;

public class ProcessYouList extends Fragment {

    private final LinkedList<String> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ProcessesAdapter mAdapter;


    public ProcessYouList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.process_you_processes, container, false);


        FloatingActionButton fab = view.findViewById(R.id.add_farm_floating_button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        // Create recycler view.
        mRecyclerView = view.findViewById(R.id.recyclerView2);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new ProcessesAdapter(getActivity(), mWordList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setNestedScrollingEnabled(false);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
