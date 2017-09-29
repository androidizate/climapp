package com.androidizate.climapp.dao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;

/**
 * @author Andres Oller
 */
@Entity
public class Location {

    @Id
    private long id;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    @ToOne(joinProperty = "actualWeatherId")
    private ActualWeather actualWeather;
    private long actualWeatherId;

    @ToMany(referencedJoinProperty = "id")
    private List<DailyWeather> dailyWeatherList;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 842527347)
    private transient LocationDao myDao;
    @Generated(hash = 1514977190)
    private transient Long actualWeather__resolvedKey;

    @Generated(hash = 1586988432)
    public Location(long id, String name, String country, double latitude,
                    double longitude, long actualWeatherId) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.actualWeatherId = actualWeatherId;
    }

    @Generated(hash = 375979639)
    public Location() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getActualWeatherId() {
        return this.actualWeatherId;
    }

    public void setActualWeatherId(long actualWeatherId) {
        this.actualWeatherId = actualWeatherId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1299187621)
    public ActualWeather getActualWeather() {
        long __key = this.actualWeatherId;
        if (actualWeather__resolvedKey == null
                || !actualWeather__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ActualWeatherDao targetDao = daoSession.getActualWeatherDao();
            ActualWeather actualWeatherNew = targetDao.load(__key);
            synchronized (this) {
                actualWeather = actualWeatherNew;
                actualWeather__resolvedKey = __key;
            }
        }
        return actualWeather;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1463767526)
    public void setActualWeather(@NotNull ActualWeather actualWeather) {
        if (actualWeather == null) {
            throw new DaoException(
                    "To-one property 'actualWeatherId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.actualWeather = actualWeather;
            actualWeatherId = actualWeather.getCityId();
            actualWeather__resolvedKey = actualWeatherId;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1312314408)
    public List<DailyWeather> getDailyWeatherList() {
        if (dailyWeatherList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DailyWeatherDao targetDao = daoSession.getDailyWeatherDao();
            List<DailyWeather> dailyWeatherListNew = targetDao
                    ._queryLocation_DailyWeatherList(id);
            synchronized (this) {
                if (dailyWeatherList == null) {
                    dailyWeatherList = dailyWeatherListNew;
                }
            }
        }
        return dailyWeatherList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1322852288)
    public synchronized void resetDailyWeatherList() {
        dailyWeatherList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1046799944)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLocationDao() : null;
    }


}
