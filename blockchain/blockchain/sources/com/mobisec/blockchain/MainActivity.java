package com.mobisec.blockchain;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    TextView mResultWidget = null;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText keyWidget = (EditText) findViewById(R.id.key);
        final EditText flagWidget = (EditText) findViewById(R.id.flag);
        Button checkButton = (Button) findViewById(R.id.check);
        final TextView resultWidget = (TextView) findViewById(R.id.result);
        this.mResultWidget = resultWidget;
        keyWidget.addTextChangedListener(new TextWatcher() { // from class: com.mobisec.blockchain.MainActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.mResultWidget.setText(BuildConfig.FLAVOR);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        flagWidget.addTextChangedListener(new TextWatcher() { // from class: com.mobisec.blockchain.MainActivity.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.mResultWidget.setText(BuildConfig.FLAVOR);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
            }
        });
        checkButton.setOnClickListener(new View.OnClickListener() { // from class: com.mobisec.blockchain.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String key = keyWidget.getText().toString();
                String flag = flagWidget.getText().toString();
                resultWidget.setText("Checking PIN....");
                resultWidget.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                boolean result = false;
                try {
                    result = FlagChecker.checkFlag(key, flag);
                } catch (Exception e) {
                    Log.e("MOBISEC", "Exception while checking flags:" + Log.getStackTraceString(e));
                }
                if (result) {
                    resultWidget.setText("Flag is valid!");
                    resultWidget.setTextColor(-16737536);
                    return;
                }
                resultWidget.setText("Flag is not valid");
                resultWidget.setTextColor(SupportMenu.CATEGORY_MASK);
            }
        });
    }
}
