package hackathon.unscript.com.ams;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ashu on 22/6/17.
 */

public class StudentCategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public StudentCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Attendance();
        } else{
            return new Notice();
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return mContext.getString(R.string.attendance);
        else
            return mContext.getString(R.string.notice);

    }

}
