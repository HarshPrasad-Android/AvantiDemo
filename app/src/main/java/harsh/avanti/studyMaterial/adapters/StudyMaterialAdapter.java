package harsh.avanti.studyMaterial.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import harsh.avanti.R;
import harsh.avanti.interfaces.IOnItemClickListener;
import harsh.avanti.storage.StudyMaterialModel;

/**
 * Created by Harsh.Prasad on 09-06-2017.
 */
public class StudyMaterialAdapter extends RecyclerView.Adapter<StudyMaterialAdapter.StudyMaterialViewHolder> {
    private List<StudyMaterialModel> mStudyMaterialList;
    private IOnItemClickListener onItemClickListener;

    public StudyMaterialAdapter(List<StudyMaterialModel> studyMaterialList) {
        this.mStudyMaterialList = studyMaterialList;
    }

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public StudyMaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study_material, parent, false);
        return new StudyMaterialViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(StudyMaterialViewHolder holder, final int position) {
        holder.mStudyMaterialTV.setText(mStudyMaterialList.get(position).getStudyMaterial());
    }

    public int getItemCount() {
        return mStudyMaterialList.size();
    }

    public static class StudyMaterialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private IOnItemClickListener onItemClickListener;
        public TextView mStudyMaterialTV;

        public StudyMaterialViewHolder(View itemView, IOnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            mStudyMaterialTV = (TextView) itemView.findViewById(R.id.tv_study_material);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null){
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}