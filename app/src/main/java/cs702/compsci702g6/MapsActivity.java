package cs702.compsci702g6;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;


public class MapsActivity extends AppCompatActivity {

    Context mContext = this;
    private ViewPager pager;
    private final static int[] mSportsImages = {R.mipmap.walking, R.mipmap.running, R.mipmap.swimming, R.mipmap.jumping, R.mipmap.yoga};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UltraViewPager ultraViewPager = (UltraViewPager)findViewById(R.id.ultra_viewpager);
        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        ultraViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
//initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter();
        ultraViewPager.setAdapter(adapter);
        ultraViewPager.setMultiScreen(0.5f);
        ultraViewPager.setItemRatio(1.0f);

//set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
        ((SeekBar) findViewById(R.id.seekBar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView)findViewById(R.id.textCalories)).setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ultraViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((TextView)findViewById(R.id.textAction)).setText(getResources().getStringArray(R.array.action)[position%5]);
                ((TextView)findViewById(R.id.textUnit)).setText(getResources().getStringArray(R.array.unit)[position%5]);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private class UltraPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sport_viewer, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_size);
            imageView.setImageDrawable(getResources().getDrawable(mSportsImages[position]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            TextView textView = (TextView) view.findViewById(R.id.text_size);
            String[] names = getResources().getStringArray(R.array.sports);
            textView.setText(names[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mSportsImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
}
