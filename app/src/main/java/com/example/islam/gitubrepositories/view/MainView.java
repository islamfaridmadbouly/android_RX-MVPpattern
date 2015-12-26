package com.example.islam.gitubrepositories.view;

import com.example.islam.gitubrepositories.model.pojos.RepoItem;

import java.util.List;

/**
 * Created by islam on 12/23/2015.
 */
public interface MainView {
    public void showProgress();

    public void showEnterLanguageEditText();

    public void hideEnterLanguageEditText();

    public void showTitle(String title);

    public void hideTitle();

    public void hideProgress();

    public void setItems(List<RepoItem> items);

    public void showMessage(String message);

    public boolean isTitleVisible();

    public String getLanguageString();

    public void showKeyboard();

    public void hideKeyboard();
}
