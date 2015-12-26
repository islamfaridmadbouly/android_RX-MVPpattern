package com.example.islam.gitubrepositories.view;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.presenters.MainPresenter;
import com.example.islam.gitubrepositories.view.adapters.LangReposAdapter;
import com.example.islam.gitubrepositories.view.fragments.MainFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
    @ViewById(R.id.repos_recyclerview)
    RecyclerView mLangRepos;
    LangReposAdapter mLangReposAdapter;
    MainPresenter mMainPresenter;

    @AfterViews
    void init() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contaner_relativelayout, new MainFragment_(), MainFragment_.class.getName())
                .commit();
    }
}
