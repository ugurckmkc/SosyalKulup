package com.meuf.sosyalkulup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.meuf.sosyalkulup.Fragments.ChatListFragment;
import com.meuf.sosyalkulup.Fragments.KulupDuyuruFragment;
import com.meuf.sosyalkulup.Fragments.KulupListFragment;

/**
 * Created by UgurCkmkc on 17/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    int mNoOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs){
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                KulupListFragment tab1 = new KulupListFragment();
                return tab1;
            case 1:
                KulupDuyuruFragment tab2 = new KulupDuyuruFragment();
                return tab2;
            case 2:
                ChatListFragment tab3 = new ChatListFragment();
                return tab3;
            default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
