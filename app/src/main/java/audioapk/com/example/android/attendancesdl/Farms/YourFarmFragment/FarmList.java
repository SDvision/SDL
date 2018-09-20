package audioapk.com.example.android.attendancesdl.Farms.YourFarmFragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import audioapk.com.example.android.attendancesdl.R;


public class FarmList extends Fragment implements View.OnClickListener,AddFarmDialog.OnInputListener{


    private ArrayList<FarmCard> mSportsData;
    private FarmsAdapter mAdapter;

    public FarmList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.farm_you_farms, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerView);
        FloatingActionButton addFarmFloatingButton = rootView.findViewById(R.id.add_farm_floating_button);
        addFarmFloatingButton.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSportsData = new ArrayList<>();

        mAdapter = new FarmsAdapter(getActivity(), mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mSportsData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
        return rootView;
    }

    private void initializeData() {

        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources = getResources()
                .obtainTypedArray(R.array.sports_images);

        mSportsData.clear();

        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new FarmCard(sportsList[i], sportsInfo[i],
                    sportsImageResources.getResourceId(i, 0)));
        }

        sportsImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

        AddFarmDialog addFarmDialog = new AddFarmDialog();
        addFarmDialog.setOnInputListener(this);
        addFarmDialog.show(getActivity().getFragmentManager(),"Add Farm");

    }


    @Override
    public void setValues() {
        Toast.makeText(getActivity(),"Fragment",Toast.LENGTH_SHORT).show();
    }
}
