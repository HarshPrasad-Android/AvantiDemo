package harsh.avanti.addData;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import harsh.avanti.AppConstants;
import harsh.avanti.R;
import harsh.avanti.addData.interfaces.IAddDataPresenter;
import harsh.avanti.addData.interfaces.IAddDataView;
import harsh.avanti.base.BaseActivity;
import harsh.avanti.storage.StudyMaterialModel;

import static harsh.avanti.AppConstants.KEY_TYPE;

public class AddDataActivity extends BaseActivity implements IAddDataView {

    @BindView(R.id.btn_save)
    Button mSaveBtn;
    @BindView(R.id.et_study_material)
    EditText mStudyMaterialET;

    private IAddDataPresenter mPresenter;
    private int mType;
    private StudyMaterialModel mStudyMaterialModel;

    @Override
    protected void onCreatingBase(Bundle savedInstanceState) {
        Intent intent = getIntent();
        mType = intent.getIntExtra(KEY_TYPE, -1);
        mStudyMaterialModel = intent.getParcelableExtra(AppConstants.KEY_MODEL);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_data;
    }

    @Override
    protected void initViews() {
        if (mStudyMaterialModel != null) {
            mStudyMaterialET.setText(mStudyMaterialModel.getStudyMaterial());
        }

        mSaveBtn.setOnClickListener(this);
    }

    @Override
    protected void initVariables() {
        mPresenter = new AddDataPresenter(this);
    }

    @Override
    public String getStudyMaterial() {
        return mStudyMaterialET.getText().toString().trim();
    }

    @Override
    public void onValidationSuccess() {
        if (mStudyMaterialModel == null) {
            mPresenter.saveStudyMaterial(mType, getStudyMaterial());
        } else {
            mPresenter.updateStudyMaterial(mStudyMaterialModel.getId(), mType, getStudyMaterial());
        }
    }

    @Override
    public void onValidationFailure(int errorMsg) {
        Snackbar.make(mSaveBtn, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onStudyMaterialSaved(StudyMaterialModel studyMaterialModel) {
        Intent intent = new Intent();
        if (mStudyMaterialModel == null) {
            intent.putExtra(AppConstants.KEY_MODEL, studyMaterialModel);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                mPresenter.validateData();
        }
    }
}
