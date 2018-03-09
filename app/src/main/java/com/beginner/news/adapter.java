package com.beginner.news;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sahai613 on 08-03-2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.MyviewHolder> {
public ArrayList<Article> list;
public adapter(ArrayList<Article>list){
    this.list=list;
}
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {
        holder.title.setText(list.get(position).Title);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String url=list.get(position).Url;
                openbrower(url);
            }
            public void openbrower(String link){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                holder.title.getContext().startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public MyviewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.textview);

        }
    }
}
