package com.thtmo.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

public class App extends Application {
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
        });

        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
