package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.LinkedList;

import audioapk.com.example.android.farmertofarmer.R;

public class ProcessYouList extends Fragment {

    private final LinkedList<String> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private ProcessesAdapter mAdapter;

    private TextView farmTitle,landEdit,noProcess,dateEdit;
    private ImageView farmImage;



    public ProcessYouList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.process_you_processes, container, false);



        CardView cardView = view.findViewById(R.id.process_farm_card);
        farmTitle = cardView.findViewById(R.id.farm_card_title);
        farmImage = cardView.findViewById(R.id.farm_card_image);
        landEdit = cardView.findViewById(R.id.farm_card_land);
        noProcess = cardView.findViewById(R.id.farm_card_no_process);
        dateEdit = cardView.findViewById(R.id.farm_card_date);
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();
        if (intent.hasExtra("title") &&
                intent.hasExtra("image_resource") &&
                intent.hasExtra("land") &&
                intent.hasExtra("date"))
        {



            assert bundle != null;
            farmTitle.setText(bundle.getString("title"));
            landEdit.setText(String.valueOf(bundle.getDouble("land")));
            noProcess.setText("0");  //TODO

            int [] date = bundle.getIntArray("date");
            assert date != null;
            String day_string = Integer.toString(date[2]);              //day
            String month_string = Integer.toString(date[1] + 1);     //month
            String year_string = Integer.toString(date[0]);             //year
            String dateMessage = (day_string+
                    "/" +  month_string +
                    "/" + year_string);

            dateEdit.setText(dateMessage);

            Glide.with(getActivity()).load(bundle.getInt("image_resource")).into(farmImage);

        }









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
        initData(bundle);

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

    private void initData(Bundle bundle) {

        //TODO find on sql and add
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

    }


}
