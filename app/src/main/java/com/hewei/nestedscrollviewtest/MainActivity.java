package com.hewei.nestedscrollviewtest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_activity, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView =
                (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Configure the search info and add any event listeners...

        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("image/*");
        myShareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File("/sdcard/test.html")));

        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        provider.setShareIntent(myShareIntent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (id == R.id.btn1) {

        } else if (id == R.id.btn2) {
            Intent intent = new Intent(this, MyListActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn3) {
            Intent intent = new Intent(this, ScrollInViewPagerActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn4) {
            Intent intent = new Intent(this, BasicNestTestActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn5) {
            Intent intent = new Intent(this, TencentNbaLiveRoomActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn6) {
            Intent intent = new Intent(this, NestedScrollViewTestActivity.class);
            startActivity(intent);
        } else if (id == R.id.btn7) {
            Intent intent = new Intent(this, LayoutTestAcitivity.class);
            startActivity(intent);
        } else if (id == R.id.btn8) {
            Intent intent = new Intent(this, CoordinatorTestActivity.class);
            startActivity(intent);
        }
    }
}
