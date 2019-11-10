package com.basant.yesicbap.financialmath.persistance;


import android.content.Context;

import com.basant.yesicbap.financialmath.models.Note;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase  extends RoomDatabase {

    public static final String DATABASE_NAME = "notes_db";
    private static NoteDatabase instances;

    static NoteDatabase getInstance(final Context context){
        if (instances == null){
            instances = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NoteDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instances;
    }



    public abstract NoteDao getNoteDao();
}
