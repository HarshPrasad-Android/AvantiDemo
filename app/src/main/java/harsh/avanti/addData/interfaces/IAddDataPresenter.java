package harsh.avanti.addData.interfaces;

/**
 * Created by Harsh on 10-06-2017.
 */

public interface IAddDataPresenter {

    void validateData();

    void saveStudyMaterial(int type, String studyMaterial);

    void updateStudyMaterial(long id, int type, String studyMaterial);
}
