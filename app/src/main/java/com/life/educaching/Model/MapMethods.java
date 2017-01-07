package com.life.educaching.Model;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by p.root on 10.12.2016.
 */

public class MapMethods {
    /**
     * Methode zum Errechen der unteren linken (southwest) und oberen rechten (northeast) Ecke der Fläche, die auf dem Bildschirm angezeigt wird (LatLngBounds)
     * @param stationen
     * @return
     */
    public static LatLngBounds calculateLatLngBounds (LatLng [] stationen)
    {
        if (stationen.length>1) {
            //minimalen Breitengrad finden
            double south = stationen[0].latitude;
            for (int i = 1; i < stationen.length; i++) {
                if (south > stationen[i].latitude) {
                    south = stationen[i].latitude;
                }
            }
            //maximalen Breitengrad finden
            double north = stationen[0].latitude;
            for (int i = 1; i < stationen.length; i++) {
                if (north < stationen[i].latitude) {
                    north = stationen[i].latitude;
                }
            }
//minimalen Längengrad finden
            double west = stationen[0].longitude;
            for (int i = 1; i < stationen.length; i++) {
                if (west > stationen[i].longitude) {
                    west = stationen[i].longitude;
                }
            }
//maximalen Längengrad finden
            double east = stationen[0].longitude;
            for (int i = 1; i < stationen.length; i++) {
                if (east < stationen[i].longitude) {
                    east = stationen[i].longitude;
                }
            }
            LatLng southwest = new LatLng(south, west);
            LatLng northeast = new LatLng(north, east);
            return new LatLngBounds(southwest, northeast);
        }
        return new LatLngBounds(stationen[0], stationen[0]);
    }
}
