package calebzone.hcmute.edu.vn.happycooking.database.retrofit;

public class APIUtils {
    public static final String BaseUrl = "http://obligatory-averages.000webhostapp.com/";

    public static DataClient getData() {

        return RetrofitClient.getClient(BaseUrl).create(DataClient.class);
    }
}
