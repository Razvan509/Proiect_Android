package com.example.proiect_android;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proiect_android.beans.Category;
import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.dao.TransactionDao;
import com.example.proiect_android.dao.UserDao;
import com.example.proiect_android.beans.User;

@Database(entities = {User.class, Transaction.class, Category.class}, version = 1)
public abstract class ProjectRoomDatabase extends RoomDatabase {
    private static ProjectRoomDatabase projectRoomInstance;

    public abstract UserDao userDao();
    public abstract TransactionDao transactionDao();
    
    public static synchronized ProjectRoomDatabase getInstance(Context context){
        if (projectRoomInstance == null){
            projectRoomInstance = Room.databaseBuilder(context.getApplicationContext(), ProjectRoomDatabase.class, "project_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return projectRoomInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(projectRoomInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private UserDao userDao;

        private PopulateDbAsyncTask(ProjectRoomDatabase database){
            userDao = database.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User user1 = new User();
            user1.setUserName("Gigel1");
            userDao.insert(user1);

            User user2 = new User();
            user2.setUserName("Gigel2");
            userDao.insert(user2);

            User user3 = new User();
            user3.setUserName("Gigel3");
            userDao.insert(user3);
            return null;
        }
    }
}
