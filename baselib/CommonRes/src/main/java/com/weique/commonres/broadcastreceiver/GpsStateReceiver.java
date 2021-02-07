package com.weique.commonres.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author GK
 * @description:
 * @date :2020/7/31 10:00
 */
public class GpsStateReceiver extends BroadcastReceiver {

    private Activity mActivity;
//    private CommonDialogFragment commonDialogFragment;

    @Override
    public void onReceive(Context context, Intent intent) {
        /*try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (!gps) {
                if (commonDialogFragment != null) {
                    return;
                }
                mActivity = AppManager.getAppManager().getTopActivity();
                commonDialogFragment = CommonDialogFragment.newInstance();
                commonDialogFragment.setOnComfirmClick("去设置", v -> {
                    Intent intent1 = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    mActivity.startActivity(intent1);
                });
                commonDialogFragment.setCancleHide();
                commonDialogFragment.setOutCancel(false);
                commonDialogFragment.setTitle("提示");
                commonDialogFragment.setGravity(Gravity.CENTER);
                commonDialogFragment.setContent("GPS未开启，会导致您的定位不准确");

                if (mActivity instanceof FragmentActivity) {
                    FragmentActivity fragmentActivity = (FragmentActivity) mActivity;
                    commonDialogFragment.setAnimStyle(R.style.BottomAnimation)
                            .show(fragmentActivity.getSupportFragmentManager());
                }else{

                }
            } else {
                if (commonDialogFragment != null) {
                    Objects.requireNonNull(commonDialogFragment.getDialog()).dismiss();
                    commonDialogFragment = null;
                    mActivity = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


}
