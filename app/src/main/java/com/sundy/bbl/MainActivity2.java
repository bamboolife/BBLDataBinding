package com.sundy.bbl;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.sundy.bbl.databinding.ActivityMainBinding;
import com.sundy.bbl.db.Injection;
import com.sundy.bbl.db.entity.User;
import com.sundy.bbl.fgt.LiveDataFragment;
import com.sundy.bbl.mvvm.ViewModelFactory;
import com.sundy.bbl.mvvm.viewmodel.UserViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.sundy.bbl.R.layout.activity_main;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = MainActivity2.class.getSimpleName();


    private ViewModelFactory mViewModelFactory;

    private UserViewModel mViewModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, activity_main);

        mViewModelFactory = Injection.provideViewModelFactory(this);
        mViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);
        binding.updateUser.setOnClickListener(v -> updateUserName());
        User user=new User("sundy");
        binding.setUser(user);

        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container, LiveDataFragment.getInstance()).commitNow();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Subscribe to the emissions of the user name from the view model.
        // Update the user name text view, at every onNext emission.
        // In case of error, log the exception.
        mDisposable.add(mViewModel.getUserName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userName -> binding.userName.setText(userName),
                        throwable -> Log.e(TAG, "Unable to get username", throwable)));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // clear all the subscriptions
        mDisposable.clear();
    }

    private void updateUserName() {
        String userName = binding.userNameInput.getText().toString();
        // Disable the update button until the user name update has been done
        binding.updateUser.setEnabled(false);
        // Subscribe to updating the user name.
        // Re-enable the button once the user name has been updated
        mDisposable.add(mViewModel.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> binding.updateUser.setEnabled(true),
                        throwable -> Log.e(TAG, "Unable to update username", throwable)));
    }
}
