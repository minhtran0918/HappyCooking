package calebzone.hcmute.edu.vn.happycooking.database;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.MyVariable;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;

public class GetDataFromWeb {

    public static final String ROOT = "http://" + MyVariable.IP + "/HappyCook/getdata.php";

    private Context mRootContext;

    public GetDataFromWeb(Context mRootContext) {
        this.mRootContext = mRootContext;
    }

    public void readData(String url, final ArrayList<RecipeModel> recipeModelArrayList) {
        RequestQueue requestQueue = Volley.newRequestQueue(mRootContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        recipeModelArrayList.add(new RecipeModel(
                                object.getLong(RecipeModel.COLUMN_ID),
                                object.getLong(RecipeModel.COLUMN_CATEGORY_ID),
                                object.getString(RecipeModel.COLUMN_NAME),
                                object.getString(RecipeModel.COLUMN_INTRO),
                                object.getString(RecipeModel.COLUMN_INSTRUCTION),
                                object.getString(RecipeModel.COLUMN_IMAGE),
                                object.getString(RecipeModel.COLUMN_LINK),
                                object.getInt(RecipeModel.COLUMN_FAVORITE)
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckUtil.createToast(mRootContext, error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}