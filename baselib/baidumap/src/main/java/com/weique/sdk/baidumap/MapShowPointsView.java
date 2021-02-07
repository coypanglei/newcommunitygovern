package com.weique.sdk.baidumap;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.sdk.baidumap.R;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.SpatialRelationUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author GK
 * @description: 用于界面中显示一个或多个点位 线信息
 * @date :2020/11/26 11:18
 * 需要过去相关权限(gps 定位权限)  再使用当前 view
 */
public class MapShowPointsView extends FrameLayout implements View.OnClickListener {
    private Context context;
    /**
     * 题图view
     */
    private MapView mMapView;
    /**
     * 容器
     */
    private FrameLayout frameLayout;
    /**
     * 定位按钮 可滑动时  显示 用来控制 题图返回定位点
     */
    private ImageView localImage;
    /**
     * 定位点 / 点击点  的中文地址
     */
    private TextView address;
    /**
     * 显示层级  16 比例尺是 200比例尺/米
     */
    private float zoom = 16.0f;
    /**
     * 显示放大缩小按钮
     */
    private boolean showZoom;
    /**
     * 手势控制
     */
    private boolean gestureControl;
    /**
     * 地图
     */
    private BaiduMap map;

    /**
     * 复位点
     */
    private LatLng restorationPoint;

    /**
     * 定位点
     */
    private LatLng localingPoint;
    /**
     * 点击点
     */
    private LatLng clickPoint;
    /**
     * 点击点的百度地址
     */
    private String clickPointString;
    /**
     * 定位点 百度的中文地址信息
     */
    private String localingString;
    /**
     * 定位 的行政编码
     */
    private int adCode;

    /**
     * 构建Marker图标
     */
    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromAssetWithDpi("icon_gcoding.png");
    BitmapDescriptor bitmapLocal = BitmapDescriptorFactory.fromAssetWithDpi("people_icon.png");
    private Overlay pointLinePolyline;
    private Overlay polygonOverlay;
    private boolean showLocaing;
    private LocationClient mLocationClient;
    /**
     * 定位 间隔多久定位一次 默认0  只定位一次
     */
    private int scanSpan = 0;
    private boolean followPositioningDisplay;
    private GeoCoder mCoder;
    private MapShowPointsViewListening listening;
    /**
     * 是否可点击
     */
    private boolean clickable;
    /**
     * 点击到网格内
     */
    private boolean clickableInGriding;

    /**
     * 多边形信息
     */
    private List<List<LatLng>> mLatLngs;
    /**
     * 用户点击的点
     */
    private Overlay clickOverlay;
    /**
     * true 点击点的地理反编码   false  是定位点的地理反编码
     */
    private boolean isClickPointTheCoding = false;

    /**
     * 反编码  需要一定的时间  需要时  加这个监听  监听反编码完成
     */
    public interface MapShowPointsViewListening {

        /**
         * 地图加载成功
         */
        void onTheCodingListener();
    }

    public MapShowPointsView(@NonNull Context context) {
        this(context, null);
    }

