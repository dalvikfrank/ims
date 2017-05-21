package ims.android.com.ims;


import android.util.Log;
import ims.android.com.ims.util.common.Debug;

/**
 * Created by frank on 10/29/16.
 */

public abstract class AbsBaseStrategy implements IStrategy<AbsBaseStrategy> {
    private final static String TAG = AbsBaseStrategy.class.getSimpleName() + ": ";
    protected AbsBaseEntity mEntity;

    public AbsBaseStrategy() {

    }

    public AbsBaseStrategy(AbsBaseEntity entity) {
        setEntity(entity);
    }
    public void setEntity(AbsBaseEntity entity) {
        if (Debug.DEBUG_FRAMEWORK)  Log.d(TAG, "setEntity");
        mEntity = entity;
        mEntity.add(this);
    }

    @Override
    public AbsBaseStrategy get() {
        return this;
    }

    @Override
    public int apply() {
        return HandleResult.CONSUMED;
    }

    @Override
    public int cancel() {
        return HandleResult.CONSUMED;
    }

    @Override
    public void finished() {
        String pkgName = mEntity.getProperty().getPackageName();
        if (ImsManager.getInstance().get(pkgName) != null) {
            if (Debug.DEBUG_FRAMEWORK)
                Log.d(TAG, "force finished: " + mEntity.toString() + ", Strategy: " + this.toString());
            ImsManager.getInstance().remove(pkgName);
        }
    }

    @Override
    public int onProcessIms() {
        Log.d(TAG, "==== onProcess Ims, Entity:" + mEntity.toString()
                + ", Strategy: " + this.toString());
        return 1024;
    }

}
