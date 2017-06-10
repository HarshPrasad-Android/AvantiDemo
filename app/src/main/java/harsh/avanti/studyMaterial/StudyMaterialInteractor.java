package harsh.avanti.studyMaterial;

import java.util.List;

import harsh.avanti.storage.StudyMaterialModel;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Harsh on 09-06-2017.
 */

public class StudyMaterialInteractor {

    public void fetchStudyMaterial(final int type, final IDataCallbacks iDataCallbacks){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<StudyMaterialModel> studyMaterialModels = realm.where(StudyMaterialModel.class)
                        .equalTo("mType", type).findAll();
                iDataCallbacks.onDataFound(studyMaterialModels);
            }
        });
    }

    interface IDataCallbacks{
        void onDataFound(List<StudyMaterialModel> studyMaterialModels);
    }
}
