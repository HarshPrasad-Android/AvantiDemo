package harsh.avanti.studyMaterial;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import harsh.avanti.AppConstants;
import harsh.avanti.R;
import harsh.avanti.addData.AddDataActivity;
import harsh.avanti.base.BaseFragment;
import harsh.avanti.interfaces.IOnItemClickListener;
import harsh.avanti.storage.StudyMaterialModel;
import harsh.avanti.studyMaterial.adapters.StudyMaterialAdapter;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialPresenter;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialView;

import static harsh.avanti.AppConstants.KEY_TYPE;
import static harsh.avanti.AppConstants.RC_ADD_DATA;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyMaterialFragment extends BaseFragment implements IStudyMaterialView, IOnItemClickListener {

    @BindView(R.id.rv_study_material)
    RecyclerView mStudyMaterialRV;

    private IStudyMaterialPresenter mPresenter;
    private StudyMaterialAdapter mStudyMaterialAdapter;
    private List<StudyMaterialModel> mStudyMaterialList;
    private int mType;

    public static StudyMaterialFragment newInstance(int type) {
        StudyMaterialFragment fragment = new StudyMaterialFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        mType = bundle.getInt(KEY_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_study_material;
    }

    @Override
    protected void initViews(View view) {
        mStudyMaterialRV.setLayoutManager(new LinearLayoutManager(mActivity));
        mStudyMaterialRV.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initVariables() {
        mPresenter = new StudyMaterialPresenter(this);

        mStudyMaterialList = new ArrayList<>();
        mStudyMaterialAdapter = new StudyMaterialAdapter(mStudyMaterialList);
        mStudyMaterialAdapter.setOnItemClickListener(this);
        mStudyMaterialRV.setAdapter(mStudyMaterialAdapter);

        mPresenter.fetchStudyMaterial(mType);
    }

    @Override
    public void onStudyMaterialFound(List<StudyMaterialModel> studyMaterialModels) {
        mStudyMaterialList.addAll(studyMaterialModels);
        mStudyMaterialAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_DATA && resultCode == Activity.RESULT_OK) {
            StudyMaterialModel model = data.getParcelableExtra(AppConstants.KEY_MODEL);
            if (model != null){
                mStudyMaterialList.add(model);
            }
            mStudyMaterialAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mActivity, AddDataActivity.class);
        intent.putExtra(KEY_TYPE, mType);
        intent.putExtra(AppConstants.KEY_MODEL, mStudyMaterialList.get(position));
        startActivityForResult(intent, RC_ADD_DATA);
    }
}
