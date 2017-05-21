package ims.android.com.ims;

import android.os.HandlerThread;
import android.util.Log;

import ims.android.com.ims.util.common.Debug;

/**
 * Created by frank on 5/21/17.
 */

    public class StrategyThread {
    private final static String TAG = StrategyThread.class.getSimpleName() + ": ";
    private static StrategyThread sInstance = null;
    private  HandlerThread mHandlerThread = null;

    public StrategyThread() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread(TAG);
            mHandlerThread.start();
        }
        if (Debug.DEBUG_FRAMEWORK)
            Log.d(TAG, "constructor StrategyThread end!");
    }

    public static StrategyThread getInstance() {
        synchronized (StrategyThread.class) {
            if (sInstance == null) {
                sInstance = new StrategyThread();
            }
            return sInstance;
        }
    }

    public HandlerThread getHandlerThread() {
        return mHandlerThread;
    }

    public void quitSafely () {
        mHandlerThread.quitSafely();
        sInstance = null;
        if (Debug.DEBUG_FRAMEWORK) Log.d(TAG, "release finished!");
    }
}
