package audioapk.com.example.android.attendancesdl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import audioapk.com.example.android.attendancesdl.Farms.FarmsMain;

public class Profile extends AppCompatActivity {

    private Spinner state_Spinner;
    private Spinner district_Spinner;
    private Spinner taluka_Spinner;

    private Map<String,List<String>> districtToTaluka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);

        state_Spinner = findViewById(R.id.profile_stateSpinner);
        district_Spinner = findViewById(R.id.profile_districtSpinner);
        taluka_Spinner = findViewById(R.id.profile_talukaSpinner);


        final ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.india_states));
        final ArrayList<String> districtArrayList = new ArrayList<>();
        final ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,districtArrayList);

        district_Spinner.setAdapter(districtAdapter);
        state_Spinner.setAdapter(stateAdapter);

        state_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position !=0 && position != stateAdapter.getPosition("Maharashtra")){
                    Toast.makeText(Profile.this,"Only Maharashtra is available for now",Toast.LENGTH_SHORT).show();
                    state_Spinner.setSelection(stateAdapter.getPosition("Maharashtra"));

                    districtArrayList.clear();
                    districtArrayList.addAll(Arrays.asList(getResources().getStringArray(R.array.maharashtra_districts)));
                    districtAdapter.notifyDataSetChanged();

                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        district_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });




    }

    public void getStartedClicked(View view) {

        if (state_Spinner.getSelectedItem() == null || district_Spinner.getSelectedItem() == null || state_Spinner.getSelectedItemPosition() == 0 || district_Spinner.getSelectedItemPosition() == 0){
            Snackbar.make(findViewById(android.R.id.content),"State and District should be specified",Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, FarmsMain.class);
        startActivity(intent);





    }

    static Map<String,List<String>> getDistrictToTaluka(){


        Map<String,List<String>> districtToTaluka = new HashMap<>();

        districtToTaluka.put("Jalgaon",Arrays.asList("Jalgaon","chalisgao",""));


        return districtToTaluka;
    }

}
