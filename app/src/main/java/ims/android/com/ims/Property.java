package ims.android.com.ims;

import android.util.Log;

/**
 * Created by frank on 10/19/16.
 */

public class Property implements IProperty {
    private final static String TAG = Property.class.getSimpleName() + ": ";
    IProperty mProperty;
    private String mPackageName;
    private int mUid;

    public Property() {

    }

    public void setPackageName(String pkgName) {
        mPackageName = pkgName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setUid(int uid) {
         mUid = uid;
    }

    public int getUid() {
        return mUid;
    }

    @Override
    public void setProperty(Property property) {
        mProperty = property;
    }

    @Override
    public IProperty getProperty() {
        return null;
    }

    @Override
    public void update() {
        Log.d(TAG, "update manually");
    }

    @Override
    public void onPropertyChange() {
        Log.d(TAG, "callback from engine");
    }
}
