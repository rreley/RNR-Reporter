package com.example.rnr_reporter.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.rnr_reporter.Entities.injuryForm;

import java.util.List;

@Dao
public interface injuryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertInjury(injuryForm injuryForm);

    @Query("SELECT * FROM injuryForm")
    List<injuryForm> getAll();
}
