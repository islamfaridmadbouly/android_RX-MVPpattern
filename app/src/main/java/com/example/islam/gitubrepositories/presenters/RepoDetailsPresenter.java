package com.example.islam.gitubrepositories.presenters;

import android.util.Log;

import com.example.islam.gitubrepositories.model.GithubService;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;
import com.example.islam.gitubrepositories.view.RepoDetailsView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by islam on 12/24/2015.
 */
public class RepoDetailsPresenter {
    RepoDetailsView mRepoDetailsView;
    GithubService mGithubService;

    public RepoDetailsPresenter(RepoDetailsView repoDetailsView) {
        mRepoDetailsView = repoDetailsView;
    }

    public void loadIssues(String endPoint,String userLogin,String name,String sort) {
        mRepoDetailsView.showIssuesProgress();
        mGithubService = GithubService.getInstance(endPoint);
        mGithubService.issues(userLogin,name,sort).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Issue>>() {
            @Override
            public void onCompleted() {
                mRepoDetailsView.hideIssuesProgress();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("", "");
            }

            @Override
            public void onNext(List<Issue> issues) {

                mRepoDetailsView.setIssues(issues.size() > 3 ? new ArrayList<>(issues.subList(0, 3)) : issues);
            }
        });
    }

    public void loadContributors(String endPoint,String userLogin,String name) {
        mRepoDetailsView.showContributorsProgress();
        mGithubService = GithubService.getInstance(endPoint);
        mGithubService.contributors(userLogin,name).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Contributor>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepoDetailsView.showMessage(e.getMessage());
            }

            @Override
            public void onNext(List<Contributor> contributors) {
                mRepoDetailsView.setContributors(contributors.size() > 3 ? new ArrayList<>(contributors.subList(0, 3)) : contributors);
            }
        });
    }
}
