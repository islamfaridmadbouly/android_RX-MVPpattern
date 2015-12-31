package com.example.islam.gitubrepositories.view;

import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;

/**
 * Created by islam on 12/24/2015.
 */
public interface RepoDetailsView {

    public void showMessage(String message);


    public void showContributorsProgress();


    public void setNewIssue(Issue issue);

    public void setContributors(Contributor contributor);

}
