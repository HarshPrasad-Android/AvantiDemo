package harsh.avanti.home.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import harsh.avanti.studyMaterial.StudyMaterialFragment;

/**
 * Created by Harsh on 09-06-2017.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>(3);

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
        for (int i = 0; i < 3; i++) {
            mFragmentList.add(StudyMaterialFragment.newInstance(i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "CHAPTER";
            case 1:
                return "RECENT";
            case 2:
                return "IMPORTANT";
        }
        return null;
    }
}
