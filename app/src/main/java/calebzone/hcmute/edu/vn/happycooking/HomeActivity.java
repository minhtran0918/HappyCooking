package calebzone.hcmute.edu.vn.happycooking;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.adapters.FoodAdapter;
import calebzone.hcmute.edu.vn.happycooking.adapters.PaperAdapter;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity {

    //region REFERENCED COMPONENT
    private TabLayout tabLayout_home = null;
    private ViewPager viewPager_home = null;
    ListView listViewFood;
    ArrayList<Food> arrayListFood;
    FoodAdapter foodAdapter;

    //method
    public void referencedComponent() {
        tabLayout_home = (TabLayout) findViewById(R.id.tabLayout_home);
        viewPager_home = (ViewPager) findViewById(R.id.viewPaper_home);
        listViewFood = (ListView) findViewById(R.id.list_food);
        arrayListFood = new ArrayList<>();
        arrayListFood.add(new Food("Phở gái 1","Food",R.drawable.a));
        arrayListFood.add(new Food("Phở gái 2","Drink",R.drawable.b));
        arrayListFood.add(new Food("Phở gái 2","Drink",R.drawable.b));
        arrayListFood.add(new Food("Phở gái 2","Drink",R.drawable.b));
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        referencedComponent();

/*        //setting tablayout
        tabLayout_home.addTab(tabLayout_home.newTab().setText("For Week"));
        tabLayout_home.addTab(tabLayout_home.newTab().setText("Recipes"));

        final PaperAdapter adapter = new PaperAdapter(getSupportFragmentManager(),tabLayout_home.getTabCount());
        viewPager_home.setAdapter(adapter);
        viewPager_home.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_home));*/

        foodAdapter = new FoodAdapter(this,R.layout.list_food_for_week,arrayListFood);
        listViewFood.setAdapter(foodAdapter);

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



    //region DESIGN
    private void designTabLayout() {

    }
    //endregion

}
