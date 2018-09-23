package audioapk.com.example.android.farmertofarmer.Farms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment.World;
import audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment.FarmYouList;
import audioapk.com.example.android.farmertofarmer.LogIn;
import audioapk.com.example.android.farmertofarmer.R;

public class FarmsMain extends AppCompatActivity {



    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_main);


        sharedPreferences = getSharedPreferences(LogIn.SHARED_FILE,MODE_PRIVATE);
        if (!sharedPreferences.contains(LogIn.LOGIN)){
            Intent intent = new Intent(this, LogIn.class);
            finish();
            startActivity(intent);
        }


        final TabLayout tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.farm_you_farms));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.farm_world));




        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return new FarmYouList();
                    case 1: return new World();
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
