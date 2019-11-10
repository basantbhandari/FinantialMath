package com.basant.yesicbap.financialmath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.basant.yesicbap.financialmath.adapters.NotesRecyclerAdapter;
import com.basant.yesicbap.financialmath.models.Note;
import com.basant.yesicbap.financialmath.persistance.NoteRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    //Ui component
    private RecyclerView  mRecyclerView;
    int i;
    private TextView mTotal;
    private Float total = 0.0f;

    //vars
    private ArrayList<Note> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;
    private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         toolbar = findViewById(R.id.toolbar_main);
         floatingActionButton = findViewById(R.id.floating_action_button);
         mRecyclerView = findViewById(R.id.recyclerView);
         mTotal = findViewById(R.id.main_total);

         mNoteRepository = new NoteRepository(this);

        initRecyclerView();
        retrieveNotes();



        //for toolbar
        setSupportActionBar(toolbar);



        //on click on floating action button
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForm =  new Intent(MainActivity.this, InputFormActivity.class);
                startActivity(intentForm);
            }
        });



    }

    //method that retrieve data from database
    private void retrieveNotes(){
        mNoteRepository.retrieveNotesTask().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                if (mNotes.size() > 0){
                    mNotes.clear();
                }
                if ( notes != null){
                   //total = total + notes.iterator().next().getCost();
                    mNotes.addAll(notes);
                }
             // mTotal.setText(" Total = "+ total.toString());
                mNotesRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }



    //recycler view method
    private void initRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.privacy_policy:

                return true;
            case R.id.share:

                return true;
            case R.id.help:

                return true;
             default:
                return super.onOptionsItemSelected(item);
        }

    }

//method called when item of recycler view is clicked
    @Override
    public void onNoteClick(int position) {
        Intent intentToNote = new Intent(MainActivity.this, NoteActivity.class);
        intentToNote.putExtra("selected_note", mNotes.get(position));
        startActivity(intentToNote);
    }
}
