package calebzone.hcmute.edu.vn.happycooking.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.Logcat;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeDetailFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "recipe_id";
    public static final String EXTRA_RECIPE_BUNDLE = "bla_bla";
    private RecipeModel mRecipeRoot;
    private RecipeDetailFragment mFragmentRoot;

    public static Intent newIntent(Context context, RecipeModel recipeArrayListe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);

        /*Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_RECIPE_ID, recipeId);
        intent.putExtra(EXTRA_RECIPE_ID, bundle);*/
        // extras
        intent.putExtra(EXTRA_RECIPE_ID, recipeArrayListe);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        getData();
        bindData();
        loadFragment();
    }

    private void bindData() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mRecipeRoot.getName());
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarRecipeDetail);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarRecipeDetail);
        loadBackdrop();
    }

    private void loadBackdrop() {
        final ImageView imageView = findViewById(R.id.image_recipe);
        Picasso.get().load(mRecipeRoot.getImage()).placeholder(R.drawable.placeholder_empty).into(imageView);
    }

    private void getData() {
        Intent intent = getIntent();
        mRecipeRoot = (RecipeModel) intent.getSerializableExtra(EXTRA_RECIPE_ID);
    }

    private void setBundleData() {
        Bundle bundleData = new Bundle();
        bundleData.putSerializable(EXTRA_RECIPE_BUNDLE, mRecipeRoot);
        if (mFragmentRoot == null) {
            mFragmentRoot = new RecipeDetailFragment();
            mFragmentRoot.setArguments(bundleData);
        }
    }

    private void loadFragment() {
        //Config data

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        setBundleData();
        //TODO: load fagment
        fragmentTransaction.add(R.id.fragment_recipe_detail, mFragmentRoot);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_recipe_detail, menu);
        return true;
    }
}
