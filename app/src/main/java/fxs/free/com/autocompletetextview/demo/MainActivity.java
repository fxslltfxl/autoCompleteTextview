package fxs.free.com.autocompletetextview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fxs.free.com.autocompletetextview.R;

public class MainActivity extends AppCompatActivity {
    List<UserVM> userVMs = new ArrayList<>();
    public AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = findViewById(R.id.auto_complete_tv);
        autoCompleteTextView.setOnClickListener(v-> Toast.makeText(this,"dianji",Toast.LENGTH_LONG).show());
        userVMs.add(new UserVM("111", "111", "111"));
        userVMs.add(new UserVM("222", "222", "222"));
        userVMs.add(new UserVM("333", "333", "333"));
        userVMs.add(new UserVM("444", "444", "444"));
        userVMs.add(new UserVM("555", "555", "555"));
        Adapter adapter = new Adapter(userVMs, this);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
    }
}
