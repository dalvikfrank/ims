package ims.android.com.ims;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import ims.android.com.ims.util.common.Debug;


/**
 * Created by frank on 10/29/16.
 */

public abstract class AbsDelayableStrategy extends AbsBaseStrategy implements IDelayable {
    private final static String TAG = AbsDelayableStrategy.class.getSimpleName() + ": ";
    private StrategyThread mStrategyThread; // TODO:memory leak?
    private Handler mHandler;

    public AbsDelayableStrategy() {
        super();
        createStrategygThread();

    }

    public AbsDelayableStrategy(AppEntity entity) {
        super(entity);
        createStrategygThread();
    }

    private void createStrategygThread() {
        mStrategyThread = StrategyThread.getInstance();
        mHandler = new Handler(mStrategyThread.getHandlerThread().getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                handleMsg(msg);
            }
        };
    }

    @Override
    public void removeCallbacksAndMessages(Object token) {
        //remove all the messages in the event queue
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public void removeMessages(int what) {
        mHandler.removeMessages(what);
    }

    @Override
    public void sendMessageDelayed(Message msg, long delayMillis) {
        mHandler.sendMessageDelayed(msg, delayMillis);
    }

    @Override
    public void sendStickyMessageDelayed(Message msg, long delayMillis) {

    }

    @Override
    public void quitSafely() {
        //quite the looper, and then quite the loop thread automatically
        if (Debug.DEBUG_STRATEGY) Log.d(TAG, "quit pending messages and looper safely !");
        mHandler.removeCallbacksAndMessages(null);
        mStrategyThread.quitSafely();
    }
}
