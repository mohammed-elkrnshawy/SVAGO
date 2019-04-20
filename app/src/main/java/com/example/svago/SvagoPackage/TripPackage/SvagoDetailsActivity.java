package com.example.svago.SvagoPackage.TripPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.svago.R;

import butterknife.BindView;
import butterknife.OnClick;

public class SvagoDetailsActivity extends AppCompatActivity {

    @BindView(R.id.btnProcess)
    Button btnProcess;

    private SvagoDetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svago_details);

        detailsPresenter=new SvagoDetailsPresenter(this);
        detailsPresenter.getIntentData(getIntent().getExtras());
        detailsPresenter.getData();
    }

    @OnClick({R.id.btnProcess})void Clicked(View view){
        switch (view.getId()){
            case R.id.btnProcess:
                detailsPresenter.onProcessClick();
                break;
        }
    }

}
