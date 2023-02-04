package edu.northeastern.numad23sp_tylerpoff;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.file.InvalidPathException;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    public TextView nameTV;
    public TextView urlTV;

    public LinkViewHolder(@NonNull View itemView) {
        super(itemView);
        this.nameTV = itemView.findViewById(R.id.name);
        this.urlTV = itemView.findViewById(R.id.url);
        urlTV.setOnClickListener(view -> {
            try{
                String Url = urlTV.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(Url);
                itemView.getContext().startActivity(intent);
            }catch(Exception e){
                Toast.makeText(itemView.getContext(), "Invalid url", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void bindThisData(Link link) {
        nameTV.setText(link.getName());
        urlTV.setText(link.getUrl());
    }
}
