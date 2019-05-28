package com.travel.svago.SharedPackage.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;

import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.cardCar)
    CardView cardCar;
    @BindView(R.id.cardDeal)
    CardView cardDeal;
    @BindView(R.id.cardFlight)
    CardView cardFlight;
    @BindView(R.id.cardHotel)
    CardView cardHotel;
    @BindView(R.id.cardSvago)
    CardView cardSvago;

    private static userData userObject;
    @BindView(R.id.Lin)
    LinearLayout Lin;
    @BindView(R.id.Rel)
    RelativeLayout Rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        getIntentData();
    }

    private void initView() {
        Point point = getNavigationBarSize(this);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) Rel.getLayoutParams();
        params.setMargins(0, 0, 0,  point.y);

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (!bundle.isEmpty()) {
            userObject = (userData) bundle.get(Constant.userFlag);
            if (userObject.getToken() == null) {
                Constant.isLogin = false;
            } else {
                Constant.isLogin = true;
            }
        }
    }

    @OnClick({R.id.cardHotel, R.id.cardSvago, R.id.cardFlight, R.id.cardDeal, R.id.cardCar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardSvago:
                Intent Svago = new Intent(HomeActivity.this, MainHomeActivity.class);
                Svago.putExtra(Constant.TypeTag, Constant.SvagoTag);
                Svago.putExtra("svago", false);
                Svago.putExtra(Constant.userFlag, userObject);
                startActivity(Svago);
                break;
            case R.id.cardHotel:
                Intent Hotel = new Intent(HomeActivity.this, MainHomeActivity.class);
                Hotel.putExtra(Constant.TypeTag, Constant.HotelTag);
                Hotel.putExtra(Constant.userFlag, userObject);
                startActivity(Hotel);
                //Toast.makeText(this, getString(R.string.not_available_now), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardCar:
                Intent Car = new Intent(HomeActivity.this, MainHomeActivity.class);
                Car.putExtra(Constant.TypeTag, Constant.CarTag);
                Car.putExtra(Constant.userFlag, userObject);
                startActivity(Car);
                //Toast.makeText(this, getString(R.string.not_available_now), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardFlight:
                Intent Flight = new Intent(HomeActivity.this, MainHomeActivity.class);
                Flight.putExtra(Constant.TypeTag, Constant.FlightTag);
                Flight.putExtra(Constant.userFlag, userObject);
                startActivity(Flight);
                //Toast.makeText(this, getString(R.string.not_available_now), Toast.LENGTH_SHORT).show();
                break;
            case R.id.cardDeal:
                Intent Offers = new Intent(HomeActivity.this, MainHomeActivity.class);
                Offers.putExtra(Constant.TypeTag, Constant.OfferTag);
                Offers.putExtra(Constant.userFlag, userObject);
                Offers.putExtra("offer", false);
                startActivity(Offers);
                //Toast.makeText(this, getString(R.string.not_available_now), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static void setData(userData userData) {
        userObject = userData;
    }

    private int getBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    public static Point getNavigationBarSize(Context context) {
        Point appUsableSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);

        // navigation bar on the side
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    public static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static Point getRealScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
        }

        return size;
    }


}
