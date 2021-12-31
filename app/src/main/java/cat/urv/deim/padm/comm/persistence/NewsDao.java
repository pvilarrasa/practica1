package cat.urv.deim.padm.comm.persistence;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(News news);
}
