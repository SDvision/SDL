package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;


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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.LogIn;
import audioapk.com.example.android.farmertofarmer.Processes.FarmWorldDatabase;
import audioapk.com.example.android.farmertofarmer.Processes.ProcessDatabase;
import audioapk.com.example.android.farmertofarmer.R;

import static android.content.Context.MODE_PRIVATE;

public class ProcessYouList extends Fragment implements AddProcessDialog.ProcessAddListener, View.OnClickListener,FinishFarmDialog.ProfitListener{

    private final ArrayList<ProcessCard> processCardList = new ArrayList<>();
    private ProcessesAdapter processesAdapter;
    private ProcessDatabase processDatabase;
    private Button finishFarmButton;
    private FarmWorldDatabase farmWorldDatabase;
    private FloatingActionButton fab;


    //TODO room
    private String processDPId,titleFarm,landFarm,dateFarm;
    private int imgFarm,farmId;
    private boolean notMineFarm = true;


    public ProcessYouList() {

    }

    public void setValues(String titleFarm,String landFarm,String dateFarm,int imgFarm,int farmId){
        this.titleFarm=titleFarm;
        this.landFarm=landFarm;
        this.dateFarm=dateFarm;
        this.imgFarm=imgFarm;
        this.farmId=farmId;
    }

    public void setNotMineFarm(String processDPId){
        this.processDPId = processDPId;
        this.notMineFarm = false;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.process_you_processes, container, false);


        finishFarmButton = view.findViewById(R.id.process_you_farm_finish);
        fab = view.findViewById(R.id.add_farm_floating_button2);
        CardView cardView = view.findViewById(R.id.process_farm_card);
        TextView farmTitle = cardView.findViewById(R.id.farm_card_title);
        ImageView farmImage = cardView.findViewById(R.id.farm_card_image);
        TextView landEdit = cardView.findViewById(R.id.farm_card_land);
        TextView dateEdit = cardView.findViewById(R.id.farm_card_date);

//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//        assert bundle != null;
//        farmId = bundle.getInt("farmId");
//        titleFarm = bundle.getString("title");
//        landFarm = String.valueOf(bundle.getDouble("land"));
//        dateFarm = bundle.getString("date");
//        imgFarm = bundle.getInt("image_resource");


        farmTitle.setText(titleFarm);
        landEdit.setText(landFarm);
        dateEdit.setText(dateFarm);
        Glide.with(getActivity()).load(imgFarm).into(farmImage);


        //TODO room
        if (notMineFarm) {
            String loginName = getActivity().getSharedPreferences(LogIn.SHARED_FILE, MODE_PRIVATE).getString(LogIn.LOGIN, "notFound");
            processDPId = String.valueOf(loginName + farmId);
        }

        processDatabase = new ProcessDatabase(getActivity(),processDPId);
        farmWorldDatabase = new FarmWorldDatabase(getActivity());


        int profit = farmWorldDatabase.CheckIfFarmIsFinished(processDPId);
        if ( profit != FarmWorldDatabase.NOT_FOUND){
            finishFarmButton.setText("This farm is finished with "+profit+" profit");
            fab.setVisibility(View.INVISIBLE);
        }else {
            //TODO room if already finished don't add dialog
            finishFarmButton.setOnClickListener(this);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AddProcessDialog addProcessDialog = new AddProcessDialog();
                    addProcessDialog.setProcessAddListener(ProcessYouList.this);
                    addProcessDialog.show(getActivity().getSupportFragmentManager(),"Add Farm");
                }
            });
        }



        RecyclerView recyclerView = view.findViewById(R.id.process_your_recyclerview);
        processesAdapter = new ProcessesAdapter(getActivity(), processCardList);
        recyclerView.setAdapter(processesAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initData();

        return view;
    }

    private void initData() {

        //TODO convert to room
        Cursor cursor = processDatabase.getAll();
        while (cursor.moveToNext()){
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String day = cursor.getString(3);
            processCardList.add(new ProcessCard(title,day,description));
        }
        cursor.close();
        processesAdapter.notifyDataSetChanged();

    }


    @Override
    public void newProcessCardAdded(ProcessCard processCard) {

        //TODO convert into room
        processDatabase.insetFarm(processCard.getTitle(),processCard.getDescription(),processCard.getDate());
        processCardList.add(processCard);
        processesAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {


        FinishFarmDialog finishProcessDialog = new FinishFarmDialog();
        finishProcessDialog.setProfitListener(this);
        finishProcessDialog.show(getActivity().getSupportFragmentManager(),"Add Farm");


    }

    @Override
    public void profitListen(int profit) {


        //TODO room
        Toast.makeText(getActivity(),"added to sql",Toast.LENGTH_SHORT).show();
        farmWorldDatabase.insetFarm(titleFarm,processDPId,imgFarm,landFarm,dateFarm,profit);

        finishFarmButton.setText("This farm is finished with "+profit+" profit");
        fab.setVisibility(View.INVISIBLE);



    }
}
