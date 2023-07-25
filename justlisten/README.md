Broadcasts and intends
Ref: https://developer.android.com/guide/components/broadcasts#java
added the below snippet to AndroidManifext.xml

```
        <receiver android:name=".MyBroadcastReceiver" android:exported="false">
            <intent-filter>
                <action android:name="com.mobisec.intent.action.FLAG_ANNOUNCEMENT" />
            </intent-filter>
        </receiver>
```
Created new MyBroadcastReceiver.java
```
public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MOBISEC", intent.getStringExtra("flag"));
    }
}
```

Modified MainActivity.java
Ref: https://google-developer-training.github.io/android-developer-fundamentals-course-concepts-v2/unit-3-working-in-the-background/lesson-7-background-tasks/7-3-c-broadcasts/7-3-c-broadcasts.html
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.mobisec.intent.action.FLAG_ANNOUNCEMENT");
        this.registerReceiver(br,filter);
    }

}
```
