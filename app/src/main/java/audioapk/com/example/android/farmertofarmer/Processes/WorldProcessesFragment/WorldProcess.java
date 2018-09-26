package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.Processes.FarmWorldDatabase;
import audioapk.com.example.android.farmertofarmer.R;


public class WorldProcess extends Fragment {

    private final ArrayList<WorldProcessCard> cardList = new ArrayList<>();
    private WorldProcessAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FarmWorldDatabase farmWorldDatabase;

    public WorldProcess() {
    }

    private String title;
    private int img;

    public void setCardValues(String title,int img){
        this.img = img;
        this.title = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.process_world, container, false);

        farmWorldDatabase = new FarmWorldDatabase(getActivity());


        mRecyclerView = view.findViewById(R.id.recyclerView3);
        mAdapter = new WorldProcessAdapter(getActivity(), cardList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initData();


        return view;
    }

    private void initData() {
        Cursor cursor = farmWorldDatabase.findFarms(title);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String process = cursor.getString(2);
            String land = cursor.getString(4);
            String date = cursor.getString(5);
            int profit = cursor.getInt(6);
            cardList.add(new WorldProcessCard(land,date,process,profit));
            mAdapter.notifyDataSetChanged();
        }
    }

}
