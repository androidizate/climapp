package com.androidizate.climapp.domain.interactors.location;

import com.androidizate.climapp.data.db.model.Location;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface LocationInteractor {

    Observable<Location> getCurrentLocation(Long cityId);

    Observable<List<Location>> getAllLocations();

    Observable<Boolean> saveLocation(Location location);
}
