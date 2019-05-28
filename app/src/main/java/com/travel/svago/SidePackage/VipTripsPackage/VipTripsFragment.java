package com.travel.svago.SidePackage.VipTripsPackage;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.travel.svago.Models.CountriesResponses.CountyData;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.Models.SimpleResponse.SimpleResponse;
import com.travel.svago.R;
import com.travel.svago.Remote.ApiUtlis;
import com.travel.svago.Remote.UserService_POST;
import com.travel.svago.SharedPackage.Activity.MainHomeActivity;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.travel.svago.SharedPackage.Classes.CountryAdapterHelper;
import com.travel.svago.SharedPackage.Classes.SharedClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipTripsFragment extends Fragment {


    @BindView(R.id.edtPhone)
    EditText edtPhone;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtLocation)
    EditText edtLocation;
    @BindView(R.id.edtCountry)
    AppCompatSpinner edtCountry;
    @BindView(R.id.edtType)
    AppCompatSpinner edtType;
    @BindView(R.id.from)
    EditText from;
    @BindView(R.id.to)
    EditText to;
    @BindView(R.id.budgetFrom)
    EditText budgetFrom;
    @BindView(R.id.budgetTo)
    EditText budgetTo;
    @BindView(R.id.edtDesc)
    EditText edtDesc;
    @BindView(R.id.submit)
    Button submit;
    Unbinder unbinder;

    private CountryAdapterHelper adapterHelper;
    private CountyData countyData;
    private com.travel.svago.Models.SharedResponses.userData userData;

    private String Type, sDate, eDate;
    private UserService_POST userService;
    private boolean isFrom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vip_trips, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getData();
        SelectItems();
        setSpinnerType();
        return view;
    }

    private void initView() {
        userService = ApiUtlis.getUserServices_Post();
        adapterHelper = new CountryAdapterHelper(getActivity());
        adapterHelper.PrepareSpinner(edtCountry);

    }

    private void getData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            userData = (com.travel.svago.Models.SharedResponses.userData) bundle.get(Constant.userFlag);
        }
    }

    private void SelectItems() {
        edtCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countyData = (CountyData) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerType() {
        List<String> missionType = new ArrayList<String>();
        missionType.add(getResources().getString(R.string.religious));
        missionType.add(getResources().getString(R.string.Entertainment));
        missionType.add(getResources().getString(R.string.culture));
        missionType.add(getResources().getString(R.string.haj));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, missionType);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        edtType.setAdapter(dataAdapter);
        // Spinner click listener
        edtType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Type = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void validate() {
        String phone = edtPhone.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String location = edtLocation.getText().toString().trim();
        String fromm = from.getText().toString().trim();
        String too = to.getText().toString().trim();
        String budgetFromm = budgetFrom.getText().toString().trim();
        String budgetToo = budgetTo.getText().toString().trim();
        String desc = edtDesc.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            edtPhone.setError(getResources().getString(R.string.requiredField));
            edtPhone.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError(getResources().getString(R.string.requiredField));
            edtEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(location)) {
            edtLocation.setError(getResources().getString(R.string.requiredField));
            edtLocation.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fromm)) {
            from.setError(getResources().getString(R.string.requiredField));
            from.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(too)) {
            to.setError(getResources().getString(R.string.requiredField));
            to.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(budgetFromm)) {
            budgetFrom.setError(getResources().getString(R.string.requiredField));
            budgetFrom.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(budgetToo)) {
            budgetTo.setError(getResources().getString(R.string.requiredField));
            budgetTo.requestFocus();
            return;
        }

        callSubmit(phone, email, location, fromm, too, budgetFromm, budgetToo, desc, countyData.getTitle(), Type);

    }

    private void callSubmit(String phone, String email, String location, String fromm, String too, String budgetFromm, String budgetToo, String desc, String country, String type) {
        SharedClass.ShowWaiting(getActivity());
        Log.d("OOPOP" , userData.getToken()) ;
        Call<SimpleResponse> call = userService.orderVipTrip("Bearer "+userData.getToken() ,
                phone , email , location , country , type , fromm , too , budgetFromm , budgetToo , desc ) ;
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SharedClass.hideWaiting();
                Log.d("OOO" , new Gson().toJson(response)) ;
                if (response.isSuccessful()){
                    if (response.body().getStatus() == 200){
                        Toast.makeText(getActivity(), getResources().getString(R.string.Trip_requested_successfully), Toast.LENGTH_SHORT).show();
                        MainHomeActivity.setFragmentWithType(Constant.SvagoTag);
                    }else {
                        Toast.makeText(getActivity(), response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), response.message() , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                SharedClass.hideWaiting();
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @SuppressLint("NewApi")
    private void setDatePickDialog() {
        final Calendar c = Calendar.getInstance();
        final int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        final int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (isFrom) {
                            sDate = year + "-" + monthOfYear + "-" + dayOfMonth;
                            from.setText(sDate);
                        } else {
                            eDate = year + "-" + monthOfYear + "-" + dayOfMonth;
                            to.setText(eDate);
                        }
                        //Log.d("DDD", sDate);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        //datePickerDialog.setTitle(getString(R.string.select_date));
        datePickerDialog.show();
        // dialog.show();
    }

   /* @SuppressLint("NewApi")
    private void setTimePickDialog() {
        Calendar mCurrentTime = Calendar.getInstance();
        int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mCurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                sTime = selectedHour + ":" + selectedMinute + ":00";
                ti.setText(sTime);
                Log.d("TTT", sTime);
                Log.d("RRR", sDate + " " + sTime);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle(getString(R.string.select_time));
        mTimePicker.show();
    }

*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.from, R.id.to, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.from:
                isFrom=true;
                setDatePickDialog();
                break;
            case R.id.to:
                isFrom=false;
                setDatePickDialog();
                break;
            case R.id.submit:
                validate();
                break;
        }
    }
}
