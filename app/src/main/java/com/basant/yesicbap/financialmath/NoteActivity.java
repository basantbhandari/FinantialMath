package com.basant.yesicbap.financialmath;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basant.yesicbap.financialmath.models.Note;
import com.basant.yesicbap.financialmath.persistance.NoteRepository;

public class NoteActivity extends AppCompatActivity {

      private EditText mTitle;
      private EditText mDescription;
      private EditText mCost;
      private EditText mStatus;
      private EditText mDate;
      private Button mOk, mDelete;

      private Note note;
      private NoteRepository mNoteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //refrencing view
        referancing();
        settingDataToView();


        //when save button is pressed
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                note.setTitle(mTitle.getText().toString());
                note.setDescription(mDescription.getText().toString());
                note.setCost(Float.parseFloat(mCost.getText().toString()));
                if (mStatus.getText().toString().compareTo("True") == 0){
                    note.setStatus(true);
                }else {
                    note.setStatus(false);
                }
                 note.setTimestamp(mDate.getText().toString());


                mNoteRepository.updateNote(note);
                finish();

            }
        });


        //want to delete the note
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mNoteRepository.deleteNote(note);
             finish();
            }
        });


    }//end create



    private void settingDataToView() {

        if(getIntent().hasExtra("selected_note")){
            note = getIntent().getParcelableExtra("selected_note");
            mTitle.setText(note.getTitle());
            mDescription.setText(note.getDescription());
            mCost.setText(String.valueOf(note.getCost()));
            if(note.isStatus()){
                mStatus.setText("True");
            }else {
                mStatus.setText("False");
            }
            mDate.setText(note.getTimestamp());
        }  //end if


    }// end settingDataToView


    private void referancing() {
        mTitle = findViewById(R.id.note_title);
        mDescription = findViewById(R.id.note_description);
        mCost = findViewById(R.id.note_cost);
        mStatus = findViewById(R.id.note_status);
        mDate = findViewById(R.id.note_date);
        mOk = findViewById(R.id.note_ok);
        mDelete = findViewById(R.id.note_delete);
        mNoteRepository = new NoteRepository(this);

    }



}
