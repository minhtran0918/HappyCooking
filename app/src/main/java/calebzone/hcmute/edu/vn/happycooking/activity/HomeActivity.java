package calebzone.hcmute.edu.vn.happycooking.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.zip.Inflater;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeListFragment;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_CAT_ID = "cat_id";
    public RecipeListFragment mFragmentRoot;
    private int mRootCurrentId;
    public SearchView mRootSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setBundleData("0");
        mRootCurrentId = R.id.nav_all_recipe;
        loadFragment();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Boolean chk_match_fragment = false;
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id != mRootCurrentId) {
            switch (id) {
                case R.id.nav_all_recipe:
                    setBundleData("0");
                    //CheckUtil.createToast(this, "nav_all_recipe");
                    break;

                case R.id.nav_favorite:
                    setBundleData("0");
                    //CheckUtil.createToast(this, "nav_favorite");
                    break;
                case R.id.nav_suggest:
                    setBundleData("10");
                    //CheckUtil.createToast(this, "nav_favorite");
                    break;
                case R.id.nav_cake:
                    setBundleData("1");
                    //CheckUtil.createToast(this, "nav_cake");
                    break;

                case R.id.nav_soup:
                    setBundleData("2");
                    //CheckUtil.createToast(this, "nav_soup");
                    break;

                case R.id.nav_porriedge:
                    setBundleData("3");
                    //CheckUtil.createToast(this, "nav_porriedge");
                    break;

                case R.id.nav_fried_food:
                    setBundleData("4");
                    //CheckUtil.createToast(this, "nav_fried_food");
                    break;

                case R.id.nav_steaming:
                    setBundleData("5");
                    //CheckUtil.createToast(this, "nav_steaming");
                    break;

                case R.id.nav_appetized:
                    setBundleData("6");
                    //CheckUtil.createToast(this, "nav_appetized");
                    break;

                case R.id.nav_cook_with_sauce:
                    setBundleData("7");
                    //CheckUtil.createToast(this, "nav_cook_with_sauce");
                    break;

                case R.id.nav_stir_fry:
                    setBundleData("8");
                    //CheckUtil.createToast(this, "nav_stir_fry");
                    break;

                case R.id.nav_smoothies:
                    setBundleData("9");
                    //CheckUtil.createToast(this, "nav_smoothies");
                    break;
            }
            mRootCurrentId = id;
            loadFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fab_search, menu);
        mRootSearch = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        mRootSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFragmentRoot.getRecipeListAdapter().filter(newText.trim());
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.fragment_recipe_list_menu_login:
                break;
            case R.id.fragment_recipe_list_menu_rate:
                break;
            case R.id.fragment_recipe_list_menu_about:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBundleData(String cat_id) {
        mFragmentRoot = new RecipeListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CAT_ID, cat_id);
        mFragmentRoot.setArguments(bundle);
    }

    private void loadFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fragmentManager.popBackStack();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_recipe_list, mFragmentRoot);
        fragmentTransaction.commit();
    }
}
