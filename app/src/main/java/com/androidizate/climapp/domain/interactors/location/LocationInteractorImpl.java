package com.androidizate.climapp.domain.interactors.location;

import com.androidizate.climapp.data.db.model.Location;
import com.androidizate.climapp.data.db.repository.location.LocationRepository;
import com.androidizate.climapp.data.network.NetworkRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */
public class LocationInteractorImpl implements LocationInteractor {

    NetworkRepository networkRepository;
    LocationRepository locationRepository;

    @Inject
    LocationInteractorImpl(NetworkRepository networkRepository, LocationRepository locationRepository) {
        this.networkRepository = networkRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Observable<Location> getCurrentLocation(final Long cityId) {
        return locationRepository.getLocation(cityId);
    }

    @Override
    public Observable<List<Location>> getAllLocations() {
        return locationRepository.getAllLocation();
    }

    @Override
    public Observable<Boolean> saveLocation(Location location) {
        return locationRepository.saveLocation(location);
    }
}
