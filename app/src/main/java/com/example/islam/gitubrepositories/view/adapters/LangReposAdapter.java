package com.example.islam.gitubrepositories.view.adapters;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islam.gitubrepositories.R;
import com.example.islam.gitubrepositories.model.pojos.RepoItem;
import com.example.islam.gitubrepositories.view.fragments.MainFragment_;
import com.example.islam.gitubrepositories.view.fragments.RepoDetailsFragment;
import com.example.islam.gitubrepositories.view.fragments.RepoDetailsFragment_;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LangReposAdapter extends RecyclerView.Adapter<LangReposAdapter.RepoListRowHolder> {


    private List<RepoItem> langReposItemList = new ArrayList<>();

    private Context mContext;

    public LangReposAdapter(Context context) {
        this.mContext = context;
    }

    public List<RepoItem> getLangReposItemList() {
        return langReposItemList;
    }

    public void setLangReposItemList(List<RepoItem> langReposItemList) {
        this.langReposItemList = langReposItemList;
    }

    @Override
    public RepoListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.repolist_row,parent, false);
        RepoListRowHolder mh = new RepoListRowHolder(v);

        return mh;    }

    @Override
    public void onBindViewHolder(RepoListRowHolder holder, int position) {
        RepoItem repoRepoItem = langReposItemList.get(position);

        Picasso.with(mContext).load(repoRepoItem.getOwner().getAvatarUrl())
                .error(R.mipmap.default_avatar)
                .placeholder(R.mipmap.default_avatar)
                .into(holder.thumbnail);

        holder.title.setText(repoRepoItem.getName());
    }

    @Override
    public int getItemCount() {
        return (null != langReposItemList ? langReposItemList.size() : 0);
    }

    class RepoListRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected ImageView thumbnail;
        protected TextView title;

        public RepoListRowHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.thumbnail = (ImageView) view.findViewById(R.id.repo_creator_avatar_imageview);
            this.title = (TextView) view.findViewById(R.id.rep_title_textview);
        }

       @Override
       public void onClick(View v) {
           RepoDetailsFragment repoDetailsFragment = new RepoDetailsFragment_().builder().mRepoItem(langReposItemList.get(getLayoutPosition())).build();
           ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().hide(((FragmentActivity)mContext).getSupportFragmentManager().findFragmentByTag(MainFragment_.class.getName())).
                   add(R.id.contaner_relativelayout, repoDetailsFragment, RepoListRowHolder.class.getName()).addToBackStack(RepoListRowHolder.class.getName()).commit();
       }
   }
}
