package harsh.avanti.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import harsh.avanti.R;

/**
 * Base Fragment class that all other fragments must extend.
 */
public abstract class BaseFragment extends Fragment{

    protected Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        Bundle bundle = getArguments();
        initBundle(bundle);
        initViews(getView());
        initVariables();
    }

    /**
     * Used to get data from bundle
     * @param bundle the bundle to get data from
     */
    protected abstract void initBundle(Bundle bundle);

    /**
     * Gets the content view of the activity
     *
     * @return the resource id of the content view layout file
     */
    protected abstract int getContentView();

    /**
     * Called to initialize the views of the class and set listeners on them
     * @param view the view of the fragment
     */
    protected abstract void initViews(View view);

    /**
     * Called to initialize the data members of the class
     */
    protected abstract void initVariables();

}
