package calebzone.hcmute.edu.vn.happycooking.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.R;
import calebzone.hcmute.edu.vn.happycooking.activity.RecipeDetailActivity;

public class RecipeDetailFragment extends Fragment {

    private View mRootView;
    private long mRecipeId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        return mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);

        // handle intent extras

        /*Intent intent = getActivity().getIntent();
        mRecipeId = intent.getLongExtra(RecipeDetailActivity.EXTRA_RECIPE_ID,0);*/

        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            mRecipeId = extras.getLong(RecipeDetailActivity.EXTRA_RECIPE_ID);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindData();
        loadData();

    }

    private void test() {
        CheckUtil.createToast(mRootView.getContext(), String.valueOf(mRecipeId));
    }

    private void loadData() {

    }

    private void bindData() {
        bindDataContent();
        //bindDataTime();
    }

    private void bindDataContent() {
        TextView txt_id = (TextView) mRootView.findViewById(R.id.txt_id);
        txt_id.setText(String.valueOf(mRecipeId));

        ImageView img_food = (ImageView) mRootView.findViewById(R.id.img_food);
        /*img_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });*/
    }
}
