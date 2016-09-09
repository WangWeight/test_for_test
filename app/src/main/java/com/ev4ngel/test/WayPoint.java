package com.ev4ngel.test;

//import com.amap.api.maps2d.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

//import dji.sdk.FlightController.DJIFlightControllerDataType;

/**
 * Created by Administrator on 2016/7/18.
 * the designed file,save and load for several times.
 */
public class WayPoint implements IJson{
    static String item_lat="latitude";
    static String item_lng="longitude";
    static String item_status="status";
    public float status;
    public double lat;
    public double lng;
    public WayPoint(double _lat, double _lng, float s)
    {
        lat=_lat;
        lng=_lng;
        status=s;
    }
    public WayPoint()
    {
        lat=lng=status=0;
    }
    public WayPoint(LatLng loc, float s)
    {
        lat=loc.latitude;
        lng=loc.longitude;
        status=s;
    }
    @Override
    public JSONObject toJson()
    {
        JSONObject obj=new JSONObject();
        try {
            obj.put(item_lat, lat);
            obj.put(item_lng, lng);
            obj.put(item_status, status);
        }catch (JSONException je)
        {
        }
        return obj;
    }

    public static WayPoint fromJson(JSONObject jObj)
    {
        try {
            return new WayPoint(jObj.getDouble(item_lat), jObj.getDouble(item_lng),jObj.getInt(item_status));
        }catch (JSONException je)
        {
        }
        return new WayPoint();
    }
    /*
    public LatLng toLatLng()
    {
        return new LatLng(lat,lng);
    }
    public DJIFlightControllerDataType.DJILocationCoordinate2D toDJI2D()
    {
        return new DJIFlightControllerDataType.DJILocationCoordinate2D(lat,lng);
    }
    */
}
