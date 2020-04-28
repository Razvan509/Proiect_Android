package com.example.proiect_android.repos;

import android.app.Application;
import android.os.AsyncTask;

import com.example.proiect_android.ProjectRoomDatabase;
import com.example.proiect_android.beans.Category;
import com.example.proiect_android.dao.CategoryDao;

import java.util.List;

public class CategoryRepository {
    private CategoryDao categoryDao;

    public CategoryRepository(Application app){
        ProjectRoomDatabase database = ProjectRoomDatabase.getInstance(app);
        categoryDao = database.categoryDao();
    }

    public void insert(Category category){
        new InsertUserAsyncTask(categoryDao).execute(category);
    }

    private static class InsertUserAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao categoryDao;

        private InsertUserAsyncTask(CategoryDao categoryDao){
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

}
