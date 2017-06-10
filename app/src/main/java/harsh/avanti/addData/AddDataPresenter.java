package harsh.avanti.addData;

import android.text.TextUtils;

import harsh.avanti.R;
import harsh.avanti.addData.interfaces.IAddDataPresenter;
import harsh.avanti.addData.interfaces.IAddDataView;
import harsh.avanti.storage.StudyMaterialModel;

/**
 * Created by Harsh on 10-06-2017.
 */

public class AddDataPresenter implements IAddDataPresenter, AddDataInteractor.IDataCallbacks {

    private IAddDataView mView;
    private AddDataInteractor mInteractor;

    public AddDataPresenter(IAddDataView view){
        mView = view;
        mInteractor = new AddDataInteractor();
    }

    @Override
    public void validateData() {
        String studyMaterial = mView.getStudyMaterial();
        if (TextUtils.isEmpty(studyMaterial)){
            mView.onValidationFailure(R.string.err_study_material_empty);
            return;
        }
        mView.onValidationSuccess();
    }

    @Override
    public void saveStudyMaterial(int type, String studyMaterial) {
        mInteractor.saveStudyMaterial(type, studyMaterial, this);
    }

    @Override
    public void updateStudyMaterial(long id, int type, String studyMaterial) {
        mInteractor.updateStudyMaterial(id, type, studyMaterial, this);
    }

    @Override
    public void onDataSaved(StudyMaterialModel studyMaterialModel) {
        mView.onStudyMaterialSaved(studyMaterialModel);
    }
}
