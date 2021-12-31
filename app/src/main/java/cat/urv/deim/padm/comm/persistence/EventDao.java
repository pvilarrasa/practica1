package cat.urv.deim.padm.comm.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM events")
    List<Event> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Event> events);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Event event);
}
