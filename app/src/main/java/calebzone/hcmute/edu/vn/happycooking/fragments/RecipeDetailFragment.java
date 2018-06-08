package calebzone.hcmute.edu.vn.happycooking.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import calebzone.hcmute.edu.vn.happycooking.R;

public class RecipeDetailFragment extends Fragment {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        return mRootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
        /*// handle intent extras
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            handleExtras(extras);
        }*/
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindData();
        //TODO: LOAD DATA ở đây

    }

    private void bindData() {
        bindDataContent();
    }

    private void bindDataContent() {
        ImageView img_food = (ImageView) mRootView.findViewById(R.id.img_food);
        img_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mRootView.getContext(), "Nhấn r", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
