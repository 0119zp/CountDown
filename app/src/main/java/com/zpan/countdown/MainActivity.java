package com.zpan.countdown;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.zpan.countdown.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TIME_OLD = "time_old";
    private static final String TIME_YEAR = "time_year";
    private static final String TIME_MONTH = "time_month";
    private static final String TIME_DAY = "time_day";

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        long currentTime = System.currentTimeMillis();
        int currentYear = TimeUtil.getCurrentYear();
        int currentMonth = TimeUtil.getCurrentYear();
        int currentDay = TimeUtil.getCurrentYear();

        mBinding.tvCountDownYear.setText(currentYear + "年余额");
        long currentYearLastTime = TimeUtil.timeToStamp(currentYear + "-12-31 24:00:00");
        mBinding.vcCountDownYear.start(currentYearLastTime - currentTime);
        long todayLastTime = TimeUtil.timeToStamp(currentYear + "-" + currentMonth + "-" + currentDay + " 24:00:00");
        mBinding.vcCountDownToday.start(todayLastTime - currentTime);

        int spOld = SharedPreferencesHelper.getInstance(this).getInteger(TIME_OLD, 60);
        int spYear = SharedPreferencesHelper.getInstance(this).getInteger(TIME_YEAR, 2020);
        String spMonth = SharedPreferencesHelper.getInstance(this).getString(TIME_MONTH);
        String spDay = SharedPreferencesHelper.getInstance(this).getString(TIME_DAY);
        if (TextUtils.isEmpty(spMonth)) {
            mBinding.btnCountDownStart.setVisibility(View.VISIBLE);
        } else {
            mBinding.btnCountDownStart.setVisibility(View.GONE);
            setCountDown(currentTime, spOld, spYear, spMonth, spDay);

            mBinding.etInputOld.setText(spOld +"");
            mBinding.etInputYear.setText(spYear + "");
            mBinding.etInputMonth.setText(spMonth);
            mBinding.etInputDay.setText(spDay);
        }

        mBinding.btnCountDownStart.setOnClickListener(v -> {
            int old = Integer.valueOf(mBinding.etInputOld.getText().toString().trim());
            int year = Integer.valueOf(mBinding.etInputYear.getText().toString().trim());
            String month = mBinding.etInputMonth.getText().toString().trim();
            String day = mBinding.etInputDay.getText().toString().trim();

            setCountDown(currentTime, old, year, month, day);

            SharedPreferencesHelper.getInstance(this).putInteger(TIME_OLD, old);
            SharedPreferencesHelper.getInstance(this).putInteger(TIME_YEAR, year);
            SharedPreferencesHelper.getInstance(this).putString(TIME_MONTH, month);
            SharedPreferencesHelper.getInstance(this).putString(TIME_DAY, day);
        });
    }

    private void setCountDown(long currentTime, int old, int year, String month, String day) {
        String lastYear = String.valueOf(year + old);
        long lostTime = TimeUtil.timeToStamp(lastYear + "-" + month + "-" + day + " 00:00:00");

        mBinding.tvCountDownOld.setText("距离" + old + "岁余额");
        mBinding.vcCountDown.setVisibility(View.VISIBLE);
        mBinding.vcCountDown.start(lostTime - currentTime);
    }
}
