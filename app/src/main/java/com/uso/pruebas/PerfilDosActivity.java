package com.uso.pruebas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.uso.pruebas.adapters.ViewPagerAdapter;

public class PerfilDosActivity extends AppCompatActivity {
    TabLayout tablayoutPerfilDos;
    ViewPager viewpagerPerfilDos;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_dos);

        initToolbar();

        tablayoutPerfilDos = findViewById(R.id.tabLayoutPerfilDos);
        viewpagerPerfilDos = findViewById(R.id.viewPagerPerfilDos);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpagerPerfilDos.setAdapter(adapter);

        tablayoutPerfilDos.setupWithViewPager(viewpagerPerfilDos);

        setTablayoutAnimation();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbarPerfilDos);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setTablayoutAnimation(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorPrimary));
                int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.black_trans));
                collapsingToolbarLayout.setContentScrimColor(myColor);
                collapsingToolbarLayout.setStatusBarScrimColor(myDarkColor);
            }
        });
    }
}