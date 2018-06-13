package calebzone.hcmute.edu.vn.happycooking.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import calebzone.hcmute.edu.vn.happycooking.MyUtility.Me;
import calebzone.hcmute.edu.vn.happycooking.R;

public class LoadingScreenActivity extends AppCompatActivity {

    CountDownTimer mCountDownTimer;
    ProgressBar mProgressBarCircle, mProgressBarLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        referencedComponent();
        int mTimeRun = 400, mTimeAll = 750;
        mCountDownTimer = new CountDownTimer(mTimeAll, mTimeRun) {
            @Override
            public void onTick(long millisUntilFinished) {
                int getProgressCurrent = mProgressBarLine.getProgress();
                mProgressBarLine.setProgress(getProgressCurrent + 45);
            }

            @Override
            public void onFinish() {
                mProgressBarLine.setProgress(100);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        }.start();
        Me.setmRootSharedPreferences(getApplicationContext());
        Me.mRootEditSharedPreferences.clear();
        Me.mRootEditSharedPreferences.commit();
        Me.addFavorite(1);
        Me.addFavorite(2);
    }

    //region Referenced Component
    public void referencedComponent() {
        mProgressBarCircle = (ProgressBar) findViewById(R.id.progressBar_circle);
        mProgressBarLine = (ProgressBar) findViewById(R.id.progressBar_line);
    }
    //endregion
}
