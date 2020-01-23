package com.example.testapplication.Repoistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapplication.Model.TitleModel;
import com.example.testapplication.Retrofit.APIInterface;
import com.example.testapplication.Retrofit.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitleRepoistory {
    private APIInterface apiInterface;
    private MutableLiveData<List<TitleModel>> mutableLiveData;
    int LastPageIndex =0;
     public TitleRepoistory(){
    apiInterface = RetrofitClient.getRetrofit().create(APIInterface.class);
}
    public LiveData<List<TitleModel>> getTitleLiveData() {
         if(mutableLiveData==null){
             mutableLiveData = new MutableLiveData<>();
         }
        return mutableLiveData;
    }

    public void getTitlewithpageNo(int pageNo) {
         if(pageNo<=LastPageIndex){
             apiInterface.getTitleList("story",pageNo).enqueue(new Callback<JsonElement>() {
                 @Override
                 public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                     if (response.isSuccessful()){
                         List<TitleModel> titleModelList = new ArrayList<>();
                         if (response.body() != null) {
                             LastPageIndex = response.body().getAsJsonObject().get("nbPages").getAsInt();
                             JsonArray jsonArray = response.body().getAsJsonObject().get("hits").getAsJsonArray();
                             for (int i=0;i<jsonArray.size();i++){
                                 String Title_name="";String Created_date="";
                                 if(!jsonArray.get(i).getAsJsonObject().get("title").isJsonNull()){
                                     Title_name = jsonArray.get(i).getAsJsonObject().get("title").getAsString();
                                 }else{
                                     Title_name = null;
                                 }
                                 if(!jsonArray.get(i).getAsJsonObject().get("created_at").isJsonNull()){
                                     Created_date = jsonArray.get(i).getAsJsonObject().get("created_at").getAsString();
                                 }else{
                                     Created_date = null;
                                 }
                                 final TitleModel titleModel = new TitleModel();
                                 titleModel.setTitle(Title_name);
                                 titleModel.setDate(Created_date);
                                 titleModel.setSwitchChecked(false);
                                 titleModelList.add(titleModel);
                             }
                             mutableLiveData.postValue(titleModelList);

                         }
                     }

                 }

                 @Override
                 public void onFailure(Call<JsonElement> call, Throwable t) {

                 }
             });
         }
    }
}
