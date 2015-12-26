package com.example.islam.gitubrepositories.view.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;
import com.example.islam.gitubrepositories.model.pojos.RepoItem;
import com.example.islam.gitubrepositories.presenters.MainPresenter;
import com.example.islam.gitubrepositories.presenters.RepoDetailsPresenter;
import com.example.islam.gitubrepositories.view.RepoDetailsView;
import com.example.islam.gitubrepositories.view.adapters.LangReposAdapter;
import com.example.islam.gitubrepositories.view.adapters.RepoDetailsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by islam on 12/23/2015.
 */
@EFragment(R.layout.fragment_main)
public class RepoDetailsFragment extends Fragment implements RepoDetailsView {
    @ViewById(R.id.repos_recyclerview)
    RecyclerView mRepoDetailsList;
    @ViewById(R.id.toolbar_title)
    TextView mToolBarTitle;
    RepoDetailsAdapter mRepoDetailsAdapter;
    RepoDetailsPresenter mRepoDetailsPresenter;
    @FragmentArg
    RepoItem mRepoItem;


    @AfterViews
    public void init() {
        mRepoDetailsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRepoDetailsPresenter = new RepoDetailsPresenter(this);
        mRepoDetailsAdapter = new RepoDetailsAdapter(getActivity());
        mRepoDetailsAdapter.setRepoItem(mRepoItem);
        mRepoDetailsList.setAdapter(mRepoDetailsAdapter);
        mRepoDetailsPresenter.loadIssues(getActivity().getResources().getString(R.string.repo_queries_endpoint_url), mRepoItem.getOwner().getLogin(), mRepoItem.getName(), "created");
        mRepoDetailsPresenter.loadContributors(getActivity().getResources().getString(R.string.repo_queries_endpoint_url), mRepoItem.getOwner().getLogin(), mRepoItem.getName());
    }

    @Override
    public void onResume() {
        super.onResume();
        mToolBarTitle.setText(mRepoItem.getLanguage());
    }

    @Override
    public void showIssuesProgress() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideIssuesProgress() {

    }


    @Override
    public void showContributorsProgress() {

    }

    @Override
    public void setIssues(List<Issue> issues) {
        mRepoDetailsAdapter.setIssues(issues);
        mRepoDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setContributors(List<Contributor> contributors) {
        mRepoDetailsAdapter.setContributors(contributors);
        mRepoDetailsAdapter.notifyDataSetChanged();
    }
}
