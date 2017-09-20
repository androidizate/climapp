package com.androidizate.climapp.ui.fragments;

import android.os.Bundle;

import com.androidizate.climapp.R;
import com.androidizate.climapp.constants.MapLayersConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;

import java.net.MalformedURLException;
import java.net.URL;

import static com.androidizate.climapp.constants.MapLayersConstants.LAYER_CLOUDS;

/**
 * @author Andres Oller.
 */
public class ForecastMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap googleMap;
    TileProvider tileProvider;
    TileOverlay tileOverlay;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
        createTileProvider(LAYER_CLOUDS);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-31.4200830, -64.1887760)));
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
}
