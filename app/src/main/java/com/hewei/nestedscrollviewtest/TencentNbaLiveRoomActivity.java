package com.hewei.nestedscrollviewtest;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TencentNbaLiveRoomActivity extends AppCompatActivity {

    private static final String TAG = "NBALive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent_nba_live_room);

        AppBarLayout abl = (AppBarLayout) findViewById(R.id.titleBar);
        final TextView textView = (TextView) findViewById(R.id.title);
        final View playBkg = findViewById(R.id.playBkg);
        textView.setVisibility(View.VISIBLE);

        abl.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "verticalOffset = " + verticalOffset);

                float alpha = 0.0f;
                if (verticalOffset > -100) {
                    alpha = 1.0f + verticalOffset / 100.0f;
                } else if (verticalOffset < -400) {
                    alpha = -4.0f - verticalOffset / 100.0f;
                    playBkg.setAlpha(1.0f - alpha);
                }

                textView.setAlpha(alpha);

                /*
                if (verticalOffset >= 100) {
                    CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) playBkg.getLayoutParams();
                    lp.setCollapseMode();
                }*/
            }
        });

        ListHelper.initRecyclerView(findViewById(R.id.recycler));
    }
}
