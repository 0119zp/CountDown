package com.zpan.countdown;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.zpan.countdown.databinding.ActivitySettingBinding;

/**
 * @author zpan
 * @date 2020/4/17 5:46 PM
 */
public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        int spOld = SharedPreferencesHelper.getInstance(this).getInteger(Element.TIME_OLD, 60);
        int spYear = SharedPreferencesHelper.getInstance(this).getInteger(Element.TIME_YEAR, 2020);
        String spMonth = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_MONTH);
        String spDay = SharedPreferencesHelper.getInstance(this).getString(Element.TIME_DAY);

        if (!TextUtils.isEmpty(spMonth)) {
            mBinding.etInputOld.setText(spOld + "");
            mBinding.etInputYear.setText(spYear + "");
            mBinding.etInputMonth.setText(spMonth);
            mBinding.etInputDay.setText(spDay);
        }

        mBinding.btnConfirmSave.setOnClickListener(v -> {
            int old = Integer.valueOf(mBinding.etInputOld.getText().toString().trim());
            int year = Integer.valueOf(mBinding.etInputYear.getText().toString().trim());
            String month = mBinding.etInputMonth.getText().toString().trim();
            String day = mBinding.etInputDay.getText().toString().trim();

            SharedPreferencesHelper.getInstance(this).putInteger(Element.TIME_OLD, old);
            SharedPreferencesHelper.getInstance(this).putInteger(Element.TIME_YEAR, year);
            SharedPreferencesHelper.getInstance(this).putString(Element.TIME_MONTH, month);
            SharedPreferencesHelper.getInstance(this).putString(Element.TIME_DAY, day);

            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
