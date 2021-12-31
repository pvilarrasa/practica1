package cat.urv.deim.padm.comm.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class, News.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract NewsDao newsDao();
}
