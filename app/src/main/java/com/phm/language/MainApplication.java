package com.phm.language;

import android.app.Application;
import android.content.Context;

import com.phm.language.Helper.LocalHelper;

public class MainApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocalHelper.onAttach(base, "en"));
    }
}
