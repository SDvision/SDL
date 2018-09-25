package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;


import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.LogIn;
import audioapk.com.example.android.farmertofarmer.Processes.ProcessDatabase;
import audioapk.com.example.android.farmertofarmer.R;

import static android.content.Context.MODE_PRIVATE;

public class ProcessYouList extends Fragment implements AddProcessDialog.ProcessAddListener{

    private final ArrayList<ProcessCard> processCardList = new ArrayList<>();
    private ProcessesAdapter processesAdapter;
    private ProcessDatabase processDatabase;

    public ProcessYouList() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.process_you_processes, container, false);



        CardView cardView = view.findViewById(R.id.process_farm_card);
        TextView farmTitle = cardView.findViewById(R.id.farm_card_title);
        ImageView farmImage = cardView.findViewById(R.id.farm_card_image);
        TextView landEdit = cardView.findViewById(R.id.farm_card_land);
        TextView noProcess = cardView.findViewById(R.id.farm_card_no_process);
        TextView dateEdit = cardView.findViewById(R.id.farm_card_date);
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        assert bundle != null;
        int farmId = bundle.getInt("farmId");
        if (intent.hasExtra("title") &&
                intent.hasExtra("image_resource") &&
                intent.hasExtra("land") &&
                intent.hasExtra("date"))
        {
            farmTitle.setText(bundle.getString("title"));
            landEdit.setText(String.valueOf(bundle.getDouble("land")));
            noProcess.setText("0");  //TODO

            dateEdit.setText(bundle.getString("date"));

            Glide.with(getActivity()).load(bundle.getInt("image_resource")).into(farmImage);

        }


        //TODO room
        String loginName = getActivity().getSharedPreferences(LogIn.SHARED_FILE,MODE_PRIVATE).getString(LogIn.LOGIN,"notFound");
        processDatabase = new ProcessDatabase(getActivity(),String.valueOf(loginName+farmId));





        FloatingActionButton fab = view.findViewById(R.id.add_farm_floating_button2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddProcessDialog addProcessDialog = new AddProcessDialog();
                addProcessDialog.setProcessAddListener(ProcessYouList.this);
                addProcessDialog.show(getActivity().getSupportFragmentManager(),"Add Farm");
            }
        });



        // Create recycler view.
        RecyclerView recyclerView = view.findViewById(R.id.process_your_recyclerview);
        // Create an adapter and supply the data to be displayed.
        processesAdapter = new ProcessesAdapter(getActivity(), processCardList);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(processesAdapter);

        recyclerView.setNestedScrollingEnabled(false);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Put initial data into the word list.
        initData(bundle);

        return view;
    }

    private void initData(Bundle bundle) {

        //TODO convert to room
        Cursor cursor = processDatabase.getAll();
        while (cursor.moveToNext()){
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String day = cursor.getString(3);
            processCardList.add(new ProcessCard(title,day,description));
        }
        processesAdapter.notifyDataSetChanged();

    }


    @Override
    public void newProcessCardAdded(ProcessCard processCard) {

        //TODO convert into room
        processDatabase.insetFarm(processCard.getTitle(),processCard.getDescription(),processCard.getDate());

        processCardList.add(processCard);
        processesAdapter.notifyDataSetChanged();

    }
}
