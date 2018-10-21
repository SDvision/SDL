package audioapk.com.example.android.farmertofarmer.Processes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;

import audioapk.com.example.android.farmertofarmer.LogIn.LogIn;
import audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment.WorldProcessRootFragment;
import audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment.ProcessYouList;
import audioapk.com.example.android.farmertofarmer.LogIn.Profile;
import audioapk.com.example.android.farmertofarmer.R;

public class ProcessesMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_main);

        SharedPreferences sharedPreferences = getSharedPreferences(LogIn.SHARED_FILE, MODE_PRIVATE);
        if (!sharedPreferences.contains(LogIn.DISTRICT)){
            Intent intent = new Intent(this, Profile.class);
            finish();
            startActivity(intent);
            return;
        }
        Objects.requireNonNull(getSupportActionBar()).setTitle(sharedPreferences.getString(LogIn.DISTRICT,"Location Not Found"));


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        int farmId = bundle.getInt("farmId");
        String titleFarm = bundle.getString("title");
        String landFarm = String.valueOf(bundle.getDouble("land"));
        String dateFarm = bundle.getString("date");
        int imgFarm = bundle.getInt("image_resource");
        final ProcessYouList processYouList = new ProcessYouList();
        processYouList.setValues(titleFarm,landFarm,dateFarm,imgFarm,farmId);

        final WorldProcessRootFragment worldProcessRootFragment = new WorldProcessRootFragment();
        worldProcessRootFragment.setCardValues(titleFarm,imgFarm);


        final TabLayout tabLayout = findViewById(R.id.tab_layout2);
        tabLayout.addTab(tabLayout.newTab().setText("My processes"));
        tabLayout.addTab(tabLayout.newTab().setText("World Process"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager2);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return processYouList;
                    case 1: return worldProcessRootFragment;
                    default: return null;
                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        viewPager.setCurrentItem(tab.getPosition());
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

    }
}
