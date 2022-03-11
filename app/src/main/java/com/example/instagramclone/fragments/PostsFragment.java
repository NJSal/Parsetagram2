package com.example.instagramclone.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.instagramclone.Post;
import com.example.instagramclone.PostsAdapter;
import com.example.instagramclone.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    public static final String TAG = "PostsFragment";

    protected PostsAdapter adapter;

    private RecyclerView rvPosts;

    protected List<Post> listofPosts;

    SwipeRefreshLayout swipeContainer;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeContainer = view.findViewById(R.id.SwiperContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG,"REFRESHING NOW");
                queryPosts();
            }
        });

        rvPosts = view.findViewById(R.id.rvPosts);

        listofPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), listofPosts);

        // Steps to recycler view
        // 0. create layout for one row of list
        // 1. create adapter
        // 2. create data source
        // 3. set adapter on recycler view
        rvPosts.setAdapter(adapter);
        // 4. set layout manager on recycler view
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts",e);
                    return;
                }

                for(Post post : posts) {
                    Log.i(TAG, "POST: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }

                adapter.clear();

                listofPosts.addAll(posts);

                adapter.notifyDataSetChanged();

                swipeContainer.setRefreshing(false);
            }
        });
    }
}