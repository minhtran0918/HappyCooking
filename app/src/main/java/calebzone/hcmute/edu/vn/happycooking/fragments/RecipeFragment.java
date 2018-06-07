package calebzone.hcmute.edu.vn.happycooking.fragments;


import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.adapters.FoodAdapterRecyclerView;
import calebzone.hcmute.edu.vn.happycooking.model.Food;

public class RecipeFragment extends Fragment {
    private Context currentContext;

    //region REFERENCED COMPONENT
    //private TabLayout tabLayout_home = null;
    RecyclerView recyclerListFood;
    ArrayList<Food> arrayListFood;


    //method
    public void initView(View view) {
        //tabLayout_home = (TabLayout) findViewById(R.id.tabLayout_home);
        recyclerListFood = (RecyclerView) view.findViewById(R.id.recyclerList_food);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
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
        new FoodAdapterRecyclerView(view.getContext(), R.layout.list_food_for_week, arrayListFood);
        recyclerListFood.setAdapter(foodAdapterRecyclerView);
    }

    //endregion

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        currentContext = view.getContext();
        initView(view);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.fragment_recipe_home, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_login:
                break;
            case R.id.menu_rate:
                break;
            case R.id.menu_about:
                showAboutDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAboutDialog() {
        // create and show the dialog
        DialogFragment newFragment = AboutDialogFragment.newInstance();
        newFragment.setTargetFragment(this, 0);
        newFragment.show(getFragmentManager(), "about");
    }

}
