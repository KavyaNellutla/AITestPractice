package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.testapplication.Adapter.TitleRecyclerAdapter;
import com.example.testapplication.Model.TitleModel;
import com.example.testapplication.ViewModel.TitleViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView selected_count;
    private ProgressBar progressBar;
    private TitleViewModel titleViewModel;
    private int LastPageIterated = 0;
    private TitleRecyclerAdapter titleRecyclerAdapter;
    private List<TitleModel>titleModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleViewModel = ViewModelProviders.of(this).get(TitleViewModel.class);
        titleViewModel.getTitleWithPageNo(LastPageIterated);
        titleModels = new ArrayList<>();
        initViews();
        initAdapter();
        initRecyclerview();
        onScrollRecycler();
        observerableLiveData();
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                titleModels.clear();
                titleViewModel.getTitleWithPageNo(LastPageIterated);
                selected_count.setText(0);
            }
        });
    }

    private void onScrollRecycler() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    progressBar.setVisibility(View.VISIBLE);
                    titleViewModel.getTitleWithPageNo(LastPageIterated++);
                }
            }
        });
    }

    private void observerableLiveData() {
        titleViewModel.getTitleLiveData().observe(this, new Observer<List<TitleModel>>() {
            @Override
            public void onChanged(List<TitleModel> titleModels1) {
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                titleModels.addAll(titleModels1);
                titleRecyclerAdapter.notifyDataSetChanged();
            }
        });

    }

    private void initAdapter() {
        titleRecyclerAdapter = new TitleRecyclerAdapter(titleModels, new ItemClickListener() {
            @Override
            public void onItemClick(int postion, int switchCheckedCount) {
                selected_count.setText(String.valueOf(switchCheckedCount));
            }
        });
        titleRecyclerAdapter.setHasStableIds(true);
    }

    private void initRecyclerview() {
        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(titleRecyclerAdapter);
    }

    private void initViews() {
        toolbar=findViewById(R.id.tool_bar);
        recyclerView=findViewById(R.id.recycler_view);
        swipeRefreshLayout=findViewById(R.id.Swipe_refresh_layout);
        selected_count=findViewById(R.id.selected_count);
        progressBar=findViewById(R.id.data_progress);
    }
}
