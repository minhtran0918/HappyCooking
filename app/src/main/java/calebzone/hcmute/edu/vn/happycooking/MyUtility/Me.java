package calebzone.hcmute.edu.vn.happycooking.MyUtility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class Me {
    public static final String TAG = "COOKBOOK";
    public static final String IP_NANI = "192.168.9.100";
    public static final String IP_2_3 = "192.168.88.101";
    public static final String URL_YOUTUBE = "https://youtu.be/";
    public static final String API_YOUTUBE = "AIzaSyDLVAtY7KYNTq0T3jhYpHMoqNKPy2b1Y4k";
    public static final String APP_SHARED_PREFERENCES = "cookbookapp";
    public static final int REQUEST_CODE_YOUTUBE = 1789;
    public static SharedPreferences mRootSharedPreferences;
    public static SharedPreferences.Editor mRootEditSharedPreferences;

    public static final String KEY_ID_FAVORITE = "id_";
    public static final String KEY_ID_ARRAY_SIZE = "array_size";

    public static void createToast(Context context, String... args) {
        String mess = "";
        for (int i = 0; i < args.length; i++) {
            mess += args[i] + "\n";
        }
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }

    public static void setmRootSharedPreferences(Context mRootContext) {
        if (mRootSharedPreferences == null) {
            mRootSharedPreferences = mRootContext.getSharedPreferences(APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
        if (mRootEditSharedPreferences == null) {
            mRootEditSharedPreferences = mRootSharedPreferences.edit();
        }
        mRootEditSharedPreferences.putInt(KEY_ID_ARRAY_SIZE, 0);
        mRootEditSharedPreferences.commit();
    }

    //Me.setmRootSharedPreferences(getApplicationContext());
    //Me.createToast(mRootContext, Me.mRootSharedPreferences.getString(Me.KEY_ID_FAVORITE,"defaul"));
    public static List get() {
        int size = mRootSharedPreferences.getInt(KEY_ID_ARRAY_SIZE, 0);
        Integer[] array = new Integer[size];
        int temp = 1;
        for (int i = 0; i < size; i++) {
            array[i] = Me.mRootSharedPreferences.getInt(KEY_ID_FAVORITE + i, 0);
        }
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
        for (Iterator<Integer> it = arrayList.iterator(); it.hasNext(); ) {
            Integer in = it.next();
            if (in.intValue() == 0)
                it.remove();
        }
        return arrayList;
    }

    public static void removeFavorite(int inputID) {
        if (inputID == mRootSharedPreferences.getInt(KEY_ID_FAVORITE + (inputID - 1), 0)) {
            mRootEditSharedPreferences.remove(KEY_ID_FAVORITE + (inputID - 1));
            mRootEditSharedPreferences.commit();
        }
    }

    public static void addFavorite(int inputID) {
        if (Me.mRootSharedPreferences.getInt(KEY_ID_FAVORITE + (inputID - 1), 0) == 0) {
            Me.mRootEditSharedPreferences.putInt(KEY_ID_FAVORITE + (inputID - 1), inputID);
            mRootEditSharedPreferences.putInt(KEY_ID_ARRAY_SIZE, mRootSharedPreferences.getInt(KEY_ID_ARRAY_SIZE, 0) + 1);
            Me.mRootEditSharedPreferences.commit();
        }
    }

    public static boolean checkFavorite(int inputID) {
        if (mRootSharedPreferences.getInt(KEY_ID_FAVORITE + (inputID - 1), 0) == 0) {
            return false;
        } else {
            return true;
        }
    }
}
