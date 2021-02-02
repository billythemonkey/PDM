package pt.ipbeja.pdm.helptoshop.db.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import pt.ipbeja.pdm.helptoshop.db.entities.Post;

@Dao
public abstract class PostDao implements BaseDao<Post>{

    @Query("select * from posts")
    public abstract List<Post> getAll();

    @Query("select * from posts where id = :id")
    public abstract Post getPost(long id);

}
