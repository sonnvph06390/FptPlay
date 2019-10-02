package com.example.son.fptplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    PagerAdapter adapter;
    int[] img;
    private static int currentpage = 0;
    private static int numpager = 0;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("FPT Play");
        BottomNavigationView bottomnavigation = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        img = new int[]{R.drawable.banner, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4,
                R.drawable.banner2, R.drawable.banner};

        viewPager = findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(img, MainActivity.this);
        viewPager.setAdapter(adapter);

        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentpage = 1;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int pagecount=img.length;
                    if (currentpage==0){
                        viewPager.setCurrentItem(pagecount-1,false);
                    }else if (currentpage==pagecount-1){
                        viewPager.setCurrentItem(0, false);
                    }

                }
            }
        });

        final Handler handler=new Handler();
        final Runnable update=new Runnable() {
            @Override
            public void run() {
                if (currentpage==numpager){
                    currentpage=0;
                }
                viewPager.setCurrentItem(currentpage++,false);
            }
        };
        Timer swipe=new Timer();
        swipe.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },1000,1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(MainActivity.this, "Trang chu", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_film:
                    Toast.makeText(MainActivity.this, "Phim truyện", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_tv:
                    Toast.makeText(MainActivity.this, "Truyền hình", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_hbo:
                    Toast.makeText(MainActivity.this, "HBO go", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_more:
                    Toast.makeText(MainActivity.this, "More", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };

}
