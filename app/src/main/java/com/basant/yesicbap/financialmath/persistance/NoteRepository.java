package com.basant.yesicbap.financialmath.persistance;

import android.content.Context;

import com.basant.yesicbap.financialmath.async.DeleteAsyncTask;
import com.basant.yesicbap.financialmath.async.InsertAsyncTask;
import com.basant.yesicbap.financialmath.async.UpdateAsyncTask;
import com.basant.yesicbap.financialmath.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note){
    new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }
    public  void updateNote(Note note){
    new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }


    public LiveData<List<Note>>  retrieveNotesTask(){

        return  mNoteDatabase.getNoteDao().getNotes();
    }

    public void deleteNote(Note note){

        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);

    }




}
