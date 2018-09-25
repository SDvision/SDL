package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.Farms.FarmDatabase;
import audioapk.com.example.android.farmertofarmer.LogIn;
import audioapk.com.example.android.farmertofarmer.R;

import static android.content.Context.MODE_PRIVATE;


public class FarmYouList extends Fragment implements View.OnClickListener,AddFarmDialog.OnInputListener{


    private ArrayList<FarmCard> mFarmData;
    private FarmsAdapter mAdapter;
    private FarmDatabase farmDatabase;

    public FarmYouList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.farm_you_farms, container, false);

        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerView);
        FloatingActionButton addFarmFloatingButton = rootView.findViewById(R.id.add_farm_floating_button);
        addFarmFloatingButton.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mFarmData = new ArrayList<>();

        mAdapter = new FarmsAdapter(getActivity(), mFarmData);
        mRecyclerView.setAdapter(mAdapter);

        //TODO room
        farmDatabase = new FarmDatabase(getActivity(),getActivity().getSharedPreferences(LogIn.SHARED_FILE,MODE_PRIVATE).getString(LogIn.LOGIN,"notFound"));

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
//                Collections.swap(mFarmData, from, to);
//                mAdapter.notifyItemMoved(from, to);
//                return true;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder,
//                                 int direction) {
//                mFarmData.remove(viewHolder.getAdapterPosition());
//                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
//            }
//        }).attachToRecyclerView(mRecyclerView);

        return rootView;
    }

    private void initializeData() {

        mFarmData.clear();


        //TODO convert to room database
        Cursor cursor = farmDatabase.getAll();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int img = cursor.getInt(2);
            double land = Double.parseDouble(cursor.getString(3));
            String day = cursor.getString(4);
            FarmCard farmCard = new FarmCard(title,img,land,day);
            farmCard.setDatabaseId(id);
            mFarmData.add(farmCard);

        }



//        String[] farmNameList = getResources().getStringArray(R.array.farm_titles);
//        TypedArray farmImageResources = getResources().obtainTypedArray(R.array.sports_images);
//        for (int i = 0; i < farmNameList.length; i++) {
//            mFarmData.add(new FarmCard(farmNameList[i],
//                    farmImageResources.getResourceId(i, 0),0, ""));
//        }
//        farmImageResources.recycle();

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {

        AddFarmDialog addFarmDialog = new AddFarmDialog();
        addFarmDialog.setOnInputListener(this);
        addFarmDialog.show(getActivity().getSupportFragmentManager(),"Add Farm");

    }


    @Override
    public void setValues(FarmCard farmCard) {

        Toast.makeText(getActivity(),"Card added",Toast.LENGTH_SHORT).show();


        //TODO convert into room
        int id = farmDatabase.insetFarm(farmCard.getTitle(),farmCard.getImageResource(),String.valueOf(farmCard.getLandArea()),farmCard.getDay());
        farmCard.setDatabaseId(id);

        mFarmData.add(farmCard);
        mAdapter.notifyDataSetChanged();
    }
}
