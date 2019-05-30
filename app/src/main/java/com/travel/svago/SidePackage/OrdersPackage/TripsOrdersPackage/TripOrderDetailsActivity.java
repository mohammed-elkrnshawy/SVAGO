package com.travel.svago.SidePackage.OrdersPackage.TripsOrdersPackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.travel.svago.Models.AffiliateResponse.AffiliateResponse;
import com.travel.svago.Models.ResponseCarsOrder.Car;
import com.travel.svago.Models.ResponseTripOrders.Trip;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Models.SimpleResponse.SimpleResponse;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Activity.OpenPictureActivity;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.SharedClass;
import com.travel.svago.SidePackage.OrdersPackage.CarsOrderPackage.CarOrderDetailsActivity;
import com.travel.svago.SvagoPackage.TripPackage.ImagesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripOrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.share)
    Button share;
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
    @BindView(R.id.btnCancel)
    Button btnCancel;

    com.travel.svago.Models.SharedResponses.userData userData ;
    UserService_POST userService ;
    private Trip data ;
    private int pos ;
    private LinearLayoutManager layoutManager ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_order_details);
        ButterKnife.bind(this);
        initView();
        setRecycler();
        getData();
    }

    private void initView(){
        userService = ApiUtlis.getUserServices_Post() ;
    }

    private void getData(){
        Bundle bundle = getIntent().getExtras() ;
        if (bundle != null){
            userData = (com.travel.svago.Models.SharedResponses.userData) bundle.get(Constant.userFlag) ;
            data = (Trip) bundle.get("trip") ;
            pos = bundle.getInt("position") ;
            setData();
        }
    }



    private void setData() {
        toolbarTitle .setText(data.getStatus());
        txtTitle.setText(data.getTrip().getTitle());
        ImageLoader.getInstance().displayImage(data.getTrip().getImage() , photoImg);
        date1.setText(data.getTrip().getFrom());
        date2.setText(data.getTrip().getTo());
        duration.setText(String.format("%d %s", data.getTrip().getDuration(), getString(R.string.day)));
        txtDesc.setText(data.getTrip().getDescription());
        ImagesAdapter mImagesAdapter = new ImagesAdapter(data.getTrip().getImages() , this);
        imageRec.setAdapter(mImagesAdapter);
        mImagesAdapter.notifyDataSetChanged();
        photoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TripOrderDetailsActivity.this , OpenPictureActivity.class);
                intent.putExtra("imageURL" ,data.getTrip().getImage()) ;
                startActivity(intent);
            }
        });
        if (data.getStatus().equals("cancelled"))
            btnCancel.setVisibility(View.GONE);
        else
            btnCancel.setVisibility(View.VISIBLE);

    }

    public void setRecycler() {
        layoutManager = new LinearLayoutManager(this ,  LinearLayoutManager.HORIZONTAL , true ) ;
        imageRec.setLayoutManager(layoutManager);
        imageRec.setHasFixedSize(true);
    }

    @OnClick({R.id.back, R.id.share, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                callLink(data.getTrip().getId());
                break;
            case R.id.btnCancel:
                cancelOrder(data.getId());
                break;
        }
    }

    private void cancelOrder(String id){
        SharedClass.ShowWaiting(this);
        Call<SimpleResponse> call = userService.cancelTripOrder("Bearer "+userData.getToken() , id) ;
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        Toast.makeText(TripOrderDetailsActivity.this, getResources().getString(R.string.order_canelled), Toast.LENGTH_SHORT).show();
                        //finish();
                        toolbarTitle.setText(getResources().getString(R.string.cancelled));
                        btnCancel.setVisibility(View.GONE);
                        TripPresenter.updateOrder(pos);
                    }else {
                        Toast.makeText(TripOrderDetailsActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(TripOrderDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(TripOrderDetailsActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callLink(int id){
        SharedClass.ShowWaiting(this);
        Call<AffiliateResponse> call = userService.getTripLink("Bearer "+userData.getToken() , id) ;
        call.enqueue(new Callback<AffiliateResponse>() {
            @Override
            public void onResponse(Call<AffiliateResponse> call, Response<AffiliateResponse> response) {
                SharedClass.hideWaiting();
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        copy(response.body().getData().getLink());
                    }else {
                        //Toast.makeText(CarOrderDetailsActivity.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(TripOrderDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<AffiliateResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(TripOrderDetailsActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void copy(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getResources().getString(R.string.url_is_copied), text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, getResources().getString(R.string.url_is_copied), Toast.LENGTH_SHORT).show();
    }

}
