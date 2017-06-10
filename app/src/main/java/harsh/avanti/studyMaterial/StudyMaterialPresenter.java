package harsh.avanti.studyMaterial;

import java.util.List;

import harsh.avanti.storage.StudyMaterialModel;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialPresenter;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialView;

/**
 * Created by Harsh on 09-06-2017.
 */

public class StudyMaterialPresenter implements IStudyMaterialPresenter, StudyMaterialInteractor.IDataCallbacks {

    private IStudyMaterialView mView;
    private StudyMaterialInteractor mInteractor;

    public StudyMaterialPresenter(IStudyMaterialView view){
        mView = view;
        mInteractor = new StudyMaterialInteractor();
    }


    @Override
    public void fetchStudyMaterial(int type) {
        mInteractor.fetchStudyMaterial(type, this);
    }

    @Override
    public void onDataFound(List<StudyMaterialModel> studyMaterialModels) {
        mView.onStudyMaterialFound(studyMaterialModels);
    }
}
