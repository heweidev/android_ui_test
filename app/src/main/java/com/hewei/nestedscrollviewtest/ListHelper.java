package com.hewei.nestedscrollviewtest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fengyinpeng on 2018/3/29.
 */

public class ListHelper {
    private static class SimpleTextItemHolder extends RecyclerView.ViewHolder {
        public SimpleTextItemHolder(View itemView) {
            super(itemView);
        }

        public void setText(CharSequence str) {
            ((TextView) itemView).setText(str);
        }
    }

    public static void initRecyclerView(View view) {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());

        RecyclerView recyclerView = ((RecyclerView) view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new SimpleTextItemHolder(textView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                SimpleTextItemHolder holder1 = (SimpleTextItemHolder) holder;
                holder1.setText("item" + position);
            }

            @Override
            public int getItemCount() {
                return 16;
            }
        });
    }
}
