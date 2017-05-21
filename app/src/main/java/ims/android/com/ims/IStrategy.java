package ims.android.com.ims;

import android.os.RemoteException;

/**
 * Created by frank on 10/29/16.
 */

public interface IStrategy<T> {

    /**
     *
     * @return
     */
    T get();

    /**
     * handle the strategy here
     */
    //throws RemoteException?
    boolean isEnabled();

    int apply();

    int cancel();

    void finished();

    int onProcessIms();

}
