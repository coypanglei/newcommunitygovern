package com.weique.commonres.dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.weique.commonres.adapter.ImageAdapter;
import com.weique.commonres.entity.ImageDataBean;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnPageChangeListener;
import com.youth.banner.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.armscomponent.commonres.R;
import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AlphaConfig;
import razerdp.util.animation.AnimationHelper;
import timber.log.Timber;

/**
 * 查看图片 Dialog
 *
 * @author Administrator
 */
public class LookPicDialog extends BasePopupWindow implements OnPageChangeListener {

    ImageAdapter adapter;
    TextView textView;
    Banner banner;

    public LookPicDialog(Context context) {
        super(context);

        try {
            adapter = new ImageAdapter();
            banner = findViewById(R.id.banner);
            banner.setAdapter(adapter)
                    .setOnBannerListener((data, position) -> {
                        Snackbar.make(banner, position + "", Snackbar.LENGTH_SHORT).show();
                        LogUtils.d("position：" + position);
                    });
            banner.addOnPageChangeListener(this);
            textView = findViewById(R.id.tv_title);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 加载数据 和位置
     *
     * @param data
     * @param position
     */
    public void setListData(List<String> data, int position) {
        try {
            List<ImageDataBean> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                list.add(new ImageDataBean(data.get(i)));
            }
            adapter.setDatas(list);
            banner.setCurrentItem(position);
            if (data.size() > 1) {
                textView.setText(position + "/" + data.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 仅有一张
     *
     * @param data
     */
    public void setListData(String data) {
        try {
            List<ImageDataBean> list = new ArrayList<>();
            list.add(new ImageDataBean(data));
            adapter.setDatas(list);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.public_look_pic);
    }


    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withAlpha(AlphaConfig.IN)
                .toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withAlpha(AlphaConfig.OUT)
                .toDismiss();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        try {
            textView.setText(position + 1 + "/" + ImageDataBean.getTestData3().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
