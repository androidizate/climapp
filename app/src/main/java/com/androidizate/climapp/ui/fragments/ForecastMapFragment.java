package com.androidizate.climapp.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.androidizate.climapp.R;
import com.androidizate.climapp.constants.MapLayersConstants;
import com.androidizate.climapp.utils.PermissionUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_CLOUDS;

/**
 * @author Andres Oller.
 */
public class ForecastMapFragment extends SupportMapFragment implements OnMapReadyCallback, LocationListener {

    GoogleMap googleMap;
    TileProvider tileProvider;
    TileOverlay tileOverlay;
    private Marker marca;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
        createTileProvider(LAYER_CLOUDS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (Manifest.permission.ACCESS_FINE_LOCATION == permissions[0] && grantResults[0] == 1) {
            miUbicacion();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-31.4200830, -64.1887760)));
        miUbicacion();
        setTileOverlay();
    }

    private void setTileOverlay() {
        tileOverlay = googleMap.addTileOverlay(new TileOverlayOptions().tileProvider(tileProvider));
        tileOverlay.setFadeIn(true);
        tileOverlay.setTransparency(0.5f);
    }

    private void createTileProvider(@MapLayersConstants String layer) {
        updateTileProvider(layer);
    }

    private void updateTileProvider(@MapLayersConstants final String layer) {
        tileProvider = new UrlTileProvider(32, 32) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                String urlTileProvider = String.format(getString(R.string.tile_url), layer, zoom, x, y, getString(R.string.weather_api_id));
                URL url = null;
                try {
                    url = new URL(urlTileProvider);
                } catch (MalformedURLException malformedUrlException) {
                    malformedUrlException.printStackTrace();
                }
                return url;
            }
        };
    }

    public void reloadTileProvider(@MapLayersConstants String layer) {
        tileOverlay.remove();
        updateTileProvider(layer);
        setTileOverlay();
    }

    public void agreagarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marca != null) marca.remove();
        marca = googleMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Posicion Actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        googleMap.animateCamera(miUbicacion);
    }

    public void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agreagarMarcador(lat, lng);
        }

    }

    public void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            List<String> permissions = new ArrayList<>();
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            PermissionUtils.checkPermissions((AppCompatActivity) getActivity(), permissions);
            return;
        }
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
    }


    @Override
    public void onLocationChanged(Location location) {
        actualizarUbicacion(location);


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
