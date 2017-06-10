package harsh.avanti.studyMaterial.interfaces;

import java.util.List;

import harsh.avanti.storage.StudyMaterialModel;

/**
 * Created by Harsh on 09-06-2017.
 */

public interface IStudyMaterialView {

    void onStudyMaterialFound(List<StudyMaterialModel> studyMaterialModels);
}
