package com.example.wordapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<com.example.wordapp.WordAdapter.WordViewHolder> {
    public List<SearchResponse> list;
    Context context;

    public WordAdapter(List<SearchResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.word_layout,parent,false);

        return new WordViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {

        holder.wordShip.setText(list.get(position).getWord());
        holder.score.setText(String.valueOf(list.get(position).getScore()));

        if(list.get(position).getTags() !=null){
            for(String s : list.get(position).getTags()){
                holder.tagS.setText(holder.tagS.getText().toString()+","+s);
            }
        }

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView wordShip,score,tagS;
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            wordShip=itemView.findViewById(R.id.wordShip);
            score=itemView.findViewById(R.id.score);
            tagS=itemView.findViewById(R.id.tagS);
        }
    }

}
