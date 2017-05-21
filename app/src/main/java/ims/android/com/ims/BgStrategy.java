package ims.android.com.ims;

import android.os.Message;
import android.util.Log;

import ims.android.com.ims.util.common.Debug;

public final class BgStrategy extends AbsDelayableStrategy {
    private static final String TAG = BgStrategy.class.getSimpleName() + ": ";
    private static final int MSG_BACKGROUND = 1;
    private final static int PUSH_STRATEGY_DELAY_TIME = 5 * 1000;
    private int mTimes = 0;
    private int mUid;

    public BgStrategy() {
        super();
    }

    public BgStrategy(AppEntity entity) {
        super(entity);
    }

    /**
     * NOTE: apply() and handleMsg() are in different threads, take care!
     */
    @Override
    public int apply() {
        if (Debug.DEBUG_STRATEGY) Log.d(TAG, "Apply entered");
        super.apply();
        mUid = mEntity.getProperty().getUid();
        Message message = new Message();
        message.what = MSG_BACKGROUND;
        sendMessageDelayed(message, PUSH_STRATEGY_DELAY_TIME);
        if (Debug.DEBUG_STRATEGY) Log.d(TAG, "Apply exited mUid = " + mUid);
        return HandleResult.CONSUMED;
    }

    @Override
    public int cancel() {
        if (Debug.DEBUG_STRATEGY) Log.d(TAG, "Apply cancel now");
        removeMessages(MSG_BACKGROUND);
        /**
         * NOTE: don't call finished here, as it has been called when adding newly strategy
         * see @ImsManager.put()
         */
        return HandleResult.CONSUMED;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void handleMsg(Message msg) {
        if (Debug.DEBUG_STRATEGY) Log.d(TAG,  "handling delayed message");
        switch (msg.what) {
            case MSG_BACKGROUND:
            {
                handleBackGround(mTimes);
            }
                break;
            default:
                break;
        }
    }

    private void handleBackGround(int times) {
        if (Debug.DEBUG_STRATEGY) Log.d(TAG,  "handling handleBackGround mTimes =" + mTimes);
        switch (mTimes) {
            case 0:
                onProcessIms();
                mTimes++;
                Message message = new Message();
                message.what = MSG_BACKGROUND;
                sendMessageDelayed(message, PUSH_STRATEGY_DELAY_TIME);
                break;
            case 1:
                onProcessIms();
                mTimes++;
                message = new Message();
                message.what = MSG_BACKGROUND;
                sendMessageDelayed(message, PUSH_STRATEGY_DELAY_TIME);
                break;
            case 2:
                onProcessIms();
                mTimes++;
                finished();
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "BgStrategy";
    }
}
