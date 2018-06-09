package calebzone.hcmute.edu.vn.happycooking.MyUtility;

import android.content.Context;
import android.widget.Toast;

public class CheckUtil {
    public static void createToast(Context context, String... args) {
        String mess = "";
        for (int i = 0; i < args.length; i++) {
            mess += args[i] + "\n";
        }
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}
