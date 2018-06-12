package calebzone.hcmute.edu.vn.happycooking.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.Logcat;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.WrapTextViewFilter;
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
        //setHasOptionsMenu(true);
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

    //TODO: fix nội dung
    private void bindData() {
        TextView txtIntroRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_intro_content);
        TextView txtIngredentsRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_ingredients_content);
        TextView txtInstructionRecipe = (TextView) mRootView.findViewById(R.id.fragment_recipe_list_instruction_content);
        txtIntroRecipe.setText(mRecipeRoot.getIntro());
        txtIngredentsRecipe.setText(mRecipeRoot.getIngredient().toString());

/*        String noidung = "1.abcdefgh\n" + "2.abcdefgh 3.abcdefgh 4.abcdefgh";
        SpannableString noidungspanned = new SpannableString(noidung);
        String bla = mRecipeRoot.getInstruction().toString();*/
        txtInstructionRecipe.setText(mRecipeRoot.getInstruction());
    }

    private String wrapText(String input) {
        String output = "";
        int indexFind = 0;
        indexFind = input.indexOf(String.valueOf(2) + ".");

        input.substring(indexFind);
        Logcat.d(input);

        return output;
    }
}
