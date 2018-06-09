package calebzone.hcmute.edu.vn.happycooking.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageInternet extends AsyncTask<String, Void, Bitmap> {

    public ImageView mImage;

    public LoadImageInternet(ImageView imageView) {
        mImage = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap mBitmapImage = null;
        try {
            URL url = new URL(strings[0]);
            InputStream inputStream = url.openConnection().getInputStream();

            mBitmapImage = BitmapFactory.decodeStream(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mBitmapImage;
    }

    @Override
    protected void onPostExecute(Bitmap mBitmapImage) {
        super.onPostExecute(mBitmapImage);
        mImage.setImageBitmap(mBitmapImage);
    }
}
