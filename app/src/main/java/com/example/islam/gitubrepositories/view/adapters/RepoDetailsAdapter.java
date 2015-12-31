package com.example.islam.gitubrepositories.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;
import com.example.islam.gitubrepositories.model.pojos.RepoItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RepoDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final int TITLE_TYPE = 0;
    final int CONTRIBUTORS_TYPE = 1;
    final int ISSUES_TYPE = 2;
        final int CONTRIBUTION_DIFFERENCE = 2;
    final int ISSUES_DIFFERENCE = 6;
    List<Issue> issues = new ArrayList<>();
    List<Contributor> contributors = new ArrayList<>();
    RepoItem repoItem;
    private Context mContext;

    public RepoDetailsAdapter(Context context) {
        this.mContext = context;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public RepoItem getRepoItem() {
        return repoItem;
    }

    public void setRepoItem(RepoItem repoItem) {
        this.repoItem = repoItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TITLE_TYPE:
                View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_body_repo_details_list_row, parent,false);
                holder= new TitlesRepoListRowHolder(titleView);
                break;
            case CONTRIBUTORS_TYPE:
                View contributorView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contributer_of_repo_details_row, parent,false);
                holder = new ContributorsRepoListRowHolder(contributorView);
                break;
            case ISSUES_TYPE:
                View issueView = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_of_repo_details_row, parent,false);
                holder = new IssuesRepoListRowHolder(issueView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TITLE_TYPE: {
                switch (position) {
                    case 0: {
                        ((TitlesRepoListRowHolder) holder).title.setText(repoItem.getName());
                        ((TitlesRepoListRowHolder) holder).body.setText(repoItem.getDescription());
                        ((TitlesRepoListRowHolder) holder).body.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 1: {
                        ((TitlesRepoListRowHolder) holder).title.setText(mContext.getResources().getString(R.string.top_3_contributors));
                        ((TitlesRepoListRowHolder) holder).body.setVisibility(View.GONE);

                        break;
                    }
                    case 5: {
                        ((TitlesRepoListRowHolder) holder).title.setText(mContext.getResources().getString(R.string.top_3_issues));
                        ((TitlesRepoListRowHolder) holder).body.setVisibility(View.GONE);

                        break;
                    }

                }
                break;
            }
            case CONTRIBUTORS_TYPE: {
                if (contributors != null && (contributors.size()> position - CONTRIBUTION_DIFFERENCE) && (position - CONTRIBUTION_DIFFERENCE >= 0)) {
                    ((ContributorsRepoListRowHolder) holder).title.setText(contributors.get(position - CONTRIBUTION_DIFFERENCE).getLogin());
                    Picasso.with(mContext).load(contributors.get(position - CONTRIBUTION_DIFFERENCE).getAvatarUrl())
                            .error(R.mipmap.default_avatar)
                            .placeholder(R.mipmap.default_avatar)
                            .into(((ContributorsRepoListRowHolder) holder).thumbnail);
                    ((ContributorsRepoListRowHolder) holder).contributionNumber.setText(contributors.get(position - CONTRIBUTION_DIFFERENCE).getContributions() + "");
                }
            }
            break;
            case ISSUES_TYPE: {
                if (issues != null && (issues.size() > position - ISSUES_DIFFERENCE) && (position - ISSUES_DIFFERENCE >= 0)) {
                    ((IssuesRepoListRowHolder) holder).issueOwnerName.setText(issues.get(position - ISSUES_DIFFERENCE).getUser().getLogin());
                    ((IssuesRepoListRowHolder) holder).body.setText(issues.get(position - ISSUES_DIFFERENCE).getBody());
                }
            }
            break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if (position == 0 || position == 1 || position == 5) {
            //titles
            viewType = TITLE_TYPE;
        } else if (position > 1 && position <= 4) {
            //contributer list
            viewType = CONTRIBUTORS_TYPE;
        } else {
            //issues list
            viewType = ISSUES_TYPE;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        //title row is counted
        int sumOfListItems = 1;
        if (issues != null) {
            sumOfListItems += issues.size()+1;
        }
        if (contributors != null) {
            sumOfListItems += contributors.size()+1;
        }
        return sumOfListItems;
    }

    class IssuesRepoListRowHolder extends RecyclerView.ViewHolder {
        protected TextView issueOwnerName;
        protected TextView body;

        public IssuesRepoListRowHolder(View view) {
            super(view);
            this.issueOwnerName = (TextView) view.findViewById(R.id.repo_issue_owner_name_textview);
            this.body = (TextView) view.findViewById(R.id.repo_issue_body_textview);
        }

    }

    class ContributorsRepoListRowHolder extends RecyclerView.ViewHolder {
        protected ImageView thumbnail;
        protected TextView title;
        protected TextView contributionNumber;

        public ContributorsRepoListRowHolder(View view) {
            super(view);
            this.thumbnail = (ImageView) view.findViewById(R.id.repo_contributor_avatar_imageview);
            this.title = (TextView) view.findViewById(R.id.repo_contributor_name_textview);
            this.contributionNumber = (TextView) view.findViewById(R.id.rep_contributions_number_textview);

        }

    }

    class TitlesRepoListRowHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView body;

        public TitlesRepoListRowHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.rep_title_textview);
            this.body = (TextView) view.findViewById(R.id.rep_desc_textview);
        }

    }

}
