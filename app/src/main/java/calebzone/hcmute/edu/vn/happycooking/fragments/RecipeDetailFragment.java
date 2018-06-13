package calebzone.hcmute.edu.vn.happycooking.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.Me;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.activity.RecipeDetailActivity;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;

public class RecipeDetailFragment extends Fragment {

    private View mRootView;
    private Context mRootContext;
    private RecipeModel mRecipeRoot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // handle intent extras
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        mRootContext = mRootView.getContext();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromBundle();
        bindData();
    }

    private void loadDataFromBundle() {
        Bundle bundleData = getArguments();
        mRecipeRoot = (RecipeModel) bundleData.getSerializable(RecipeDetailActivity.EXTRA_RECIPE_BUNDLE);
    }

    private void bindData() {
        TextView txtIntroRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_intro_content);
        TextView txtIngredentsRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_ingredients_content);
        TextView txtInstructionRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_instruction_content);
        txtIntroRecipe.setText(mRecipeRoot.getIntro());
        txtIngredentsRecipe.setText(mRecipeRoot.getIngredient().toString());
        txtInstructionRecipe.setText(mRecipeRoot.getInstruction());
    }

    private void shareFB() {
        Uri videoFileUri = Uri.parse(Me.URL_YOUTUBE + mRecipeRoot.getLink().toString());
        ShareDialog shareDialog = new ShareDialog((Activity) mRootContext);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(videoFileUri)
                .setQuote(mRecipeRoot.getName())
                .build();
        shareDialog.show(content);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_recipe_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.fragment_recipe_detail_menu_share:
                shareFB();
                break;
            case R.id.fragment_recipe_detail_menu_rate:
                Toast.makeText(mRootContext, "Rate", Toast.LENGTH_LONG).show();
                break;
            case R.id.fragment_recipe_detail_menu_about:
                showAboutDialog();
                break;
            case android.R.id.home:
                ((Activity) mRootContext).finish();
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
