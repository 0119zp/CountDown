package com.zpan.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.zpan.countdown.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        long currentTime = System.currentTimeMillis();
        int currentYear = TimeUtil.getCurrentYear();
        int currentMonth = TimeUtil.getCurrentYear();
        int currentDay = TimeUtil.getCurrentYear();

        mBinding.tvCountDownYear.setText(currentYear + "年倒计时");
        long currentYearLastTime = TimeUtil.timeToStamp(currentYear + "-12-31 24:00:00");
        mBinding.vcCountDownYear.start(currentYearLastTime - currentTime);
        long todayLastTime = TimeUtil.timeToStamp(currentYear + "-" + currentMonth + "-" + currentDay + " 24:00:00");
        mBinding.vcCountDownToday.start(todayLastTime - currentTime);

        int spOld = SharedPreferencesHelper.getInstance(this).getInteger(Element.TIME_OLD, 60);
        int spYear = SharedPreferencesHelper.getInstance(this).getInteger(Element.TIME_YEAR, 2020);
        String spMonth = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_MONTH);
        String spDay = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_DAY);
        if (!TextUtils.isEmpty(spMonth)) {
            setCountDown(currentTime, spOld, spYear, spMonth, spDay);
        } else {
            mBinding.vcCountDown.setVisibility(View.GONE);
        }

        mBinding.ivSetting.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        });
    }

    private void setCountDown(long currentTime, int old, int year, String month, String day) {
        String lastYear = String.valueOf(year + old);
        long lostTime = TimeUtil.timeToStamp(lastYear + "-" + month + "-" + day + " 00:00:00");

        mBinding.tvCountDownOld.setText(old + "岁倒计时");
        mBinding.vcCountDown.setVisibility(View.VISIBLE);
        mBinding.vcCountDown.start(lostTime - currentTime);
    }
}
