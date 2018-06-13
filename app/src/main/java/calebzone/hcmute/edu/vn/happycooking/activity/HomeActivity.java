package calebzone.hcmute.edu.vn.happycooking.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeListFragment;

/*
    Happy Cooking App
    Create 31/05/2018
 */
public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_CAT_ID = "cat_id";
    public static String USER_ID_AUTHENTICATION;
    public RecipeListFragment mFragmentRoot;
    private int mRootCurrentId;
    public SearchView mRootSearch;
    CallbackManager mCallbackManager;
    boolean isLoggedIn;

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
        setTitle(R.string.nav_title_all_recipe);
        loadFragment();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        isLoggedIn = accessToken != null && !accessToken.isExpired();


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
            String titleActivity = "";
            switch (id) {
                case R.id.nav_all_recipe:
                    setBundleData("0");
                    titleActivity = this.getResources().getString(R.string.nav_title_all_recipe);
                    break;

                case R.id.nav_favorite:
                    setBundleData("-1");
                    titleActivity = this.getResources().getString(R.string.nav_title_favorite);
                    //Me.createToast(this, "nav_favorite");
                    break;
                case R.id.nav_suggest:
                    setBundleData("10");
                    titleActivity = this.getResources().getString(R.string.nav_title_suggest);
                    //Me.createToast(this, "nav_favorite");
                    break;
                case R.id.nav_cake:
                    setBundleData("1");
                    titleActivity = this.getResources().getString(R.string.nav_title_cake);
                    //Me.createToast(this, "nav_cake");
                    break;

                case R.id.nav_soup:
                    setBundleData("2");
                    titleActivity = this.getResources().getString(R.string.nav_title_soup);
                    //Me.createToast(this, "nav_soup");
                    break;

                case R.id.nav_porriedge:
                    setBundleData("3");
                    titleActivity = this.getResources().getString(R.string.nav_title_porridge);
                    //Me.createToast(this, "nav_porriedge");
                    break;

                case R.id.nav_fried_food:
                    setBundleData("4");
                    titleActivity = this.getResources().getString(R.string.nav_title_fried_food);
                    //Me.createToast(this, "nav_fried_food");
                    break;

                case R.id.nav_steaming:
                    setBundleData("5");
                    titleActivity = this.getResources().getString(R.string.nav_title_steaming);
                    //Me.createToast(this, "nav_steaming");
                    break;

                case R.id.nav_appetized:
                    setBundleData("6");
                    titleActivity = this.getResources().getString(R.string.nav_title_appetized);
                    //Me.createToast(this, "nav_appetized");
                    break;

                case R.id.nav_cook_with_sauce:
                    setBundleData("7");
                    titleActivity = this.getResources().getString(R.string.nav_title_cook_with_sauce);
                    //Me.createToast(this, "nav_cook_with_sauce");
                    break;

                case R.id.nav_stir_fry:
                    setBundleData("8");
                    titleActivity = this.getResources().getString(R.string.nav_title_stir_fry);
                    //Me.createToast(this, "nav_stir_fry");
                    break;

                case R.id.nav_smoothies:
                    setBundleData("9");
                    titleActivity = this.getResources().getString(R.string.nav_title_smoothies);
                    //Me.createToast(this, "nav_smoothies");
                    break;
            }
            setTitle(titleActivity);
            mRootCurrentId = id;
            loadFragment();
        }
        if (id == R.id.nav_login) {
            DialogLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void DialogLogin() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_login_facebook);
        LoginButton mBtnLoginFacebook;

        mCallbackManager = CallbackManager.Factory.create();

        mBtnLoginFacebook = (LoginButton) dialog.findViewById(R.id.login_button);
        mBtnLoginFacebook.setReadPermissions(Arrays.asList("public_profile"));
        /*// If using in a fragment
        loginButton.setFragment(this);*/

        // Callback registration
        mBtnLoginFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            String mess;

            @Override
            public void onSuccess(LoginResult loginResult) {
                //loginResult AccessToken
                // App code
                mess = "User ID: " + loginResult.getAccessToken().getUserId() + "\n" +
                        "Auth Token: " + loginResult.getAccessToken().getToken();
                Toast.makeText(getApplicationContext(), "User ID: " + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                // App code
                mess = "Login Cancel";
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                mess = "Login failed";
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG).show();
            }
        });
        if (isLoggedIn) {
            Toast.makeText(this, "Đã đăng nhập", Toast.LENGTH_SHORT).show();

        }
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
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
