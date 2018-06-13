package calebzone.hcmute.edu.vn.happycooking.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.Me;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeDetailFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "recipe_id";
    public static final String EXTRA_RECIPE_BUNDLE = "bla_bla";
    private RecipeModel mRecipeRoot;
    private RecipeDetailFragment mFragmentRoot;
    private Boolean mCheckFavorite = false;
    private Boolean mCheckFavoriteDefaul;

    private YouTubePlayerSupportFragment youTubePlayerFragment;
    private YouTubePlayer youTubePlayer;
    private String mRootYoutubeID;

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
        initializeYoutubePlayer();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_favorite_recipe);
        if (Me.checkFavorite(Integer.valueOf(mRecipeRoot.getId()))) {
            fab.setImageResource(R.drawable.ic_favorite_select);
            mCheckFavorite = true;
        }
        mCheckFavoriteDefaul = mCheckFavorite;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mess;
                if (mCheckFavorite) {
                    fab.setImageResource(R.drawable.ic_favorite_unselect);
                    mCheckFavorite = false;
                    mess = getApplicationContext().getResources().getString(R.string.fragment_recipe_detail_mess_favorite_unselect);
                } else {
                    fab.setImageResource(R.drawable.ic_favorite_select);
                    mCheckFavorite = true;
                    mess = getApplicationContext().getResources().getString(R.string.fragment_recipe_detail_mess_favorite_select);
                }
                Snackbar.make(view, mess, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPause() {
        if (mCheckFavoriteDefaul != mCheckFavorite) {
            if (mCheckFavorite) {
                Me.addFavorite(Integer.valueOf(mRecipeRoot.getId()));
            } else {
                Me.removeFavorite(Integer.valueOf(mRecipeRoot.getId()));
            }
        }
        super.onPause();
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

    private void initializeYoutubePlayer() {
        mRootYoutubeID = mRecipeRoot.getLink().toString();
        youTubePlayerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_player_fragment);

        if (youTubePlayerFragment == null)
            return;

        youTubePlayerFragment.initialize(Me.API_YOUTUBE, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;

                    //set the player style default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //cue the 1st video by default
                    youTubePlayer.cueVideo(mRootYoutubeID);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {

                //print or show error if initialization failed
                Log.e("aaaaaabbb", "Youtube Player View initialization failed");
            }
        });
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
}
