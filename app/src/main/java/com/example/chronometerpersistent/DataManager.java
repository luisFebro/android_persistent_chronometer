// reference: https://www.youtube.com/watch?v=B_xWjKhfBMA
package com.example.chronometerpersistent;

import android.content.Context;
import android.content.SharedPreferences;

public class DataManager {
    // Initialize variable
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public DataManager(Context context) {
        // Initialize shared preferences
        sharedPreferences = context.getSharedPreferences("AppKey", 0);

        // Initialize editor
        editor = sharedPreferences.edit();

        // Apply editor
        editor.apply();
    }

    public void setFlag(Boolean flag) {
        // Put boolean value
        editor.putBoolean("KEY_FLAG", flag);

        // Commit editor
        editor.commit();
    }

    public boolean getFlag() {
        // return boolean value (default value is false)
        return sharedPreferences.getBoolean("KEY_FLAG", false);
    }

    public void setCurrentTime(String currentTime) {
        // Put string value
        editor.putString("KEY_TIME", currentTime);

        // Commit editor
        editor.commit();
    }

    public String getCurrentTime() {
        // return string value
        return sharedPreferences.getString("KEY_TIME", "");
    }
}
