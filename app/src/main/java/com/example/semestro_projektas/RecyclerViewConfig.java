package com.example.semestro_projektas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewConfig {
    private Context mContext;
    private CounterAdapter mCounterAdapter;
    public  void setConfig(RecyclerView recyclerView,Context context, List<Counter> counters,
                           List<String> keys){
        mContext=context;
        mCounterAdapter= new CounterAdapter(counters,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCounterAdapter);
    }
    class counterItemView extends RecyclerView.ViewHolder {
    private TextView mID;
    private TextView mTipas;
    private TextView mBusena;
    private TextView mSanaudos;

    private String key;

        public counterItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.counter_item,parent,false));

            mID = (TextView)itemView.findViewById(R.id.idCounter);
            mTipas= (TextView)itemView.findViewById(R.id.id2);
            mBusena = (TextView)itemView.findViewById(R.id.id3);
            mSanaudos = (TextView)itemView.findViewById(R.id.id4);
        }
        public void bind(Counter counter, String key){
            mTipas.setText(counter.getTipas());
            mBusena.setText(counter.getBusena());
            this.key = key;
        }


    }
    class CounterAdapter extends  RecyclerView.Adapter<counterItemView>{
        private List<Counter> mCounterList;
        private List<String> mKeys;

        public CounterAdapter(List<Counter> mCounterList, List<String> mKeys) {
            this.mCounterList = mCounterList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public counterItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new counterItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull counterItemView counterItemView, int i) {
            counterItemView.bind(mCounterList.get(i),mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mCounterList.size();
        }
    }

}
