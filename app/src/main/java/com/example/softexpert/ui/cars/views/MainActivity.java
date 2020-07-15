package com.example.softexpert.ui.cars.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

import com.example.softexpert.R;
import com.example.softexpert.di.component.DaggerMainActivityComponent;
import com.example.softexpert.di.component.MainActivityComponent;
import com.example.softexpert.di.module.CarModule;
import com.example.softexpert.domain.App;
import com.example.softexpert.domain.Car;
import com.example.softexpert.ui.cars.CarsMVP;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements CarsMVP.CarsView {

    Snackbar snackbar;
    CarsRecycler mCarsRecycler;

    @Nullable
    @BindView(R.id.appContainer)
    ConstraintLayout appContainer;
    @Nullable
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @BindView(R.id.rclCars)
    RecyclerView rclCars;


    Unbinder unbinder;

    @Inject
    CarsMVP.GetCarsPresenter mGetCarsPresenter;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mGetCarsPresenter.unsubscribe();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        App mApp = (App)getApplication();
        MainActivityComponent mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(mApp.mAppComponent)
                .carModule(new CarModule())
                .build();
        mMainActivityComponent.inject(this);
        mGetCarsPresenter.setView(this);
        mGetCarsPresenter.getCars();

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
          mGetCarsPresenter.resetPaging();
          mCarsRecycler.Data.clear();
          mCarsRecycler.notifyDataSetChanged();
          mGetCarsPresenter.getCars();
        });
        mSwipeRefreshLayout.setRefreshing(true);
        mCarsRecycler  = new CarsRecycler(mGetCarsPresenter);
        rclCars.setLayoutManager(new LinearLayoutManager(this));
        rclCars.setAdapter(mCarsRecycler);
    }


    @Override
    public void setCars(List<Car> lstCars) {
        if(lstCars!=null && lstCars.size() > 0) {
            int init = mCarsRecycler.Data.size();
            mCarsRecycler.Data.addAll(lstCars);
            mCarsRecycler.notifyItemRangeInserted(init, lstCars.size());
        }else if(mCarsRecycler.Data.size()>0) {
            mCarsRecycler.notifyItemChanged(mCarsRecycler.Data.size()+1);
        }
    }

    @Override
    public void reset() {

    }

    @Override
    public void showNotification(Integer resNum) {
        Toast.makeText(this,getString(resNum),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNotification(String notification) {
        Toast.makeText(this,notification,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingPopup() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoadingPopup() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNetworkError() {
        snackbar  = Snackbar
                .make(appContainer, getText(R.string.txt_please_connect_internet), Snackbar.LENGTH_INDEFINITE)
                .setAction(getText(R.string.txt_retry), view -> {
                    finish();
                    startActivity(getIntent());
                });
        snackbar.show();
    }

    @Override
    public void raiseError() {
        Toast.makeText(this,getString(R.string.txt_try_again),Toast.LENGTH_LONG).show();
    }
}
