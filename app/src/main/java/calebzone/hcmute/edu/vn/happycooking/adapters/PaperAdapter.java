package calebzone.hcmute.edu.vn.happycooking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import calebzone.hcmute.edu.vn.happycooking.fragments.RecipeFragment;
import calebzone.hcmute.edu.vn.happycooking.fragments.WeekFragment;

public class PaperAdapter extends FragmentStatePagerAdapter {
    int mNumOfTab;

    public PaperAdapter(FragmentManager fm, int mNumOfTab) {
        super(fm);
        this.mNumOfTab = mNumOfTab;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                WeekFragment tab1 = new WeekFragment();
                return tab1;
            case 1:
                RecipeFragment tab2 = new RecipeFragment();
                return tab2;
            case 2:
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTab;
    }
}
