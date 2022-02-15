package com.pas.pos_system.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.pas.pos_system.repository.Repository;

public class LoginViewModel extends AndroidViewModel {

    Repository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application);
    }

    public void login(Context context, String username, String password){ this.repository.login(context,username,password);}

}
