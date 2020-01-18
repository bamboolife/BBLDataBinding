package com.sundy.bbl.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:19
 * 描述：
 */
@Database(entities = {User.class},version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    private static volatile UsersDatabase INSTANCE;

    public abstract UserDao mUserDao();

    public static UsersDatabase getInstance(Context context){
        if (INSTANCE==null){
            synchronized (UsersDatabase.class){
                if (INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UsersDatabase.class, "Sample.db")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
