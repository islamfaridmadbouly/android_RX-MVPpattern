package com.example.islam.gitubrepositories.presenters;

import com.example.islam.gitubrepositories.model.GithubService;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;
import com.example.islam.gitubrepositories.view.RepoDetailsView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    public void loadIssues(String endPoint, String userLogin, String name, String sort, int numberOfNeededElements) {
        mGithubService = GithubService.getInstance(endPoint);
        mGithubService.issues(userLogin, name, sort).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<List<Issue>, Observable<Issue>>() {
            @Override
            public Observable<Issue> call(List<Issue> issues) {
                return Observable.from(issues);
            }
        }).take(numberOfNeededElements).subscribe(new Subscriber<Issue>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Issue issue) {
                mRepoDetailsView.setNewIssue(issue);
            }
        });
    }

    public void loadContributors(String endPoint, String userLogin, String name,int numberOfContributions) {
        mRepoDetailsView.showContributorsProgress();
        mGithubService = GithubService.getInstance(endPoint);
        mGithubService.contributors(userLogin, name).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<List<Contributor>, Observable<Contributor>>() {
            @Override
            public Observable<Contributor> call(List<Contributor> contributors) {
                return Observable.from(contributors);
            }
        }).take(numberOfContributions).subscribe(new Subscriber<Contributor>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRepoDetailsView.showMessage(e.getMessage());
            }

            @Override
            public void onNext(Contributor contributor) {
                mRepoDetailsView.setContributors(contributor);
            }
        });
    }
}
