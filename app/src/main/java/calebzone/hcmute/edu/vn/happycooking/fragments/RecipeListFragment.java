package calebzone.hcmute.edu.vn.happycooking.fragments;


import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.Logcat;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.My;
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
    private YouTubePlayerView mRootYoutube;
    RecipeListAdapter recipeListAdapter;

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
        setupRecyclerView();
        bindData();
        loadDataFromBundle();
        arrayListRecipe = new ArrayList<RecipeModel>();
        loadRetrofit();
    }

    private void bindData() {


        //arrayListRecipe.add(new RecipeModel("101", "1","Phở gái 1", "Food", "bla", "bla", "http://hot24h.org/wp-content/uploads/2017/12/hinh-gai-de-thuong.jpg","",""));
        //arrayListRecipe.add(new RecipeModel("102","2", "Phở gái 2, Phở gái 2 Phở gái 2", "Drink", "bla", "blo", "http://i.imgur.com/DvpvklR.png","",""));
        /*GetDataFromWeb getDataFromWeb = new GetDataFromWeb(mRootContext);
        arrayListRecipe = getDataFromWeb.getRecipeModelArrayList();*/
        /*recipeListAdapter = new RecipeListAdapter(mRootView.getContext(), R.layout.list_food_for_week, arrayListRecipe);
        mRecyclerListRecipe.setAdapter(recipeListAdapter);
        recipeListAdapter.notifyDataSetChanged();*/
    }

   /* private ArrayList<RecipeModel> loadData(ArrayList<RecipeModel> arrayListRecipe) {

        GetDataFromWeb getDataFromWeb = new GetDataFromWeb(mRootContext);
        getDataFromWeb.readDataByCat(mRootCatId, arrayListRecipe);
        return getDataFromWeb.returnDataFromWeb();
    }*/

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


    private void loadRetrofit() {
        //TODO: Database sẽ kết nối đưa dữ liệu tại đây
        DataClient dataClient = APIUtils.getData();
        Call<List<RecipeModel>> callback = dataClient.getData(mRootCatId);
        callback.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                ArrayList<RecipeModel> recipeArrayList = (ArrayList<RecipeModel>) response.body();
                if (recipeArrayList.size() > 0) {
                    String id, name, intro, ingredient, instruction, image, link;
                    for (int i = 0; i < recipeArrayList.size(); i++) {
                        id = recipeArrayList.get(i).getId();
                        name = recipeArrayList.get(i).getName();
                        intro = recipeArrayList.get(i).getIntro();
                        ingredient = recipeArrayList.get(i).getIngredient().toString();
                        instruction = recipeArrayList.get(i).getInstruction();
                        image = recipeArrayList.get(i).getImage();
                        link = recipeArrayList.get(i).getLink().toString();
                        arrayListRecipe.add(new RecipeModel(id, "1", name, intro, ingredient, instruction, image, link, ""));
                    }
                }
                recipeListAdapter = new RecipeListAdapter(mRootView.getContext(), R.layout.list_food_for_week, arrayListRecipe);
                mRecyclerListRecipe.setAdapter(recipeListAdapter);
                recipeListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                Snackbar.make(mRootView, R.string.error_load_data_retrofit, Snackbar.LENGTH_LONG);
            }
        });
    }

}
