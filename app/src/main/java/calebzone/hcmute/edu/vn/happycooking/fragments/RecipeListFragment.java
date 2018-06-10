package calebzone.hcmute.edu.vn.happycooking.fragments;


import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.adapters.RecipeListAdapter;
import calebzone.hcmute.edu.vn.happycooking.database.GetDataFromWeb;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import calebzone.hcmute.edu.vn.happycooking.view.GridItemDecoration;

public class RecipeListFragment extends Fragment {

    private Context mRootContext;
    private View mRootView;
    private RecyclerView mRecyclerListRecipe = null;
    private ArrayList<RecipeModel> arrayListRecipe;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        mRootContext = mRootView.getContext();
        setupRecyclerView();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindData();
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

    private void bindData() {
        RecipeListAdapter recipeListAdapter;

        //TODO: Database sẽ kết nối đưa dữ liệu tại đây
        arrayListRecipe = new ArrayList<>();
        recipeListAdapter = new RecipeListAdapter(mRootView.getContext(), R.layout.list_food_for_week, arrayListRecipe);
        mRecyclerListRecipe.setAdapter(recipeListAdapter);
        loadData(arrayListRecipe);
        recipeListAdapter.notifyDataSetChanged();
    }

    private void loadData(ArrayList<RecipeModel> arrayListRecipe) {

        GetDataFromWeb getDataFromWeb = new GetDataFromWeb(mRootContext);
        getDataFromWeb.readData(GetDataFromWeb.ROOT, arrayListRecipe);
        /*arrayListRecipe.add(new RecipeModel(101, 1,"Phở gái 1", "Food", "bla","http://hot24h.org/wp-content/uploads/2017/12/hinh-gai-de-thuong.jpg","",0));
        arrayListRecipe.add(new RecipeModel(102, 2,"Phở gái 2", "Drink", "blo","http://i.imgur.com/DvpvklR.png","",0));*/
        //arrayListFood.add(new Food(106, "Phở gái 6", "Food", "http://i.imgur.com/DvpvklR.png"));
    }
    private void showAboutDialog() {
        // create and show the dialog
        DialogFragment newFragment = AboutDialogFragment.newInstance();
        newFragment.setTargetFragment(this, 0);
        newFragment.show(getFragmentManager(), "about");
    }


    //region Recycler View Setup
    private void setupRecyclerView() {
        //reference
        mRecyclerListRecipe = (RecyclerView) mRootView.findViewById(R.id.recyclerList_recipe);

        //set layout manager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), getGridSpanCount());
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerListRecipe.setLayoutManager(gridLayoutManager);
        /*LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(mRootView.getContext(), LinearLayoutManager.HORIZONTAL, false);*/
        /*//Config divider RecyclerView
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_divider_recycler_view));
        recyclerListRecipe.addItemDecoration(dividerItemDecoration);*/

        // set fixed size
        mRecyclerListRecipe.setHasFixedSize(true);

        // add decoration spacing (padding item on recyclerView)
        RecyclerView.ItemDecoration itemDecoration = new GridItemDecoration(getResources().getDimensionPixelSize(R.dimen.fragment_recipe_list_recycler_item_spacing));
        mRecyclerListRecipe.addItemDecoration(itemDecoration);

        // set animator
        mRecyclerListRecipe.setItemAnimator(new DefaultItemAnimator());
    }

    private int getGridSpanCount() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        float screenWidth = displayMetrics.widthPixels;
        float cellWidth = getResources().getDimension(R.dimen.fragment_recipe_list_recycler_size);
        return Math.round(screenWidth / cellWidth);
    }
    //endregion
}
