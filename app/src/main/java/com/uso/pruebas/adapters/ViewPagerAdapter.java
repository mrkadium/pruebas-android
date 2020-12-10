package com.uso.pruebas.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.uso.pruebas.ContactosFragment;
import com.uso.pruebas.OfertasFragment;
import com.uso.pruebas.ProductosFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ContactosFragment();
            case 1: return new ProductosFragment();
            case 2: return new OfertasFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0: title = "Contactos"; break;
            case 1: title = "Productos"; break;
            case 2: title = "Ofertas"; break;
        }
        return title;
    }
}
