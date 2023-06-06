package com.example.questionbank25_32;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.questionbank25_32.activity.DataAnalysisActivity;
import com.example.questionbank25_32.activity.FeedbackActivity;
import com.example.questionbank25_32.activity.LifeAssistantActivity;
import com.example.questionbank25_32.activity.MetroQueryActivity;
import com.example.questionbank25_32.activity.QueryBusActivity;
import com.example.questionbank25_32.activity.QueryRoadActivity;
import com.example.questionbank25_32.activity.RealTimeActivity;
import com.example.questionbank25_32.activity.VehicleViolationActivity;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        titleTv.setText("主菜单");
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.lkcx:
                        startActivity(new Intent(MainActivity.this, QueryRoadActivity.class));
                        break;
                    case R.id.sjfx:
                        startActivity(new Intent(MainActivity.this, DataAnalysisActivity.class));
                        break;
                    case R.id.shzs:
                        startActivity(new Intent(MainActivity.this, LifeAssistantActivity.class));
                        break;
                    case R.id.gjcx:
                        startActivity(new Intent(MainActivity.this, QueryBusActivity.class));
                        break;
                    case R.id.sshj:
                        startActivity(new Intent(MainActivity.this, RealTimeActivity.class));
                        break;
                    case R.id.clwz:
                        startActivity(new Intent(MainActivity.this, VehicleViolationActivity.class));
                        break;
                    case R.id.yjfk:
                        startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                        break;
                    case R.id.dtcx:
                        startActivity(new Intent(MainActivity.this, MetroQueryActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}