package com.androidizate.climapp.data.db.repository.location;

import com.androidizate.climapp.data.db.model.DaoSession;
import com.androidizate.climapp.data.db.model.Location;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Andres Oller
 */
public class LocationRepositoryImpl implements LocationRepository {

    private DaoSession daoSession;

    @Inject
    public LocationRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<Boolean> isLocationEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !(daoSession.getLocationDao().count() > 0);
            }
        });
    }

    @Override
    public Observable<Boolean> saveLocation(final Location location) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                daoSession.getLocationDao().insert(location);
                return true;
            }
        });
    }

    @Override
    public Observable<Location> getLocation(final long cityId) {
        return Observable.fromCallable(new Callable<Location>() {
            @Override
            public Location call() throws Exception {
                return daoSession.getLocationDao().load(cityId);
            }
        });
    }

    @Override
    public Observable<List<Location>> getAllLocation() {
        return Observable.fromCallable(new Callable<List<Location>>() {
            @Override
            public List<Location> call() throws Exception {
                return daoSession.getLocationDao().loadAll();
            }
        });
    }

    @Override
    public boolean locationExists(long cityId) {
        return daoSession.getLocationDao().load(cityId) != null;
    }
}
