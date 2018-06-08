package calebzone.hcmute.edu.vn.happycooking.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeListFragment;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity {

    //region REFERENCED COMPONENT
    //private TabLayout tabLayout_home = null;

    //method
    public void initView() {
        //tabLayout_home = (TabLayout) findViewById(R.id.tabLayout_home);
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //initView();
        loadFragment(new RecipeListFragment());

/*        //setting tablayout
        tabLayout_home.addTab(tabLayout_home.newTab().setText("For Week"));
        tabLayout_home.addTab(tabLayout_home.newTab().setText("Recipes"));

        final PaperAdapter adapter = new PaperAdapter(getSupportFragmentManager(),tabLayout_home.getTabCount());
        viewPager_home.setAdapter(adapter);
        viewPager_home.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout_home));
        FoodAdapterListView foodAdapterListView = new FoodAdapterListView(this,R.layout.list_food_for_week,arrayListFood);
        listViewFood.setAdapter(foodAdapterListView);*/

    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_recipe_list,fragment);
        fragmentTransaction.commit();
    }
}
