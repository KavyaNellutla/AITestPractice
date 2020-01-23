package com.example.testapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapplication.Model.TitleModel;
import com.example.testapplication.Repoistory.TitleRepoistory;

import java.util.List;

public class TitleViewModel extends AndroidViewModel {
    private TitleRepoistory titleRepoistory;
    public TitleViewModel(@NonNull Application application) {
        super(application);
        titleRepoistory = new TitleRepoistory();
    }
    public LiveData<List<TitleModel>>getTitleLiveData(){
        return titleRepoistory.getTitleLiveData();
    }
    public void getTitleWithPageNo(int pageNo){
        titleRepoistory.getTitlewithpageNo(pageNo);
    }
}
