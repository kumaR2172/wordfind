package com.example.wordapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    Api api;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView=findViewById(R.id.s1);
        rv=findViewById(R.id.re1);
        rv.setLayoutManager(new LinearLayoutManager(this));

        api = RetrofitClientInstance.getRetrofitInstance().create(Api.class);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if(s.equalsIgnoreCase("")){

                }else{
                    Log.i("TAG", "onQueryTextChange: "+s);
                    searchWord(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if(s.equalsIgnoreCase("")){

                }else{
                    Log.i("TAG", "onQueryTextChange: "+s);
                    searchWord(s);
                }

                return false;
            }
        });
    }
    private void searchWord(String word){

        Call<List<SearchResponse>> call= api.getWord(word);

        call.enqueue(new Callback<List<SearchResponse>>() {
            @Override
            public void onResponse(Call<List<SearchResponse>> call, Response<List<SearchResponse>> response) {
                Log.i("TAG", "searchWord: "+call.request().toString());

                if (response.code()==200){
                    Collections.sort(response.body(), new Comparator<SearchResponse>() {
                        @Override
                        public int compare(SearchResponse word1, SearchResponse word2) {
                            return word1.getWord().compareToIgnoreCase(word2.getWord());
                        }
                    });
                    RecyclerView.Adapter adapter = new WordAdapter(response.body(), MainActivity.this);
                    rv.setAdapter(adapter);


                }else{
                    Log.i("TAG", "onResponse: "+response.code());
                }

            }

            @Override
            public void onFailure(Call<List<SearchResponse>> call, Throwable throwable) {

                Log.i("TAG", "onFailure: "+ throwable.getMessage());
            }
        });

    }
}
