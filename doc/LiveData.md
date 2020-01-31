## 定义

简单地说，LiveData是一个数据持有类。它具有以下特点：

- 数据可以被观察者订阅；

- 能够感知组件（Fragment、Activity、Service）的生命周期；

- 只有在组件出于激活状态（STARTED、RESUMED）才会通知观察者有数据更新；

>PS: 文中提到的“组件”皆指实现了LifecycleOwner接口Fragment、Activity。

## 为什么需要LiveData

从LiveData具有的特点，我们就能联想到它能够解决我们遇到的什么问题。LiveData具有以下优点：

- 能够保证数据和UI统一

这个和LiveData采用了观察者模式有关，LiveData是被观察者，当数据有变化时会通知观察者（UI）。

- 减少内存泄漏

这是因为LiveData能够感知到组件的生命周期，当组件处于DESTROYED状态时，观察者对象会被清除掉。

- 当Activity停止时不会引起崩溃

 这是因为组件处于非激活状态时，不会收到LiveData中数据变化的通知。

- 不需要额外的手动处理来响应生命周期的变化

 这一点同样是因为LiveData能够感知组件的生命周期，所以就完全不需要在代码中告诉LiveData组件的生命周期状态。

- 组件和数据相关的内容能实时更新

 组件在前台的时候能够实时收到数据改变的通知，这是可以理解的。当组件从后台到前台来时，LiveData能够将最新的数据通知组件，这两点就保证了组件中和数据相关的内容能够实时更新。

- 针对configuration change时，不需要额外的处理来保存数据

  我们知道，当你把数据存储在组件中时，当configuration change（比如语言、屏幕方向变化）时，组件会被recreate，然而系统并不能保证你的数据能够被恢复的。当我们采用LiveData保存数据时，因为数据和组件分离了。当组件被recreate，数据还是存在LiveData中，并不会被销毁。

- 资源共享

 通过继承LiveData类，然后将该类定义成单例模式，在该类封装监听一些系统属性变化，然后通知LiveData的观察者，这个在继承LiveData中会看到具体的例子。

## LiveData使用

在了解LiveData定义和优点后，那它到底怎么应用呢？LiveData有几种使用方式：

- 使用LiveData对象
- 继承LiveData类

## 使用LiveData对象

使用LiveData对象主要有以下几个步骤：

- 创建保存特定数据类型的LiveData实例；
- 创建Observer对象，作为参数传入LiveData.observe()方法添加观察者；
- 更新Livedata对象存储的数据；

## 创建LiveData实例

Android文档中建议LiveData配合ViewModel使用更加哦，其实呢，你也可以不使用ViewModel，但是一定要做到LiveData中保存的数据和组件分离，至于原因，前面我们已经提到过了。下面是在ViewModel中创建LiveData实例的例子：

```java

public class NameViewModel extends ViewModel{
    // Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;
    // Create a LiveData with a String list
    private MutableLiveData<List<String>> mNameListData;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }

    public MutableLiveData<List<String>> getNameList(){
        if (mNameListData == null) {
            mNameListData = new MutableLiveData<>();
        }
        return mNameListData;
    }
}
```

在NameViewModel中创建了两个MutableLiveData(MutableLiveData是LiveData的子类)实例，分别存储当前姓名、姓名列表；两个实例通过NameViewModel中的getter方法得到。

## 创建Observer对象，添加观察者
```java
public class LiveDataFragment extends Fragment {
    private static final String TAG = "LiveDataFragment";
    private NameViewModel mNameViewModel;
    TextView mTextView;

    public static LiveDataFragment getInstance(){
        return new LiveDataFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.bbl_livedata_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView=view.findViewById(R.id.textView);
        ViewModelProvider.AndroidViewModelFactory factory=new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication());
        mNameViewModel= new ViewModelProvider(this,factory).get(NameViewModel.class);
        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextView.setText(s);
            }
        });
        mNameViewModel.getNameListData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                for (String item : strings) {
                    Log.d(TAG, "name: " + item);
                }
            }
        });
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameViewModel.getCurrentName().setValue("Jane");
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> nameList = new ArrayList<>();
                for (int i = 0; i < 10; i++){
                    nameList.add("Jane<" + i + ">");
                }
                mNameViewModel.getNameListData().setValue(nameList);
            }
        });
    }


}


```
## 继承LiveData类

除了直接使用LiveDatad对象外，我们还可以通过集成LiveData类来定义适合特定需求的LiveData。下面继承LiveData类的例子，验证下LiveData的其中一个优点——资源共享。
```java
public class MyLiveData extends LiveData<Integer> {
    private static final String TAG = "MyLiveData";
    private static MyLiveData sData;
    private WeakReference<Context> mContextWeakReference;

    public static MyLiveData getInstance(Context context){
        if (sData == null){
            sData = new MyLiveData(context);
        }
        return sData;
    }

    private MyLiveData(Context context){
        mContextWeakReference = new WeakReference<>(context);
    }

    @Override
    protected void onActive() {
        super.onActive();
        registerReceiver();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        unregisterReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        mContextWeakReference.get().registerReceiver(mReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        mContextWeakReference.get().unregisterReceiver(mReceiver);
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "action = " + action);
            if (WifiManager.RSSI_CHANGED_ACTION.equals(action)) {
                int wifiRssi = intent.getIntExtra(WifiManager.EXTRA_NEW_RSSI, -200);
                int wifiLevel = WifiManager.calculateSignalLevel(
                        wifiRssi, 4);
                sData.setValue(wifiLevel);
            }
        }
    };
}
```
MyLiveData是个继承了LiveData的单例类，在onActive()和onInactive()方法中分别注册和反注册Wifi信号强度的广播。然后在广播接收器中更新MyLiveData对象。在使用的时候就可以通过MyLiveData.getInstance()方法，然后通过调用observe()方法来添加观察者对象，订阅Wifi信息强度变化。

- onActive()，此方法是当处于激活状态的observer个数从0到1时，该方法会被调用。
- onInactive() ，此方法是当处于激活状态的observer个数从1变为0时，该方法会被调用。

