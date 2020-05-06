package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class intViewAdapter extends PagerAdapter {
    Context mcontext;
    List<screenItem> mlistscreen;

    public intViewAdapter(Context mcontext, List<screenItem> mlistscreen) {
        this.mcontext = mcontext;
        this.mlistscreen = mlistscreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.slide_layout,null);
        ImageView img=layoutScreen.findViewById(R.id.imageView2);
        TextView title=layoutScreen.findViewById(R.id.intro);
        TextView descrip=layoutScreen.findViewById(R.id.desc);

        title.setText(mlistscreen.get(position).getTitle());
        descrip.setText(mlistscreen.get(position).getDesc());
        img.setImageResource(mlistscreen.get(position).getScreenImg());
        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mlistscreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
