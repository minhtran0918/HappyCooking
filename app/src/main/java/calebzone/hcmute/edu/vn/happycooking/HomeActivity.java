package calebzone.hcmute.edu.vn.happycooking;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calebzone.hcmute.edu.vn.happycooking.adapters.PaperAdapter;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        referencedComponent();

        //setting tablayout
        tabLayout_home.addTab(tabLayout_home.newTab().setText("For Week"));
        tabLayout_home.addTab(tabLayout_home.newTab().setText("Recipes"));

        final PaperAdapter adapter = new PaperAdapter(getSupportFragmentManager(),tabLayout_home.getTabCount());
        viewPager_home.setAdapter(adapter);
        viewPager_home.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_home));

        tabLayout_home.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager_home.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //region REFERENCED COMPONENT
    private TabLayout tabLayout_home = null;
    private ViewPager viewPager_home = null;

    //method
    public void referencedComponent() {
        tabLayout_home = (TabLayout) findViewById(R.id.tabLayout_home);
        viewPager_home = (ViewPager) findViewById(R.id.viewPaper_home);
    }

    //endregion

    //region DESIGN
    private void designTabLayout() {

    }
    //endregion

}
