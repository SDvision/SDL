package audioapk.com.example.android.attendancesdl.Processes;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import audioapk.com.example.android.attendancesdl.Processes.YourProcessesFragment.ProcessYouList;
import audioapk.com.example.android.attendancesdl.Processes.WorldProcessesFragment.WorldProcess;
import audioapk.com.example.android.attendancesdl.R;

public class ProcessesMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_main);

        final TabLayout tabLayout = findViewById(R.id.tab_layout2);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.farm_you_farms));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.farm_world));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager2);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0: return new ProcessYouList();
                    case 1: return new WorldProcess();
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
