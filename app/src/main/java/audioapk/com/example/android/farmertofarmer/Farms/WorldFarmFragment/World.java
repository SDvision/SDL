package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.R;


public class World extends Fragment{

    private ArrayList<WorldFarmCard> mSportsData;
    private WorldFarmsAdapter mAdapter;


    public World() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.farm_world, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerView);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSportsData = new ArrayList<>();

        mAdapter = new WorldFarmsAdapter(getActivity(), mSportsData);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();

//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
//                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView,
//                                  RecyclerView.ViewHolder viewHolder,
//                                  RecyclerView.ViewHolder target) {
//                int from = viewHolder.getAdapterPosition();
//                int to = target.getAdapterPosition();
//
//                Collections.swap(mSportsData, from, to);
//                mAdapter.notifyItemMoved(from, to);
//                return true;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder,
//                                 int direction) {
//                mSportsData.remove(viewHolder.getAdapterPosition());
//                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//            }
//        }).attachToRecyclerView(mRecyclerView);

        return rootView;
    }

    private void initializeData() {

        String[] sportsList = getResources()
                .getStringArray(R.array.farm_titles);
        String[] sportsInfo = getResources()
                .getStringArray(R.array.sports_info);
        TypedArray sportsImageResources = getResources()
                .obtainTypedArray(R.array.sports_images);

        mSportsData.clear();

        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new WorldFarmCard(sportsList[i], sportsInfo[i],
                    sportsImageResources.getResourceId(i, 0)));
        }

        sportsImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }

}
