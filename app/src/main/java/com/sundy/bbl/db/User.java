package com.sundy.bbl.db;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.sundy.bbl.BR;

import java.util.UUID;

@Entity(tableName = "users")
public class User  extends BaseObservable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private String mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    @Ignore
    public User(String userName) {
        mId = UUID.randomUUID().toString();
        mUserName = userName;
    }

    public User(@NonNull String id, String userName) {
        this.mId = id;
        this.mUserName = userName;
    }

    public String getId() {
        return mId;
    }

    @Bindable
    public String getUserName() {
        return mUserName;
    }

    public void setId(@NonNull String id) {
        mId = id;
    }

    public void setUserName(String userName) {
        mUserName = userName;
        notifyChange();
    }
}
