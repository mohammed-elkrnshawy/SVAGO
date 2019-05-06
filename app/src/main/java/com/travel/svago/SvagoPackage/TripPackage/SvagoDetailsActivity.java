package com.travel.svago.SvagoPackage.TripPackage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travel.svago.R;

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

    private SvagoDetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svago_details);
        ButterKnife.bind(this);
        detailsPresenter = new SvagoDetailsPresenter(this);
        detailsPresenter.getIntentData(getIntent().getExtras());
        detailsPresenter.setRecycler();
        detailsPresenter.getData();
    }

    @OnClick({R.id.btnProcess , R.id.back})
    void Clicked(View view) {
        switch (view.getId()) {
            case R.id.btnProcess:
                detailsPresenter.onProcessClick();
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
