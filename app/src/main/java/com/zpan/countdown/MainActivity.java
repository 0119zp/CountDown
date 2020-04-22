package com.zpan.countdown;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.zpan.countdown.databinding.ActivityMainBinding;

/**
 * @author zpan
 */
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

        mBinding.tvCountDownYear.setText(getString(R.string.current_year_count_down, currentYear));
        long currentYearLastTime = TimeUtil.timeToStamp(getString(R.string.count_down_time, currentYear , 12, 31));
        mBinding.vcCountDownYear.start(currentYearLastTime - currentTime);
        long todayLastTime = TimeUtil.timeToStamp(getString(R.string.count_down_time, currentYear, currentMonth, currentDay));
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
        String spHour = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_HOUR);
        String spMinute = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_MINUTE);
        String spSecond = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_SECOND);

        long lostTime = TimeUtil.timeToStamp(getString(R.string.old_count_down_time, lastYear, month, day, spHour, spMinute, spSecond));

        mBinding.tvCountDownOld.setText(getString(R.string.old_count_down, old));
        mBinding.vcCountDown.setVisibility(View.VISIBLE);
        mBinding.vcCountDown.start(lostTime - currentTime);
    }
}
