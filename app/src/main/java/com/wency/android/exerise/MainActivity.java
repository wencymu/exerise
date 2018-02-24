package com.wency.android.exerise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wency.android.exerise.view.Main3Activity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String TAG = "MainActivity";
    private View stubedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });

        Button inflate_1 = (Button) findViewById(R.id.button2);
        Button inflate_2 = (Button) findViewById(R.id.button3);
        final ViewStub viewStub = (ViewStub) findViewById(R.id.inflate_view);
        inflate_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stubedView = viewStub.inflate();
            }
        });
        inflate_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    viewStub.inflate();
                } catch (Exception e) {
                    viewStub.setVisibility(viewStub.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);
                }
            }
        });



        Button webview= (Button) findViewById(R.id.webview);
        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,WebViewActivity.class));
            }
        });


        Button ref= (Button) findViewById(R.id.reference);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ReferenceActivity.class));
            }
        });

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private View noView = null;
    private View errorView = null;

    /**
     * 显示无数据
     *
     * @param currentView 当前view
     *                    显示默认无数据布局
     */
    public void showNoDataView(View currentView) {
        if (errorView != null && errorView.getVisibility() == View.VISIBLE) {
            errorView.setVisibility(View.INVISIBLE);
        }
        if (noView != null) {
            noView.setVisibility(View.VISIBLE);
            return;
        }

        if (errorView != null) {
            if (currentView.getParent() instanceof FrameLayout) {
                FrameLayout mLayout = (FrameLayout) currentView.getParent();
                noView = LayoutInflater.from(this).inflate(R.layout.no_data_view, mLayout, false);
                mLayout.addView(noView);
            }
        } else {
            ViewGroup parent = (ViewGroup) currentView.getParent();
            int index = parent.indexOfChild(currentView);
            parent.removeView(currentView);

            FrameLayout mContentLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParamsContent = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            parent.addView(mContentLayout, index, layoutParamsContent);

            mContentLayout.addView(currentView);

            noView = LayoutInflater.from(this).inflate(R.layout.no_data_view, mContentLayout, false);
            mContentLayout.addView(noView);
        }


    }

    public void showErrorView(View currentView) {
        if (noView != null && noView.getVisibility() == View.VISIBLE) {
            noView.setVisibility(View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(View.VISIBLE);
            return;
        }

        if (noView != null) {
            if (currentView.getParent() instanceof FrameLayout) {
                FrameLayout mLayout = (FrameLayout) currentView.getParent();
                errorView = LayoutInflater.from(this).inflate(R.layout.error_layout, mLayout, false);
                mLayout.addView(errorView);
            }
        } else {
            ViewGroup parent = (ViewGroup) currentView.getParent();
            int index = parent.indexOfChild(currentView);
            parent.removeView(currentView);

            FrameLayout mContentLayout = new FrameLayout(this);
            FrameLayout.LayoutParams layoutParamsContent = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            parent.addView(mContentLayout, index, layoutParamsContent);

            mContentLayout.addView(currentView);

            errorView = LayoutInflater.from(this).inflate(R.layout.error_layout, mContentLayout, false);
            mContentLayout.addView(errorView);
        }
    }

    public void hideNoDataView() {
        if (noView != null) {
            noView.setVisibility(View.INVISIBLE);
        }
        if (errorView != null) {
            errorView.setVisibility(View.INVISIBLE);
        }
    }
}
