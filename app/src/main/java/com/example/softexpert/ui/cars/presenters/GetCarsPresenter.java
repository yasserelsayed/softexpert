package com.example.softexpert.ui.cars.presenters;



import com.example.softexpert.domain.Car;
import com.example.softexpert.util.NetWorkHandler;
import com.example.softexpert.ui.cars.CarsMVP;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GetCarsPresenter implements CarsMVP.GetCarsPresenter{

    CarsMVP.CarsView mCarsView;
    CarsMVP.CarsModel mCarsModel;
    NetWorkHandler mNetWorkHandler;

    Observable<List<Car>> res;
    int page=1;

    public GetCarsPresenter(CarsMVP.CarsModel _CarsModel, NetWorkHandler _NetWorkHandler)
    {
        this.mCarsModel = _CarsModel;
        mNetWorkHandler = _NetWorkHandler;
    }

    @Override
    public void setView(CarsMVP.CarsView _CarsView) {
        mCarsView = _CarsView;
    }

    @Override
    public void submit() {
        if(!mNetWorkHandler.isNetworkConnected()){
            mCarsView.hideLoadingPopup();
            mCarsView.showNetworkError();
            return;
        }

        if (page > 0){
            if(page==1)
                mCarsView.showLoadingPopup();
            res  = mCarsModel.getCars(page);
            res.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Car>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Car> value) {
                        try{
                            mCarsView.hideLoadingPopup();
                          if (value != null) {
                                if (value.size() > 0) {
                                    mCarsView.setCars(value);
                                    page++;
                                }else {
                                    page = -1;
                                    mCarsView.setCars(null);
                                }
                            }else {
                                page = -1;
                              mCarsView.setCars(null);
                            }
                        }catch (Exception e){
                            mCarsView.raiseError();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCarsView.showLoadingPopup();
                        mCarsView.showNotification(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                });
          }
        }

    @Override
    public int getPage() {
        return page;
    }

    public void unsubscribe(){
        if(res!=null)
        res.unsubscribeOn(Schedulers.io());
    }

    @Override
    public void resetPaging() {
        page = 1;
    }
}


