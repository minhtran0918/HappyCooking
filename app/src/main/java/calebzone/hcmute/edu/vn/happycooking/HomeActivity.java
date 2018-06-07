package calebzone.hcmute.edu.vn.happycooking;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.adapters.FoodAdapterListView;
import calebzone.hcmute.edu.vn.happycooking.adapters.FoodAdapterRecyclerView;
import calebzone.hcmute.edu.vn.happycooking.fragments.AboutDialogFragment;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeFragment;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity {

    //region REFERENCED COMPONENT
    //private TabLayout tabLayout_home = null;
    RecyclerView recyclerListFood;
    ArrayList<Food> arrayListFood;

    //method
    public void initView() {
        //tabLayout_home = (TabLayout) findViewById(R.id.tabLayout_home);
        recyclerListFood = (RecyclerView) findViewById(R.id.recyclerList_food);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        /*//Config divider RecyclerView
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider_recycler_view));
        recyclerListFood.addItemDecoration(dividerItemDecoration);*/

        recyclerListFood.setHasFixedSize(true);
        recyclerListFood.setLayoutManager(linearLayoutManager);
        /*recyclerListFood.setItemAnimator(new DefaultItemAnimator());*/


        arrayListFood = new ArrayList<>();
        arrayListFood.add(new Food("Phở gái 1", "Food", R.drawable.a));
        arrayListFood.add(new Food("Phở gái 2", "Drink", R.drawable.b));
        arrayListFood.add(new Food("Phở gái 3", "Food", R.drawable.b));
        arrayListFood.add(new Food("Phở gái 4", "Drink", R.drawable.a));
        arrayListFood.add(new Food("Phở gái 5", "Drink", R.drawable.a));
        arrayListFood.add(new Food("Phở gái 6", "Food", R.drawable.a));

        FoodAdapterRecyclerView foodAdapterRecyclerView =
                new FoodAdapterRecyclerView(getApplicationContext(), R.layout.list_food_for_week, arrayListFood);
        recyclerListFood.setAdapter(foodAdapterRecyclerView);
    }

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //initView();
        loadFragment(new RecipeFragment());

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
