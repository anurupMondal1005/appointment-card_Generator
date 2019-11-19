package com.example.uemapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    private Context context;
    private List<LogS> personUtils;

    public LogAdapter(Context context, List accUtils) {
        this.context = context;
        this.personUtils = accUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_logview, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));
        LogS pu = personUtils.get(position);
        holder.message.setText(pu.message);
    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView message;

        public ViewHolder(View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.textLog);

        }
    }

}