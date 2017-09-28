package com.androidizate.climapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidizate.climapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * @author Andres Oller.
 */
public class WeeklyForecastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        RecyclerView recyclerView = view.findViewById(R.id.weekly_forecast_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapter(data));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mDataset;

        public MyAdapter(List<String> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_forecast, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);

                List<String> data2 = new ArrayList<>();
                data2.add("");
                data2.add("");
                data2.add("");
                data2.add("");
                data2.add("");
                data2.add("");
                RecyclerView recyclerView2 = itemView.findViewById(R.id.hourly_forecast_recycler_view);
                recyclerView2.setHasFixedSize(true);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(HORIZONTAL);
                recyclerView2.setLayoutManager(linearLayoutManager);
                recyclerView2.setAdapter(new MySecondAdapter(data2));
            }
        }
    }

    public class MySecondAdapter extends RecyclerView.Adapter<MySecondAdapter.ViewHolder> {
        private List<String> mDataset;

        public MySecondAdapter(List<String> myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MySecondAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hour_forecast, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
