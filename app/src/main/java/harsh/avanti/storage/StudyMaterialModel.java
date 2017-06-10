package harsh.avanti.storage;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Harsh on 10-06-2017.
 */

public class StudyMaterialModel extends RealmObject implements Parcelable {
    
    @PrimaryKey
    private long mId;
    private int mType;
    private String mStudyMaterial;

    public StudyMaterialModel() {
    }

    public StudyMaterialModel(long mId, int mType, String mStudyMaterial) {
        this.mId = mId;
        this.mType = mType;
        this.mStudyMaterial = mStudyMaterial;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public int getType() {
        return mType;
    }

    public void setType(int mType) {
        this.mType = mType;
    }

    public String getStudyMaterial() {
        return mStudyMaterial;
    }

    public void setStudyMaterial(String mStudyMaterial) {
        this.mStudyMaterial = mStudyMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudyMaterialModel model = (StudyMaterialModel) o;

        return mId == model.mId;

    }

    @Override
    public int hashCode() {
        return (int) (mId ^ (mId >>> 32));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mId);
        dest.writeInt(this.mType);
        dest.writeString(this.mStudyMaterial);
    }

    protected StudyMaterialModel(Parcel in) {
        this.mId = in.readLong();
        this.mType = in.readInt();
        this.mStudyMaterial = in.readString();
    }

    public static final Parcelable.Creator<StudyMaterialModel> CREATOR = new Parcelable.Creator<StudyMaterialModel>() {
        @Override
        public StudyMaterialModel createFromParcel(Parcel source) {
            return new StudyMaterialModel(source);
        }

        @Override
        public StudyMaterialModel[] newArray(int size) {
            return new StudyMaterialModel[size];
        }
    };
}
