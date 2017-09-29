package com.androidizate.climapp.data.db.repository.location;

import com.androidizate.climapp.data.db.model.Location;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */

public interface LocationRepository {

    Observable<Boolean> isLocationEmpty();

    Observable<Boolean> saveLocation(Location location);

    Observable<Location> getLocation(long cityId);

    Observable<List<Location>> getAllLocation();

    boolean locationExists(long cityId);
}
