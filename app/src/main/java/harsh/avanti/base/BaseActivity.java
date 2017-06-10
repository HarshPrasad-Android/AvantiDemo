package harsh.avanti.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

/**
 * The base activity of the application that all other activities extend
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        onCreatingBase(savedInstanceState);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this);
        initViews();
        initVariables();
    }

    /**
     * Called when the base activity's onCreate is called
     *
     * @param savedInstanceState the bundle received in onCreate of Base Activity
     */
    protected abstract void onCreatingBase(Bundle savedInstanceState);

    /**
     * Gets the content view of the activity
     *
     * @return the resource id of the content view layout file
     */
    protected abstract int getContentView();

    /**
     * Called to initialize the views of the class and set listeners on them
     */
    protected abstract void initViews();

    /**
     * Called to initialize the data members of the class
     */
    protected abstract void initVariables();
}
