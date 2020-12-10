package com.uso.pruebas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {
    ViewPager viewpager;
    TabLayout tabs;
    ImageView imagenInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        initToolbar();

        viewpager = findViewById(R.id.viewpagerPerfil);
        tabs = (TabLayout) findViewById(R.id.tabsPerfil);
        imagenInter = findViewById(R.id.imagenInter);

        new DownloadImageFromInternet(imagenInter).execute("https://images.unsplash.com/photo-1593642703055-4b72c180d9b5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80");


        tabs.setupWithViewPager(viewpager);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(new ContactosFragment(), "Contactos");
        adapter.addFragment(new ProductosFragment(), "Productos");
        adapter.addFragment(new OfertasFragment(), "Ofertas");
        viewpager.setAdapter(adapter);

        setTabLayoutAnimation();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //shows back-arrow icon
    }

    private void setTabLayoutAnimation(){
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

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments = new ArrayList<>();
        List<String> fragmentTitles = new ArrayList<>();


        public SectionsPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(@NonNull Fragment f, String title){
            fragments.add(f);
            fragmentTitles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage=BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}