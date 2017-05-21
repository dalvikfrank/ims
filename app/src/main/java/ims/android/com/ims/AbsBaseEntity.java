package ims.android.com.ims;

import android.util.Log;

import java.util.ArrayList;

import ims.android.com.ims.util.common.Debug;

/**
 * Created by frank on 10/31/16.
 */

public abstract class AbsBaseEntity implements IProperty {
    private final static String TAG = AbsBaseEntity.class.getSimpleName() + ": ";
    protected Property mProperty;
    protected ArrayList<AbsBaseStrategy> mStrategyList = new ArrayList<AbsBaseStrategy>();

    public AbsBaseEntity() {

    }


    public AbsBaseEntity(Property property) {
        mProperty = property;
    }

    public void add(AbsBaseStrategy strategy) {
        if (Debug.DEBUG_FRAMEWORK) Log.d(TAG, "Entity:" + this.toString()
                + "====> add Strategy: " + strategy.toString());
        synchronized (this) {
            if (strategy != null)
                mStrategyList.add(strategy);
        }
    }

    public void remove(AbsBaseStrategy strategy) {
        synchronized (this) {
            if (strategy != null) {
                for (AbsBaseStrategy s : mStrategyList) {
                    if (strategy.equals(s)) {
                        mStrategyList.remove(s);
                        s.cancel();
                    }
                }
            }
        }
    }

    public void clear() {
        synchronized (this) {
            for (AbsBaseStrategy s : mStrategyList) {
                if (Debug.DEBUG_FRAMEWORK) Log.d(TAG, "Entity:" + this.toString()
                        + "<==== remove Strategy: " + s.toString());
                s.cancel();
            }
            mStrategyList.clear();
        }
    }

    @Override
    public void setProperty(Property property) {
        mProperty = property;
    }

    @Override
    public Property getProperty() {
        return mProperty;
    }

    @Override
    public void onPropertyChange() {
    }

    @Override
    public void update() {

    }

}
