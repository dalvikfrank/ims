package ims.android.com.ims;

/**
 * Created by frank on 10/31/16.
 */

public interface IProperty {

    //usually update frequently
    public int WORKING = 1;
    public int RECENT = 1 << 1;

    //usually update unfrequently
    public int SUPPPER = 1 << 2;
    public int FAVIORATE = 1 << 3;
    public int USING = 1 << 4;
    public int OTHER = 1 << 5;

    IProperty getProperty();

    void setProperty(Property property);

    /**
     * update specific app property manually
     */
    void update();

    /**
     * receive callback from alpheme
     */
    void onPropertyChange();
}
