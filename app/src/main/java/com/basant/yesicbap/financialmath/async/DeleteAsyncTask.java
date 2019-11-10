package com.basant.yesicbap.financialmath.async;

import android.os.AsyncTask;

import com.basant.yesicbap.financialmath.models.Note;
import com.basant.yesicbap.financialmath.persistance.NoteDao;

public class DeleteAsyncTask extends AsyncTask< Note , Void, Void  > {

    private NoteDao mNoteDao;
    public DeleteAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        mNoteDao.delete(notes);
        return null;
    }
}
