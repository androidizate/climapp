/* (c) Androidizate. Todos los derechos reservados. */
package com.androidizate.climapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.androidizate.climapp.api.RestApiClient;
import com.androidizate.climapp.dao.WeatherInfo;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andres Oller.
 **/
public class MainActivity extends AppCompatActivity implements Callback<WeatherInfo> {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @BindView(R.id.message) TextView mTextMessage; //Butterknife
    @BindView(R.id.navigation) BottomNavigationView navigation;//Butterknife
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RestApiClient restApiClient = new RestApiClient();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("lat", "-31.4200830");
        parameters.put("lon", "-64.1887760");
        parameters.put("units", "metric");
        restApiClient.getWeatherInfo(parameters).enqueue(this);
    }

    @Override
    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
        Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<WeatherInfo> call, Throwable t) {
        Toast.makeText(this, "Error de conexion", Toast.LENGTH_LONG).show();
    }
}
