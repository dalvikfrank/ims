package ims.android.com.ims;

/**
 * Created by frank on 10/31/16.
 */

public class AppEntity extends AbsBaseEntity {
    public AppEntity() {
        super();
    }

    public AppEntity(Property property) {
        super(property);
    }

    @Override
    public String toString() {
        return  "package=" + this.getProperty().getPackageName()
                + " uid=" + this.getProperty().getUid() + " ";
    }
}
