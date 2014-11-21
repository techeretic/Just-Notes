
package com.pshetye.justnotes;

import com.pshetye.justnotes.util.StyleAttributes;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public abstract class BaseActivity extends ActionBarActivity {

    private static final String LOG_TAG = "BaseActivity";
    public static final String SHARED_PREF_APP_DATA = "APP_DATA";
    public static final String SHARED_PREF_KEY_THEME = "theme";
    public static final String SHARED_PREF_KEY_LAYOUT = "layout";

    public static final int VIEW_CODE = 1;

    public static final int INPUT_CODE = 2;

    protected abstract int getLayoutResource();

    private Toolbar toolbar = null;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = getSharedPreferences(
                SHARED_PREF_APP_DATA, MODE_PRIVATE);
        int theme = mPrefs.getInt("theme", -1);
        Log.d(LOG_TAG, "Setting Theme in onCreate");
        if (theme == -1) {
            Editor editor = mPrefs.edit();
            editor.putInt(SHARED_PREF_KEY_THEME, 0);
            editor.commit();
            StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeDark);
        } else {
            switch (theme) {
                case 0:
                    setTheme(R.style.AppThemeDark);
                    StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeDark);
                    break;
                case 1:
                    setTheme(R.style.AppThemeLight);
                    StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeLight);
                    break;
                case 2:
                    setTheme(R.style.AppThemeRedDark);
                    StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeRedDark);
                    break;
                case 3:
                    setTheme(R.style.AppThemeRedLight);
                    StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeRedLight);
                    break;
                case 4:
                    setTheme(R.style.AppThemeBlueDark);
                    StyleAttributes.setStyleAttributes(BaseActivity.this, R.style.AppThemeBlueDark);
                    break;
                case 5:
                    setTheme(R.style.AppThemeBlueLight);
                    StyleAttributes
                            .setStyleAttributes(BaseActivity.this, R.style.AppThemeBlueLight);
                    break;
            }
        }
        setContentView(getLayoutResource());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.L) {
            statusBarColor();
        }
        toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @TargetApi(Build.VERSION_CODES.L)
    private void statusBarColor() {
        getWindow().setStatusBarColor(getResources().getColor(StyleAttributes.colorPrimaryDark));
    }
}
