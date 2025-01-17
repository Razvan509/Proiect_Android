package com.example.proiect_android;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proiect_android.beans.Category;
import com.example.proiect_android.beans.Transaction;
import com.example.proiect_android.dao.CategoryDao;
import com.example.proiect_android.dao.TransactionDao;
import com.example.proiect_android.dao.UserDao;
import com.example.proiect_android.beans.User;

import java.util.Date;


@Database(entities = {User.class, Category.class, Transaction.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ProjectRoomDatabase extends RoomDatabase {
    private static ProjectRoomDatabase projectRoomInstance;

    public abstract UserDao userDao();
    public abstract TransactionDao transactionDao();
    public abstract CategoryDao categoryDao();
    
    public static synchronized ProjectRoomDatabase getInstance(Context context){
        if (projectRoomInstance == null){
            projectRoomInstance = Room.databaseBuilder(context.getApplicationContext(), ProjectRoomDatabase.class, "project_data")
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
        private CategoryDao categoryDao;
        private TransactionDao transactionDao;

        private PopulateDbAsyncTask(ProjectRoomDatabase database){
            categoryDao = database.categoryDao();
            transactionDao = database.transactionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.insert(new Category(1,"income"));
            categoryDao.insert(new Category(2,"necessary"));
            categoryDao.insert(new Category(3,"unnecessary"));

            Transaction t = new Transaction();
            t.setUserId("UL81gKnZWIV0Tj7HS4JcdSp8cXz2");
            t.setTransactionDate(new Date(2020,4,29));
            t.setCategoryId(1);
            t.setAmount(20);
            transactionDao.insert(t);


            //income - 1
            //necessary - 2
            //unnecessary - 3
            return null;
        }
    }
}
