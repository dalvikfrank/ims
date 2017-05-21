package ims.android.com.ims.util.common;

/**
 * Created by frank on 11/1/16.
 */

public final class Debug {
    public final static boolean DEBUG = false;
    public final static boolean DEBUG_ALL = true;
    public final static boolean DEBUG_FRAMEWORK = false | DEBUG_ALL;
    public final static boolean DEBUG_QUEUE = false | DEBUG_ALL;
    public final static boolean DEBUG_STRATEGY = true | DEBUG_ALL;
    public final static boolean DEBUG_DEVICE = true | DEBUG_ALL;

    //project
    public final static boolean DEBUG_PRO_PUSH = true | DEBUG_ALL;
}
