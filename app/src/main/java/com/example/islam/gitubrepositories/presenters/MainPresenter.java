package com.example.islam.gitubrepositories.presenters;

import com.example.islam.gitubrepositories.model.GithubService;
import com.example.islam.gitubrepositories.model.pojos.AllRepos;
import com.example.islam.gitubrepositories.view.MainView;

import java.util.LinkedHashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by islam on 12/23/2015.
 */
public class MainPresenter {
    String EndPoint;
    MainView mMainView;

    public MainPresenter(MainView mainView, String endpoint) {
        mMainView = mainView;
        EndPoint = endpoint;
    }

    public void loadReposOfSpecifiLang(String lang) {
        mMainView.showProgress();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("q=language", lang);
        params.put("sort", "stars");
        GithubService.getInstance(EndPoint).rxRequestAllReposForSpecificLang(params).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AllRepos>() {
            @Override
            public void onCompleted() {
                mMainView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                mMainView.showMessage(e.getMessage());
                mMainView.hideProgress();
            }

            @Override
            public void onNext(AllRepos allRepos) {
                if (allRepos != null) {
                    mMainView.setItems(allRepos.getItems());
                }
            }
        });
    }

    public void enterLanguage() {
        if (mMainView.isTitleVisible()) {
            mMainView.hideTitle();
            mMainView.showEnterLanguageEditText();
            mMainView.showKeyboard();
        } else {
            String language = mMainView.getLanguageString();
            if (language != null && language.trim().equals("")) {
                mMainView.showMessage("Enter Valid String");
            } else {
                loadReposOfSpecifiLang(language);
                mMainView.hideKeyboard();
                mMainView.hideEnterLanguageEditText();
                mMainView.showTitle(language);
            }
        }

    }
}
