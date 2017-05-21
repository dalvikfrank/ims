package ims.android.com.ims;

import android.os.Message;

/**
 * Created by frank on 10/29/16.
 */

public interface IDelayable {
    /**
     * cancel current strategy interrupted by other message
     *
     * @return
     */
    void removeCallbacksAndMessages(Object token);

    /**
     * cancel specific message
     * @param what
     */
    void removeMessages(int what);

    /**
     * send delay message
     */
    void sendMessageDelayed(Message msg, long delayMillis);

    /**
     * Make sure the message will NOT be canceled by interface cancel()
     *
     * @param msg
     * @param delayMillis
     */
    void sendStickyMessageDelayed(Message msg, long delayMillis);

    /**
     * handle delay messages
     * Don't same name with the handleMessage(Message msg), or will cause dead loop
     */
    void handleMsg(Message msg);

    /**
     *
     */
    void quitSafely();
}
