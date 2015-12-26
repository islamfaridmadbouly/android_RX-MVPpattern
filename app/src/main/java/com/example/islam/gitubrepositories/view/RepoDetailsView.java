package com.example.islam.gitubrepositories.view;

import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;

import java.util.List;

/**
 * Created by islam on 12/24/2015.
 */
public interface RepoDetailsView {
    public void showIssuesProgress();

    public void showMessage(String message);

    public void hideIssuesProgress();

    public void showContributorsProgress();


    public void setIssues(List<Issue> issues);

    public void setContributors(List<Contributor> contributors);

}
