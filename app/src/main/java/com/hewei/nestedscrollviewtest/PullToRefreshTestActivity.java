package com.hewei.nestedscrollviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class PullToRefreshTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_pull_to_refresh_test);
        setContentView(R.layout.pull_to_fresh_no_nestedscroll);


        /*
        setContentView(R.layout.third_pull_to_fresh);
        final PtrFrameLayout ptrFrame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);

        PtrClassicDefaultHeader mPtrClassicHeader = new PtrClassicDefaultHeader(this);
        ptrFrame.setHeaderView(mPtrClassicHeader);
        ptrFrame.addPtrUIHandler(mPtrClassicHeader);

        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        */

        //ListHelper.initRecyclerView(findViewById(R.id.recycler));
    }
}
