package com.pas.pos_system.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.pas.pos_system.repository.Repository;

public class CreateLoginViewModel extends AndroidViewModel {

    Repository repository;

    public CreateLoginViewModel(@NonNull Application application) {
        super(application);

        this.repository = new Repository(application);
    }

    public void createLogin(Context context, String username, String password, String name){
        this.repository.createLogin(context, username, password, name);
    }

}
