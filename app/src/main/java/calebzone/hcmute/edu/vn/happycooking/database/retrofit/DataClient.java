package calebzone.hcmute.edu.vn.happycooking.database.retrofit;

import org.json.JSONArray;

import java.util.List;

import calebzone.hcmute.edu.vn.happycooking.database.model.RecipeModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataClient {

    @FormUrlEncoded
    @POST("getdata.php")
    Call<List<RecipeModel>> getData(@Field("cat_id") String cat_id);

}
