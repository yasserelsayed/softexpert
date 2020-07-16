package com.example.softexpert.ui.cars.views;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.softexpert.R;
import com.example.softexpert.domain.App;
import com.example.softexpert.domain.Car;
import com.example.softexpert.ui.cars.CarsMVP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by yasse on 12/18/2016.
 */
public class CarsRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<Car> Data;
    MainActivity mMainActivity;
    App _App;

    private static final int VIEWTYPE_none = -1;
    private static final int VIEWTYPE_ITEM = 1;
    private static final int VIEWTYPE_LOADER = 2;

    CarsMVP.GetCarsPresenter mGetCarsPresenter;
    public CarsRecycler(CarsMVP.GetCarsPresenter _GetCarsPresenter) {
        this.Data = new ArrayList<>();
        this.mGetCarsPresenter = _GetCarsPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView ;
        mMainActivity =  (MainActivity)parent.getContext();
        _App = (App)mMainActivity.getApplication();
        if (viewType == VIEWTYPE_LOADER) {
            itemView =     LayoutInflater.from(mMainActivity).inflate(R.layout.item_loading, parent, false);
            return new CarsRecycler.ListItemViewLoader(itemView);
        } else if(viewType == VIEWTYPE_ITEM){
            itemView =     LayoutInflater.from(mMainActivity).inflate(R.layout.car_item, parent, false);
            return new CarsRecycler.ListItemViewHolder(itemView);
        }else {
            itemView =     LayoutInflater.from(mMainActivity).inflate(R.layout.item_none, parent, false);
            return new CarsRecycler.ListItemViewLoader(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder  holder, int position) {

        if (holder instanceof ListItemViewLoader)
            mGetCarsPresenter.submit();
        else if(holder instanceof ListItemViewHolder) {
            CarsRecycler.ListItemViewHolder row = (CarsRecycler.ListItemViewHolder)holder;
            Car mCar = Data.get(position);

            if (mCar != null && mCar.getImageUrl() != null) {
                Picasso.get().load(mCar.getImageUrl())
                        .resize(200, 200)
                        .centerInside()
                        .placeholder(R.drawable.loader)
                        .into(row.imgCarPhoto);
            } else row.imgCarPhoto.setImageResource(R.drawable.image_background);

            row.txtBrand.setText(mCar.getBrand());
            row.txtConstructionYear.setText(mCar.getConstructionYear());
            row.chkIsUsed.setChecked(mCar.getIsUsed());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position == getItemCount() - 1 )
            if(mGetCarsPresenter.getPage()>-1)
                return VIEWTYPE_LOADER ;
            else return VIEWTYPE_none;
        else return VIEWTYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        if (Data == null || Data.size() == 0)
            return 0;
        return Data.size() + 1;
    }




    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgCarPhoto ;
        public TextView txtBrand ;
        public TextView txtConstructionYear;
        public AppCompatCheckBox chkIsUsed;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            imgCarPhoto = itemView.findViewById(R.id.imgCarPhoto);
            txtBrand = itemView.findViewById(R.id.txtBrand);
            txtConstructionYear = itemView.findViewById(R.id.txtConstructionYear);
            chkIsUsed = itemView.findViewById(R.id.chkIsUsed);
        }
    }

    public static class ListItemViewLoader extends RecyclerView.ViewHolder {

        public ListItemViewLoader(View itemView) {
            super(itemView);
        }

    }
}
