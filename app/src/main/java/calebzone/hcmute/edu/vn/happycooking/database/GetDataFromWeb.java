package calebzone.hcmute.edu.vn.happycooking.database;

import android.content.Context;
import android.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.CheckUtil;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.Logcat;
import calebzone.hcmute.edu.vn.happycooking.MyUtility.MyVariable;
import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;

public class GetDataFromWeb {

    public static final String ROOT = "http://obligatory-averages.000webhostapp.com/getdata.php?cat_id=4";

    private Context mRootContext;
    private ArrayList<RecipeModel> recipeModelArrayList;

    public ArrayList<RecipeModel> getRecipeModelArrayList() {
        return recipeModelArrayList;
    }

    private String cat_id;

    public GetDataFromWeb(Context mRootContext) {
        this.mRootContext = mRootContext;
        //readDataByCat();
    }

    /*public void readData(String url, final ArrayList<RecipeModel> recipeModelArrayList) {
        RequestQueue requestQueue = Volley.newRequestQueue(mRootContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        recipeModelArrayList.add(new RecipeModel(
                                object.getString(RecipeModel.COLUMN_ID),
                                object.getString(RecipeModel.COLUMN_NAME),
                                object.getString(RecipeModel.COLUMN_INTRO),
                                object.getString(RecipeModel.COLUMN_INGREDIENT),
                                object.getString(RecipeModel.COLUMN_INSTRUCTION),
                                object.getString(RecipeModel.COLUMN_IMAGE)
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
    }*/

  /*  public void readDataByCat() {
        recipeModelArrayList = new ArrayList<RecipeModel>();
        String url = ROOT;
        RequestQueue requestQueue = Volley.newRequestQueue(mRootContext);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        recipeModelArrayList.add(new RecipeModel(
                                object.getString(RecipeModel.COLUMN_ID),
                                object.getString(RecipeModel.COLUMN_CATEGORY_ID),
                                object.getString(RecipeModel.COLUMN_NAME),
                                object.getString(RecipeModel.COLUMN_INTRO),
                                object.getString(RecipeModel.COLUMN_INGREDIENT),
                                object.getString(RecipeModel.COLUMN_INSTRUCTION),
                                object.getString(RecipeModel.COLUMN_IMAGE),
                                object.getString(RecipeModel.COLUMN_LINK),
                                object.getString(RecipeModel.COLUMN_FAVORITE)
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
    }*/
}
