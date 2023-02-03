package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LinkCollector extends AppCompatActivity {
    private static final String STATE_LINKS = "links";
    RecyclerView linkRecyclerView;
    List<Link> links;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            links = (List<Link>) savedInstanceState.getSerializable(STATE_LINKS);
        }
        else {
            links = new ArrayList<>();
        }
        setContentView(R.layout.activity_link_collector);
        FloatingActionButton addLink = findViewById(R.id.btnCollector);

        links.add(new Link("test1", "test2"));

        linkRecyclerView = findViewById(R.id.link_collector_rv);
        linkRecyclerView.setHasFixedSize(true);
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkRecyclerView.setAdapter(new LinkAdapter(links, this));
    }
}