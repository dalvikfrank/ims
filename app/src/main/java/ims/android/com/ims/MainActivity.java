package ims.android.com.ims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName() + ": ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        Button bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);
        Button bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        Button bt3= (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button:
                Log.i(TAG, " Button apply onClicked! ");
//                Property property = new Property();
//                property.setPackageName("com.meizu.filemanager");
//                property.setUid(5050);
//                AppEntity appEntity = new AppEntity(property);
//                BgStrategy bgStrategy = new BgStrategy(appEntity);
//                ImsManager.getInstance().put("com.meizu.filemanager", appEntity);
//                bgStrategy.apply();
                ImsManager.getInstance().apply("com.meizu.filemanager", new BgStrategy());
                break;
            case R.id.button2:
                Log.i(TAG, " Button cancel onClicked! ");
                ImsManager.getInstance().remove("com.meizu.filemanager");
                break;
            case R.id.button3:
                Log.i(TAG, " Button outside onClicked! ");
                ImsManager.getInstance().apply("com.meizu.filemanager", new BgStrategy());
                break;

            default:
                break;
        }
    }
}
