package calebzone.hcmute.edu.vn.happycooking.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import calebzone.hcmute.edu.vn.happycooking.R;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "recipe_id";

    public static Intent newIntent(Context context, long recipeId) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);

        // extras
        intent.putExtra(EXTRA_RECIPE_ID, recipeId);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
    }

}
