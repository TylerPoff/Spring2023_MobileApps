package edu.northeastern.numad23sp_tylerpoff;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTV;
    public TextView urlTV;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTV = itemView.findViewById(R.id.name);
        this.urlTV = itemView.findViewById(R.id.url);
    }

    public void bindThisData(Link link) {
        nameTV.setText(link.getName());
        urlTV.setText(link.getUrl());
    }
}
