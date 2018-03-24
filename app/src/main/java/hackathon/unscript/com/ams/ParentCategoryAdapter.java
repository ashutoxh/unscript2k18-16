package hackathon.unscript.com.ams;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ParentCategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public ParentCategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Attendance();
        } else if(position == 1){
            return new Notice();
        }
        else
            return new Chat();
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return mContext.getString(R.string.attendance);
        else if (position == 1)
            return mContext.getString(R.string.notice);
        else
            return mContext.getString(R.string.chat);

    }

}

