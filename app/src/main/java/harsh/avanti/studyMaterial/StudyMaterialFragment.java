package harsh.avanti.studyMaterial;


import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import harsh.avanti.AppConstants;
import harsh.avanti.R;
import harsh.avanti.addData.AddDataActivity;
import harsh.avanti.base.BaseFragment;
import harsh.avanti.interfaces.IOnItemClickListener;
import harsh.avanti.storage.StudyMaterialModel;
import harsh.avanti.studyMaterial.adapters.StudyMaterialAdapter;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialPresenter;
import harsh.avanti.studyMaterial.interfaces.IStudyMaterialView;

import static harsh.avanti.AppConstants.KEY_TYPE;
import static harsh.avanti.AppConstants.RC_ADD_DATA;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyMaterialFragment extends BaseFragment implements IStudyMaterialView, IOnItemClickListener, SearchView.OnQueryTextListener {

    @BindView(R.id.rv_study_material)
    RecyclerView mStudyMaterialRV;

    private IStudyMaterialPresenter mPresenter;
    private StudyMaterialAdapter mStudyMaterialAdapter;
    private List<StudyMaterialModel> mStudyMaterialList;
    private int mType;
    private SearchView mSearchView;
    private MenuItem mSearchMenuItem;

    public static StudyMaterialFragment newInstance(int type) {
        StudyMaterialFragment fragment = new StudyMaterialFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TYPE, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initBundle(Bundle bundle) {
        mType = bundle.getInt(KEY_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_study_material;
    }

    @Override
    protected void initViews(View view) {
        mActivity.invalidateOptionsMenu();
        setHasOptionsMenu(true);
        mStudyMaterialRV.setLayoutManager(new LinearLayoutManager(mActivity));
        mStudyMaterialRV.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initVariables() {
        mPresenter = new StudyMaterialPresenter(this);

        mStudyMaterialList = new ArrayList<>();
        mStudyMaterialAdapter = new StudyMaterialAdapter(mStudyMaterialList);
        mStudyMaterialAdapter.setOnItemClickListener(this);
        mStudyMaterialRV.setAdapter(mStudyMaterialAdapter);

        mPresenter.fetchStudyMaterial(mType);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_study_material,menu);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint(getString(R.string.hint_search));
        mSearchMenuItem = menu.findItem(R.id.action_search);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void resetSearch(){
        mSearchView.setQuery("", true);
        mSearchMenuItem.collapseActionView();
    }

    @Override
    public void onStudyMaterialFound(List<StudyMaterialModel> studyMaterialModels) {
        mStudyMaterialList.addAll(studyMaterialModels);
        mStudyMaterialAdapter.notifyDataSetChanged();
        mStudyMaterialAdapter.getFilter().filter("");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_DATA && resultCode == Activity.RESULT_OK) {
            StudyMaterialModel model = data.getParcelableExtra(AppConstants.KEY_MODEL);
            int indexOfModel = mStudyMaterialList.indexOf(model);
            if (indexOfModel == -1){
                mStudyMaterialList.add(model);
            }else{
                mStudyMaterialList.set(indexOfModel, model);
            }
            mStudyMaterialAdapter.notifyDataSetChanged();
            mStudyMaterialAdapter.getFilter().filter("");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mActivity, AddDataActivity.class);
        intent.putExtra(KEY_TYPE, mType);
        intent.putExtra(AppConstants.KEY_MODEL, mStudyMaterialList.get(position));
        startActivityForResult(intent, RC_ADD_DATA);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mStudyMaterialAdapter.getFilter().filter(query);
        mSearchView.clearFocus();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mStudyMaterialAdapter.getFilter().filter(newText);
        return true;
    }
}
