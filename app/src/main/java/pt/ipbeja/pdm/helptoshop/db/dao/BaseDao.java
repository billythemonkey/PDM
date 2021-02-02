package pt.ipbeja.pdm.helptoshop.db.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import pt.ipbeja.pdm.helptoshop.db.entities.Post;

public interface BaseDao<T> {

    @Insert
    long insert (Post post);

    @Update
    int update (Post post);

    @Delete
    int delete (Post post);
}
