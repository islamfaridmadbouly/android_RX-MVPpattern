package com.example.islam.gitubrepositories.model;

import com.example.islam.gitubrepositories.model.pojos.AllRepos;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;

import java.util.LinkedHashMap;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

public class GithubService {
    public static final int PER_PAGE = 1;
//    public static String ENDPOINT = "https://api.github.com";

    private GithubAPIInterface mGithubAPI;

    public GithubService(GithubAPIInterface githubAPI) {
        mGithubAPI = githubAPI;
    }

    public static GithubService getInstance(String endpoint) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return new GithubService(restAdapter.create(GithubAPIInterface.class));
    }

    @GET("/")
    public Observable<AllRepos> rxRequestAllReposForSpecificLang(@QueryMap() LinkedHashMap params) {
        return mGithubAPI.rxRequestAllReposForSpecificLang(params);
    }

    @GET("/{user_login}/{name}/contributors")
    public Observable<List<Contributor>> contributors(@Path("user_login") String userLogin, @Path("name") String name) {
        return mGithubAPI.contributors(userLogin, name);
    }

    @GET("/{user_login}/{name}/issues")
    public Observable<List<Issue>> issues(@Path("user_login") String userLogin, @Path("name") String name, @Query("q=sort") String sort) {

        return mGithubAPI.issues(userLogin, name, sort);
    }

}
