package com.zpan.countdown;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

//http://stackoverflow.com/questions/4693387/sharedpreferences-and-thread-safety
//http://developer.android.com/reference/android/content/SharedPreferences.html

/**
 * SharedPreferences工具类<br>
 * 注意：SharedPreferences中存放配置信息，不要存放大数据，数据多了会卡（xml文件的读取写入性能降低）
 */
public class SharedPreferencesHelper {

    private static SharedPreferencesHelper sInstance;
    private static SharedPreferences sSettings;

    private SharedPreferencesHelper(Context outContext) {
        String outAppPackagename = outContext.getPackageName();
        sSettings = outContext.getSharedPreferences(outAppPackagename, 0);
    }

    /**
     * 获得以传入outContext所在包的包名为key的SharedPreferences的工具类实例
     */
    public static synchronized SharedPreferencesHelper getInstance(Context outContext) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesHelper(outContext);
        }
        return sInstance;
    }

    /**
     * 保存string
     */
    public void putString(String key, String value) {
        synchronized (sSettings) {
            sSettings.edit().putString(key, value).apply();
        }
    }

    /**
     * 得到String
     */
    public String getString(String key) {
        synchronized (sSettings) {
            return sSettings.getString(key, "");
        }
    }

    /**
     * 得到String
     *
     * @param defValue 默认值
     */
    public String getString(String key, String defValue) {
        synchronized (sSettings) {
            return sSettings.getString(key, defValue);
        }
    }

    /**
     * 获取Double
     */
    public Double getDouble(String key) {
        String retStr = getString(key, null);
        Double ret = null;
        try {
            ret = Double.parseDouble(retStr);
        } catch (Exception e) {
        }
        return ret;
    }

    public void putBoolean(String key, boolean bool) {
        synchronized (sSettings) {
            sSettings.edit().putBoolean(key, bool).apply();
        }
    }

    public void putInteger(String key, int integer) {
        synchronized (sSettings) {
            sSettings.edit().putInt(key, integer).apply();
        }
    }

    public void putLong(String key, long lon) {
        synchronized (sSettings) {
            sSettings.edit().putLong(key, lon).apply();
        }
    }

    public Boolean getBoolean(String key, boolean defValue) {
        synchronized (sSettings) {
            return sSettings.getBoolean(key, defValue);
        }
    }

    public int getInteger(String key, int defValue) {
        synchronized (sSettings) {
            return sSettings.getInt(key, defValue);
        }
    }

    public long getLong(String key, long defValue) {
        synchronized (sSettings) {
            return sSettings.getLong(key, defValue);
        }
    }

    /**
     * 保存hashmap key和value必须是Stirng类型
     */
    public void putHashMap(String key, HashMap<String, String> map) {
        JSONObject ret = new JSONObject(map);
        synchronized (sSettings) {
            sSettings.edit().putString(key, ret.toString()).apply();
        }
    }

    public HashMap<String, String> getHashMap(String key) {
        return getHashMapByKey(key);
    }

    public HashMap<String, String> getHashMapByKey(String key) {
        HashMap<String, String> ret = new HashMap<String, String>();

        String mapStr = getString(key, "{}");
        JSONObject mapJson = null;
        try {
            mapJson = new JSONObject(mapStr);
        } catch (Exception e) {
            return ret;
        }

        if (mapJson != null) {
            @SuppressWarnings("unchecked")
            Iterator<String> it = mapJson.keys();
            while (it.hasNext()) {
                String theKey = it.next();
                String theValue = mapJson.optString(theKey);
                ret.put(theKey, theValue);
            }
        }

        return ret;
    }

    /**
     * 保存list 必须是String类型
     */
    public void putArrayList(String key, ArrayList<String> list) {
        JSONArray ret = new JSONArray(list);
        putString(key, ret.toString());
    }

    public ArrayList<String> getArrayList(String key) {
        ArrayList<String> ret = new ArrayList<String>();

        String listStr = getString(key, "{}");
        JSONArray listJson = null;
        try {
            listJson = new JSONArray(listStr);
        } catch (Exception e) {
            return ret;
        }

        if (listJson != null) {
            for (int i = 0; i < listJson.length(); i++) {
                String temp = listJson.optString(i);
                ret.add(temp);
            }
        }

        return ret;
    }
}
