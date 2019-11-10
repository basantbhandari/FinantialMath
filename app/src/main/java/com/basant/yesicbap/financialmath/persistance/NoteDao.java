package com.basant.yesicbap.financialmath.persistance;

import com.basant.yesicbap.financialmath.models.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {

    @Insert
    long[] insertNotes(Note... notes);


    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getNotes();

    @Query("SELECT * FROM notes WHERE title LIKE :title")
    List<Note> getNoteWithCustomQuery( String title);

    @Delete
    int delete(Note... notes);



    @Update
    int update(Note... notes);


}
