package com.hewei.nestedscrollviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CoordinatorTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_test);
        ListHelper.initRecyclerView(findViewById(R.id.recycler));
    }
}
