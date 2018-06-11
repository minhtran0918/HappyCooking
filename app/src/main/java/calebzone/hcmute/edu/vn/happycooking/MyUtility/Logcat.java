package calebzone.hcmute.edu.vn.happycooking.MyUtility;

import android.util.Log;

public class Logcat {
    public static final String TAG = "COOKBOOK";

    public Logcat() {
    }

    public static void d(String mess) {
        Log.d(TAG, mess);
    }
}
