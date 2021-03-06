package harsh.avanti.addData;

import harsh.avanti.storage.StudyMaterialModel;
import io.realm.Realm;

/**
 * Created by Harsh on 10-06-2017.
 */

public class AddDataInteractor {

    public void saveStudyMaterial(final int type, final String studyMaterial, final IDataCallbacks iDataCallbacks){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long id = getKey();
                StudyMaterialModel model = new StudyMaterialModel(id, type, studyMaterial);
                realm.copyToRealm(model);
                iDataCallbacks.onDataSaved(new StudyMaterialModel(id, type, studyMaterial));
            }
        });
    }

    public void updateStudyMaterial(final long id, final int type, final String studyMaterial, final IDataCallbacks iDataCallbacks){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                StudyMaterialModel model = new StudyMaterialModel(id, type, studyMaterial);
                realm.copyToRealmOrUpdate(model);
                iDataCallbacks.onDataSaved(new StudyMaterialModel(id, type, studyMaterial));
            }
        });
    }

    private long getKey() {
        Realm realm = Realm.getDefaultInstance();
        Number id = realm.where(StudyMaterialModel.class).max("mId");
        if (id == null){
            return 0;
        }else{
            return id.longValue() + 1;
        }
    }

    interface IDataCallbacks{
        void onDataSaved(StudyMaterialModel studyMaterialModel);
    }
}
