package com.sundy.bbl.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sundy.bbl.db.entity.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:09
 * 描述：
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM Users LIMIT 1")
    Flowable<User> getUser();

    /**
     * 插入User数据  如果User存在则替换
     * @param user
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

    /**
     * 删除所有user
     */
    @Query("DELETE FROM Users")
    void deleteAllUsers();
}
