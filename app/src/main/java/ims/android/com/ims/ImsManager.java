package ims.android.com.ims;

import android.util.ArrayMap;
import android.util.Log;
import java.util.ArrayList;
import ims.android.com.ims.util.common.Debug;

/**
 * Created by frank on 5/21/17.
 */

public class ImsManager {
    private final static String TAG = ImsManager.class.getSimpleName() + ": ";
    private static ImsManager sInstance = null;
    ArrayMap<String, AbsBaseEntity> mPackageMap
            = new ArrayMap<String, AbsBaseEntity>();
    ArrayList<String> mStrategyMap = new ArrayList<String>();


    public ImsManager() {
        mStrategyMap.add("ims.android.com.ims.BgStrategy");
        if (Debug.DEBUG_FRAMEWORK)
            Log.d(TAG, "constructor StrategyThread end!");
    }

    public void died() {
        synchronized (ImsManager.class) {
            StrategyThread.getInstance().quitSafely();
            clear();
            if (mStrategyMap != null) mPackageMap.clear();
            if (mPackageMap != null) mStrategyMap.clear();
            sInstance = null;
        }
    }

    public static ImsManager getInstance() {
        synchronized (ImsManager.class) {
            if (sInstance == null) {
                sInstance = new ImsManager();
            }
            return sInstance;
        }
    }

    public void put(String pkgName, AbsBaseEntity appEntity) {
        synchronized (ImsManager.class) {
            AbsBaseEntity entity = mPackageMap.get(pkgName);
            if (entity != null) {
                entity.clear();     //Remove all pending strategies
            }
            if (pkgName != null) {
                mPackageMap.put(pkgName, appEntity);
            }
        }
    }

    public AbsBaseEntity get(String pkgName) {
        synchronized (ImsManager.class) {
            if (pkgName != null) {
                AbsBaseEntity appEntity = mPackageMap.get(pkgName);
                return appEntity;
            }
            return null;
        }
    }

    public void remove(String pkgName) {
        synchronized (ImsManager.class) {
            AbsBaseEntity entity = mPackageMap.get(pkgName);
            if (entity != null) {
                mPackageMap.remove(pkgName);
                entity.clear();
            }
        }
    }

    public void clear() {
        synchronized (ImsManager.class) {
            for(String key: mPackageMap.keySet()) {
                AbsBaseEntity entity = mPackageMap.get(key);
                if (entity != null) {
                    entity.clear();
                }
            }
            mPackageMap.clear();
        }
    }

    //usually called by other module
    public boolean apply(String pkgName, AbsBaseStrategy strategy) {
        synchronized (ImsManager.class) {
            if (strategy != null) {
                if (!strategy.isEnabled()) {
                    Log.e(TAG, "Strategy:" + strategy.toString() + "is disabled!");
                    return false;
                }
                AbsBaseEntity entity = createEntityByPackageName(pkgName);
                this.put(pkgName, entity);
                strategy.setEntity(entity);
                strategy.apply();
            }
        }
        return true;
    }

    private AbsBaseEntity createEntityByPackageName(String pkgName) {
        Property property = new Property();
        property.setPackageName("com.meizu.filemanager");
        property.setUid(5050);
        AppEntity appEntity = new AppEntity(property);
        return appEntity;
    }
}
