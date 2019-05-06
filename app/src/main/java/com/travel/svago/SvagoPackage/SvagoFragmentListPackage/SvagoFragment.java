package com.travel.svago.SvagoPackage.SvagoFragmentListPackage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agrawalsuneet.loaderspack.loaders.CurvesLoader;
import com.travel.svago.Models.SharedResponses.userData;
import com.travel.svago.R;
import com.travel.svago.SharedPackage.Classes.Constant;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SvagoFragment extends Fragment {

    private View view;
    private SvagoPresenter svagoPresenter;
    private userData userObject;

    @BindView(R.id.recycleSvago)
    RecyclerView recyclerSvago;
    @BindView(R.id.progress)
    CurvesLoader progress;

    public SvagoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_svago, container, false);
        ButterKnife.bind(this,view);
        getIntentData();
        initComponents();

        return view;
    }

    private void getIntentData() {
        Bundle bundle=getArguments();
        if (!bundle.isEmpty()){
            userObject=(userData) bundle.getSerializable(Constant.userFlag);
        }
    }

    private void initComponents() {
        svagoPresenter=new SvagoPresenter(this,userObject);
        svagoPresenter.callData();
        //region ImageLoader
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext().getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);
        //endregion
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
