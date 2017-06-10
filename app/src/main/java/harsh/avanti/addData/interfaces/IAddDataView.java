package harsh.avanti.addData.interfaces;

import harsh.avanti.storage.StudyMaterialModel;

/**
 * Created by Harsh on 10-06-2017.
 */

public interface IAddDataView {

    String getStudyMaterial();

    void onValidationSuccess();

    void onValidationFailure(int errorMsg);

    void onStudyMaterialSaved(StudyMaterialModel studyMaterialModel);
}
