package com.example.proiect_android.models;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proiect_android.ProjectRoomDatabase;
import com.example.proiect_android.beans.User;
import com.example.proiect_android.dao.UserDao;
import com.example.proiect_android.repos.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> users;

    public UserViewModel(Application application) {
        super(application);
        repository = new UserRepository(application);
        users = repository.getAllUsers();
    }

    public void insert(User user){
        repository.insert(user);
    }

    public void update(User user){
        repository.update(user);
    }

    public void delete(User user){
        repository.delete(user);
    }

    public void deleteAllUsers(){
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getUsers(){
        return users;
    }
}
