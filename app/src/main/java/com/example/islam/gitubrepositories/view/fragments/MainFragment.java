package com.example.islam.gitubrepositories.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.model.pojos.RepoItem;
import com.example.islam.gitubrepositories.presenters.MainPresenter;
import com.example.islam.gitubrepositories.view.DividerItemDecoration;
import com.example.islam.gitubrepositories.view.MainView;
import com.example.islam.gitubrepositories.view.adapters.LangReposAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by islam on 12/23/2015.
 */
@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment implements MainView {
    @ViewById(R.id.loading_progressbar)
    ProgressBar mLoadingProgressBar;
    @ViewById(R.id.repos_recyclerview)
    RecyclerView mLangRepos;
    @ViewById(R.id.enter_language_imageview)
    ImageView mEnterLangImageView;
    @ViewById(R.id.language_editField)
    EditText mLangEditText;
    @ViewById(R.id.toolbar_title)
    TextView mToolBarTitle;
    LangReposAdapter mLangReposAdapter;
    MainPresenter mMainPresenter;
    List<RepoItem> mAllReposItems;

    @AfterViews
    void init() {
        mLangRepos.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMainPresenter = new MainPresenter(this, getResources().getString(R.string.all_repos_endpoint_url));
        mLangReposAdapter = new LangReposAdapter(getActivity());
        mLangRepos.setAdapter(mLangReposAdapter);
        mMainPresenter.loadReposOfSpecifiLang("java");
        mToolBarTitle.setText("java");
        mEnterLangImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mLangRepos.setVisibility(View.GONE);
    }

    @Override
    public void showEnterLanguageEditText() {
        mLangEditText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEnterLanguageEditText() {
        mLangEditText.setVisibility(View.GONE);
    }

    @Override
    public void showTitle(String title) {
        mToolBarTitle.setVisibility(View.VISIBLE);
        mToolBarTitle.setText(title);
    }

    @Override
    public void hideTitle() {
        mToolBarTitle.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mLoadingProgressBar.setVisibility(View.GONE);
        mLangRepos.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<RepoItem> items) {
        mAllReposItems = items;
        mLangReposAdapter.setLangReposItemList(items);
        mLangReposAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isTitleVisible() {
        return mToolBarTitle.getVisibility() == View.VISIBLE ? true : false;
    }

    @Override
    public String getLanguageString() {
        return mLangEditText.getText().toString();
    }

    @Override
    public void showKeyboard() {
        mLangEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity(). getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mLangEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputManager =
                (InputMethodManager) getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Click(R.id.enter_language_imageview)
    void enterLanguageClick() {
        mMainPresenter.enterLanguage();
    }
}
