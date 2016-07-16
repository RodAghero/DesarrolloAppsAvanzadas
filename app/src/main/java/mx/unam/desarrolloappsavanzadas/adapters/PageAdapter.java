package mx.unam.desarrolloappsavanzadas.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Roy on 19/06/2016.
 */
public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private FragmentManager fm;
    private HashMap<Integer, String> mapFragmentTags;

    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
        mapFragmentTags = new HashMap<Integer, String>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object object = super.instantiateItem(container, position);

        if (object instanceof Fragment ) {
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mapFragmentTags.put(position, tag);
        }
        return object;

    }

    public Fragment getFragment (int position) {
        String tag = mapFragmentTags.get(position);
        if (tag == null)
            return null;
        return fm.findFragmentByTag(tag);

    }

}