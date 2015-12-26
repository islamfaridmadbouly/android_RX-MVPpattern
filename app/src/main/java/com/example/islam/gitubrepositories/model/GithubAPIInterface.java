package com.example.islam.gitubrepositories.model;

import com.example.islam.gitubrepositories.model.pojos.AllRepos;
import com.example.islam.gitubrepositories.model.pojos.Contributor;
import com.example.islam.gitubrepositories.model.pojos.Issue;

import java.util.LinkedHashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

interface GithubAPIInterface {


    @GET("/repositories")
    Observable<AllRepos> rxRequestAllReposForSpecificLang(@QueryMap() LinkedHashMap params);
    @GET("/{user_login}/{name}/contributors")
    Observable<List<Contributor>> contributors(@Path("user_login") String userLogin,@Path("name")String name);
    @GET("/{user_login}/{name}/issues")
    Observable<List<Issue>> issues(@Path("user_login") String userLogin,@Path("name")String name,@Query("q=sort") String sort);
}
