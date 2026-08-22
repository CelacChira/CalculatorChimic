package com.example.calculatorchimic;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public ChemData chemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Incarcam o singura data datele din JSON, le folosesc toate cele 3 file
        chemData = new ChemData(this);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Adapter simplu pentru cele 3 tab-uri
    public static class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new MasaMolaraFragment();
                case 1: return new ConcentratieFragment();
                default: return new TipStareFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0: return "Masa Molară";
                case 1: return "Concentrație";
                default: return "Tip și Stare";
            }
        }
    }
}