    public MapShowPointsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapShowPointsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(attrs);
        initView();
    }

    public void setListening(MapShowPointsViewListening listening) {
        this.listening = listening;
    }

    /**
     * 设置复位点
     *
     * @param restorationPoint restorationPoint
     */
    public void setRestorationPoint(LatLng restorationPoint) {
        this.restorationPoint = restorationPoint;
    }

    /**
     * 获取定位点坐标信息
     *
     * @return LatLng
     */
    public LatLng getLocalingPoint() {
        return localingPoint;
    }


    /**
     * 获取定位点中文地址位置信息
     *
     * @return String
     */
    public String getLocalingString() {
        return localingString;
    }

    /**
     * 获取点击点的 坐标信息
     *
     * @return LatLng
     */
    public LatLng getClickPoint() {
        return clickPoint;
    }

    /**
     * 获取点击点的 中文地址信息
     *
     * @return String
     */
    public String getClickPointString() {
        return clickPointString;
    }

    /**
     * 初始化属性信息
     *
     * @param attrs attrs
     */
    private void initAttrs(AttributeSet attrs) {
        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.app_map_show_points);
            //显示是否可以放大缩小
            showZoom = ta.getBoolean(R.styleable.app_map_show_points_app_show_zoom, false);
            //手势控制
            gestureControl = ta.getBoolean(R.styleable.app_map_show_points_app_gesture_control, false);
            //显示用户定位 位置
            showLocaing = ta.getBoolean(R.styleable.app_map_show_points_app_show_localing, false);
            //比例尺大小
            zoom = ta.getFloat(R.styleable.app_map_show_points_app_map_zoom, 16.0f);
            //定位间隔
            scanSpan = ta.getInt(R.styleable.app_map_show_points_app_localing_scan_span, 0);
            //跟随定位显示
            followPositioningDisplay = ta.getBoolean(R.styleable.app_map_show_points_app_follow_positioning_display, true);
            //是否可点击
            clickable = ta.getBoolean(R.styleable.app_map_show_points_app_clickable, false);
            //点击到 网格内
            clickableInGriding = ta.getBoolean(R.styleable.app_map_show_points_app_clickable_in_griding, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化view
     */
    private void initView() {
        try {
            mLatLngs = new ArrayList<>();
            View view = LayoutInflater.from(context).inflate(R.layout.app_map_show_points_layout, this, false);
            frameLayout = view.findViewById(R.id.frame_layout);
            localImage = view.findViewById(R.id.local);
            address = view.findViewById(R.id.address);
            localImage.setOnClickListener(this);
            localImage.setVisibility(gestureControl ? View.VISIBLE : View.GONE);
            addView(view);
            initMapView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置显示比例
     * 详细 看 http://lbsyun.baidu.com/index.php?title=androidsdk/guide/create-map/showmap  -地图类型及显示层级
     *
     * @param zoom zoom
     */
    public void setZoom(float zoom) {
        try {
            this.zoom = zoom;
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.zoom(zoom);
            map.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化地图界面
     */
    private void initMapView() {
        try {
            mMapView = new MapView(context, getMapOptions());
            frameLayout.addView(mMapView);
            map = mMapView.getMap();
            map.setMyLocationEnabled(true);
            //设置默认缩放比
            setZoom(zoom);
            //显示定位信息
            if (showLocaing) {
                setLocalingInfo();
                mCoder = GeoCoder.newInstance();
                mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                        try {
                            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                                //没有找到检索结果
                                return;
                            } else {
                                //点击点的地理反编码
                                if (isClickPointTheCoding) {
                                    isClickPointTheCoding = false;
                                    //详细地址
                                    clickPointString = result.getAddress();
                                    if (listening != null) {
                                        listening.onTheCodingListener();
                                    }
                                } else {
                                    //详细地址
                                    localingString = result.getAddress();
                                    //行政区号
                                    adCode = result.getCityCode();
                                    if (listening != null) {
                                        listening.onTheCodingListener();
                                    }
                                }
                                address.setText(result.getAddress());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                if (clickable) {
                    map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            MapShowPointsView.this.onMapClick(latLng);
                        }

                        @Override
                        public void onMapPoiClick(MapPoi mapPoi) {
                            MapShowPointsView.this.onMapClick(mapPoi.getPosition());
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * map点击事件
     */
    private void onMapClick(LatLng latLng) {
        try {
            if (latLng == null) {
//                Timber.w("未发现点击地点坐标");
                return;
            }
            if (map == null) {
//                Timber.w("map 对象为空");
                return;
            }
            if (clickableInGriding) {
                if (mLatLngs != null && mLatLngs.size() > 0) {
                    if (pointInGrid(latLng, mLatLngs)) {
                        drawClickPoint(latLng);
                    } else {
//                        Timber.w("请点击网格内信息");
                    }
                } else {
//                    Timber.w("用户多边形信息 有误");
                }
            } else {
                drawClickPoint(latLng);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制用户点击点位
     *
     * @param latLng latLng
     */
    private void drawClickPoint(LatLng latLng) {
        try {
            if (clickOverlay != null) {
                clickOverlay.remove();
            }
            clickPoint = latLng;
            MarkerOptions ooA = new MarkerOptions().position(latLng)
                    .icon(BitmapDescriptorFactory.fromAssetWithDpi("click_local.png"))
                    .zIndex(9).draggable(false);
            clickOverlay = map.addOverlay(ooA);
            isClickPointTheCoding = true;
            setTheCoding(latLng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置反编码
     *
     * @param latLng latLng
     */
    public void setTheCoding(LatLng latLng) {
        try {
            if (mCoder != null && latLng != null) {
                ReverseGeoCodeOption reverseGeoCodeOption = new ReverseGeoCodeOption()
                        .location(latLng)
                        .newVersion(1)
                        .radius(1000)
                        .pageNum(0);
                // 发起反地理编码请求，该方法必须在监听之后执行，否则会在某些场景出现拿不到回调结果的情况
                mCoder.reverseGeoCode(reverseGeoCodeOption);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置用户定位信息
     */
    public void setLocalingInfo() {
        //定位初始化
        try {
            mLocationClient = new LocationClient(context);

            //通过LocationClientOption设置LocationClient相关参数
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType(CoordType.BD09LL.name()); // 设置坐标类型
            option.setScanSpan(scanSpan);
            //设置locationClientOption
            mLocationClient.setLocOption(option);
            //注册LocationListener监听器
            MyLocationListener myLocationListener = new MyLocationListener();
            mLocationClient.registerLocationListener(myLocationListener);
            //开启地图定位图层
            mLocationClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置 单点位信息
     *
     * @param latLng latLng
     */
    public void setPointAndShow(LatLng latLng) {
        try {
            if (map != null) {
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(latLng)
                        .icon(bitmap)
                        .flat(true)
                        .animateType(MarkerOptions.MarkerAnimateType.jump)
                        .draggable(false);
                //在地图上添加Marker，并显示
                map.addOverlay(option);
            }
            updateMapStatus(latLng);
            setRestorationPoint(latLng);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置 多点位信息
     *
     * @param latLng latLng
     */
    public void setPointsAndShow(List<LatLng> latLng) {
        try {
            if (map != null) {
                //构建MarkerOption，用于在地图上添加Marker
                for (LatLng lng : latLng) {
                    OverlayOptions option = new MarkerOptions()
                            .position(lng)
                            .icon(bitmap)
                            .flat(true)
                            .animateType(MarkerOptions.MarkerAnimateType.jump)
                            .draggable(false);
                    //在地图上添加Marker，并显示
                    map.addOverlay(option);
                }
                if (latLng.size() > 0) {
                    updateMapStatus(latLng.get(0));
                    setRestorationPoint(latLng.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 更新地图到某个点位
     *
     * @param latLng 点位
     */
    public void updateMapStatus(LatLng latLng) {
        try {
            if (latLng != null) {
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
                map.animateMapStatus(mapStatusUpdate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置 点线 信息
     *
     * @param latLng latLng
     */
    public void setPointLineAndShow(List<LatLng> latLng) {
        try {
            if (map != null) {
                if (pointLinePolyline != null) {
                    pointLinePolyline.remove();
                }
                //构建MarkerOption，用于在地图上添加Marker
                //添加纹理图片
                List<BitmapDescriptor> textureList = new ArrayList<>();
                //添加纹理索引
                List<Integer> indexList = new ArrayList<>();
                int i = 0;
                for (LatLng lng : latLng) {
                    OverlayOptions option = new MarkerOptions()
                            .position(lng)
                            .icon(bitmap)
                            .flat(true)
                            .animateType(MarkerOptions.MarkerAnimateType.jump)
                            .draggable(false);
                    //在地图上添加Marker，并显示
                    map.addOverlay(option);
                    textureList.add(BitmapDescriptorFactory.fromAssetWithDpi("Icon_road_green_arrow.png"));
                    indexList.add(i);
                    i++;
                }
                //设置折线的属性
                OverlayOptions mOverlayOptions = new PolylineOptions()
                        .width(15)
                        .color(0xAAFF0000)
                        .points(latLng)
                        .customTextureList(textureList)
                        .textureIndex(indexList);
                //在地图上绘制折线
                //pointLinePolyline 折线对象
                pointLinePolyline = map.addOverlay(mOverlayOptions);
                if (latLng.size() > 0) {
                    updateMapStatus(latLng.get(0));
                    setRestorationPoint(latLng.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制多边形信息
     * 构造PolygonOptions
     *
     * @param latLngs     点信息
     * @param fillColor   填充色
     * @param strokeColor 边框色
     */
    public void drawPolygon(List<LatLng> latLngs, int fillColor, int strokeColor) {
        try {
            mLatLngs.add(latLngs);
            if (map != null) {
                if (polygonOverlay != null) {
                    polygonOverlay.remove();
                }
                PolygonOptions mPolygonOptions = new PolygonOptions()
                        .points(latLngs)
                        .fillColor(fillColor)
                        .stroke(new Stroke(5, strokeColor));
                //在地图上显示多边形
                polygonOverlay = map.addOverlay(mPolygonOptions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制多个多边形信息
     * 构造PolygonOptions
     *
     * @param latLngs     点信息
     * @param fillColor   填充色
     * @param strokeColor 边框色
     */
    public void drawPolygons(List<List<LatLng>> latLngs, int fillColor, int strokeColor) {
        try {
            mLatLngs.addAll(latLngs);
            if (map != null) {
                for (List<LatLng> latLng : latLngs) {
                    PolygonOptions mPolygonOptions = new PolygonOptions()
                            .points(latLng)
                            .fillColor(fillColor)
                            .stroke(new Stroke(5, strokeColor));
                    //在地图上显示多边形
                    map.addOverlay(mPolygonOptions);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置题图相关参数
     *
     * @return
     */
    private BaiduMapOptions getMapOptions() {
        BaiduMapOptions options = new BaiduMapOptions();
        try {
            //显示指南针
            options.compassEnabled(true);
            //地图模式，默认为普通地图
            options.mapType(BaiduMap.MAP_TYPE_NORMAL);
            //是否允许地图旋转手势，默认允许
            options.rotateGesturesEnabled(gestureControl);
            //是否允许拖拽手势，默认允许
            options.scrollGesturesEnabled(gestureControl);
            //	是否允许俯视图手势，默认允许
            options.overlookingGesturesEnabled(gestureControl);
            //设置比例尺控件位置
            //options.scaleControlPosition();
            //是否显示比例尺控件，默认显示
            options.scaleControlEnabled(true);
            //	设置缩放控件位置
            //options.zoomControlsPosition(showZoom);
            //	是否允许缩放手势，默认允许
            options.zoomGesturesEnabled(showZoom);
            //是否显示缩放按钮控件，默认允许
            options.zoomControlsEnabled(showZoom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return options;
    }

    /**
     * 同步 activity
     */
    public void onResume() {
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    /**
     * 同步 activity
     */
    public void onPause() {
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    /**
     * 同步 activity
     */
    public void onDestroy() {
        try {
            if (mLocationClient != null) {
                mLocationClient.stop();
            }
            if (map != null) {
                map.setMyLocationEnabled(false);
            }
            if (mMapView != null) {
                mMapView.onDestroy();
                mMapView = null;
            }
            frameLayout.removeAllViews();
            frameLayout = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            //复位
            if (v.getId() == R.id.local) {
                if (restorationPoint != null) {
                    updateMapStatus(restorationPoint);
                } else if (localingPoint != null) {
                    updateMapStatus(localingPoint);
                }
                setZoom(zoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定位监听
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            try {
                if (location == null || mMapView == null) {
                    return;
                }
                localingPoint = new LatLng(location.getLatitude(), location.getLongitude());
                setTheCoding(localingPoint);
                if (followPositioningDisplay) {
                    updateMapStatus(localingPoint);
                }
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(location.getDirection()).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                map.setMyLocationData(locData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断点是否在网格内
     *
     * @param latLng latLng
     * @return boolean
     */
    private boolean pointInGrid(LatLng latLng, List<List<LatLng>> latLngLists) {
        boolean isPolygonContains = false;
        for (List<LatLng> latLngs : latLngLists) {
            if (SpatialRelationUtil.isPolygonContainsPoint(latLngs, latLng)) {
                isPolygonContains = true;
                break;
            }
        }
        return isPolygonContains;
    }
}
