package edu.northeastern.numad23sp_tylerpoff;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LinkCollector extends AppCompatActivity {
    private static final String STATE_LINKS = "links";
    RecyclerView linkRecyclerView;
    List<Link> links;
    private AlertDialog.Builder dialogEntry;
    private EditText newName;
    private EditText newUrl;
    private Button linkAdd;
    private Button linkCancel;
    private AlertDialog dialogBox;

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
        addLink_FAB();
        linkRecyclerView = findViewById(R.id.link_collector_rv);
        linkRecyclerView.setHasFixedSize(true);
        linkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        linkRecyclerView.setAdapter(new LinkAdapter(links, this));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_LINKS, (Serializable) links);
    }

    public void addLink(String name, String url) {
        links.add(new Link(name, url));
    }

    public void addLink_FAB() {
        FloatingActionButton addFAB = findViewById(R.id.btnCollector);
        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogEntry();
            }
        });

    }

    public void createDialogEntry() {
        dialogEntry = new AlertDialog.Builder(this);
        final View linkEntry = getLayoutInflater().inflate(R.layout.link_dialog, null);
        newName = linkEntry.findViewById(R.id.LinkNameET);
        newUrl = linkEntry.findViewById(R.id.LinkURLET);
        linkAdd = linkEntry.findViewById(R.id.btnLinkAdd);
        linkCancel = linkEntry.findViewById(R.id.btnLinkCancel);
        dialogEntry.setView(linkEntry);
        dialogBox = dialogEntry.create();
        dialogBox.show();

        linkAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!newName.getText().toString().equals("") && !newUrl.getText().toString().equals("")) {
                    addLink(newName.getText().toString(), newUrl.getText().toString());
                    Snackbar.make(linkRecyclerView, "Link added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(linkRecyclerView, "Link could not be added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                dialogBox.dismiss();
            }
        });
        linkCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox.dismiss();
                Snackbar.make(linkRecyclerView, "Canceled", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        }
    }