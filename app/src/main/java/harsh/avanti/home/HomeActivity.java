package harsh.avanti.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import harsh.avanti.R;
import harsh.avanti.addData.AddDataActivity;
import harsh.avanti.base.BaseActivity;
import harsh.avanti.customviews.SlidingTabLayout;
import harsh.avanti.home.adapters.HomePagerAdapter;
import harsh.avanti.studyMaterial.StudyMaterialFragment;

import static harsh.avanti.AppConstants.KEY_TYPE;
import static harsh.avanti.AppConstants.RC_ADD_DATA;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.vp_home)
    ViewPager mHomeVP;
    @BindView(R.id.stl_home)
    SlidingTabLayout mHomeSTL;
    @BindView(R.id.fab_add_study_material)
    FloatingActionButton mAddStudyMaterialFab;
    private HomePagerAdapter mAdapter;

    @Override
    protected void onCreatingBase(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        mHomeVP.setOffscreenPageLimit(2);
        mHomeSTL.setDistributeEvenly(true);
        mHomeSTL.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(HomeActivity.this, android.R.color.white);
            }
        });
        mAddStudyMaterialFab.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        mAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mHomeVP.setAdapter(mAdapter);
        mHomeSTL.setViewPager(mHomeVP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_study_material:
                Intent intent = new Intent(this, AddDataActivity.class);
                intent.putExtra(KEY_TYPE, mHomeVP.getCurrentItem());
                Fragment fragment = mAdapter.getItem(mHomeVP.getCurrentItem());
                ((StudyMaterialFragment)fragment).resetSearch();
                fragment.startActivityForResult(intent, RC_ADD_DATA);
                break;
        }
    }
}
