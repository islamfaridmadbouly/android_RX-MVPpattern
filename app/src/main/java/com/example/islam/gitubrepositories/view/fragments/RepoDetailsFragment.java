package com.example.islam.gitubrepositories.view.fragments;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;
import com.example.islam.gitubrepositories.model.pojos.RepoItem;
import com.example.islam.gitubrepositories.presenters.RepoDetailsPresenter;
import com.example.islam.gitubrepositories.view.RepoDetailsView;
import com.example.islam.gitubrepositories.view.adapters.RepoDetailsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

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
    final int NUMBER_OF_NEEDED_ISSUES = 3;
    final int NUMBER_OF_NEEDED_CONTRIBUTIONS = 3;
    @FragmentArg
    RepoItem mRepoItem;


    @AfterViews
    public void init() {
        mRepoDetailsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRepoDetailsPresenter = new RepoDetailsPresenter(this);
        mRepoDetailsAdapter = new RepoDetailsAdapter(getActivity());
        mRepoDetailsAdapter.setRepoItem(mRepoItem);
        mRepoDetailsList.setAdapter(mRepoDetailsAdapter);
        mRepoDetailsPresenter.loadIssues(getActivity().getResources().getString(R.string.repo_queries_endpoint_url), mRepoItem.getOwner().getLogin(), mRepoItem.getName(), "created", NUMBER_OF_NEEDED_ISSUES);
        mRepoDetailsPresenter.loadContributors(getActivity().getResources().getString(R.string.repo_queries_endpoint_url), mRepoItem.getOwner().getLogin(), mRepoItem.getName(),NUMBER_OF_NEEDED_CONTRIBUTIONS);
    }

    @Override
    public void onResume() {
        super.onResume();
        mToolBarTitle.setText(mRepoItem.getLanguage());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showContributorsProgress() {

    }

    @Override
    public void setNewIssue(Issue issue) {
        mRepoDetailsAdapter.getIssues().add(issue);
        mRepoDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void setContributors(Contributor contributor) {
        mRepoDetailsAdapter.getContributors().add(contributor);
        mRepoDetailsAdapter.notifyDataSetChanged();
    }
}
