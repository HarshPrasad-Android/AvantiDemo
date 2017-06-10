package harsh.avanti;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Harsh on 10-06-2017.
 */

public class AvantiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
