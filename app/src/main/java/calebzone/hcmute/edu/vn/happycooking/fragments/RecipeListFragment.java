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
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.Logcat;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.activity.HomeActivity;
import calebzone.hcmute.edu.vn.happycooking.activity.RecipeDetailActivity;
import calebzone.hcmute.edu.vn.happycooking.adapters.RecipeListAdapter;
import calebzone.hcmute.edu.vn.happycooking.database.GetDataFromWeb;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import calebzone.hcmute.edu.vn.happycooking.database.retrofit.APIUtils;
import calebzone.hcmute.edu.vn.happycooking.database.retrofit.DataClient;
import calebzone.hcmute.edu.vn.happycooking.view.GridItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListFragment extends Fragment {

    private Context mRootContext;
    private View mRootView;
    private RecyclerView mRecyclerListRecipe = null;
    private ArrayList<RecipeModel> arrayListRecipe;
    private String mRootCatId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        mRootContext = mRootView.getContext();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromBundle();
        setupRecyclerView();
        loadDataFromBundle();
        bindData();
    }


    private void bindData() {
        RecipeListAdapter recipeListAdapter;
        //TODO: Database sẽ kết nối đưa dữ liệu tại đây
        arrayListRecipe = new ArrayList<RecipeModel>();
        recipeListAdapter = new RecipeListAdapter(mRootView.getContext(), R.layout.list_food_for_week, arrayListRecipe);
        /*if(mRootCatId == "1") {

        }
        else{
            arrayListRecipe.add(new RecipeModel(101, 1,"Phở gái 1", "Food", "bla","bla","http://hot24h.org/wp-content/uploads/2017/12/hinh-gai-de-thuong.jpg","",0));
        }*/
        mRootCatId = "1";
        loadRetrofit();
        //arrayListRecipe.add(new RecipeModel("101", "1", "Phở gái 1", "Food", "bla", "bla", "http://hot24h.org/wp-content/uploads/2017/12/hinh-gai-de-thuong.jpg", "", "0"));
        //arrayListRecipe.add(new RecipeModel("102", "2", "Phở gái 2, Phở gái 2 Phở gái 2", "Drink", "bla", "blo", "http://i.imgur.com/DvpvklR.png", "", "0"));
        /*GetDataFromWeb getDataFromWeb = new GetDataFromWeb(mRootContext);
        arrayListRecipe = getDataFromWeb.getRecipeModelArrayList();*/
        mRecyclerListRecipe.setAdapter(recipeListAdapter);
        recipeListAdapter.notifyDataSetChanged();
    }

   /* private ArrayList<RecipeModel> loadData(ArrayList<RecipeModel> arrayListRecipe) {

        GetDataFromWeb getDataFromWeb = new GetDataFromWeb(mRootContext);
        getDataFromWeb.readDataByCat(mRootCatId, arrayListRecipe);
        return getDataFromWeb.returnDataFromWeb();
    }*/

    private void showAboutDialog() {
        // create and show the dialog
        DialogFragment newFragment = AboutDialogFragment.newInstance();
        newFragment.setTargetFragment(this, 0);
        newFragment.show(getFragmentManager(), "about");
    }

    private void loadDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mRootCatId = bundle.getString(HomeActivity.EXTRA_CAT_ID);
        }
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
    /*@Override
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
    }*/
    private void loadRetrofit() {
        DataClient dataClient = APIUtils.getData();
        Call<List<RecipeModel>> callback = dataClient.getData(mRootCatId);
        callback.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                arrayListRecipe = (ArrayList<RecipeModel>) response.body();
                /*if (recipeArrayList.size() > 0) {
                    for (int i = 0; i < 5; i++) {
                        arrayListRecipe.add(new RecipeModel(
                                recipeArrayList.get(i).getId(),
                                recipeArrayList.get(i).getCatId(),
                                recipeArrayList.get(i).getName(),
                                recipeArrayList.get(i).getIntro(),
                                recipeArrayList.get(i).getIngredient(),
                                recipeArrayList.get(i).getInstruction(),
                                recipeArrayList.get(i).getImage(),
                                recipeArrayList.get(i).getLink(),
                                recipeArrayList.get(i).getFavorite()
                        ));
                        Logcat.d(recipeArrayList.get(i).getName());
                    }

                }*/
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                CheckUtil.createToast(mRootContext, "Error get data");
            }
        });
    }
}
