package com.travel.svago.SvagoPackage.TripPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SvagoDetailsActivity extends AppCompatActivity {

    @BindView(R.id.btnProcess)
    Button btnProcess;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.photo_Img)
    CircleImageView photoImg;
    @BindView(R.id.cart)
    CardView cart;
    @BindView(R.id.date1)
    TextView date1;
    @BindView(R.id.duration)
    TextView duration;
    @BindView(R.id.date2)
    TextView date2;
    @BindView(R.id.rel)
    RelativeLayout rel;
    @BindView(R.id.txtModel)
    TextView txtModel;
    @BindView(R.id.txtDesc)
    TextView txtDesc;
    @BindView(R.id.imageRec)
    RecyclerView imageRec;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public SkeletonScreen skeletonScreen;

    private SvagoDetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svago_details);
        ButterKnife.bind(this);
        skeletonScreen = Skeleton.bind((ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0))
                .load(R.layout.wait_car_details)
                .color(R.color.ColorGray)       // the shimmer color.
                //.angle(30)// the shimmer angle.
                .duration(1000)
                .shimmer(true)
                .show();
        detailsPresenter = new SvagoDetailsPresenter(this);
        detailsPresenter.getIntentData(getIntent().getExtras());
        detailsPresenter.setRecycler();
        detailsPresenter.getData();
    }

    @OnClick({R.id.btnProcess , R.id.back})
    void Clicked(View view) {
        switch (view.getId()) {
            case R.id.btnProcess:
                if (Constant.isLogin)
                    detailsPresenter.onProcessClick();
                else
                    SharedClass.setDialog(this , Constant.SvagoTag);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
    }
}
