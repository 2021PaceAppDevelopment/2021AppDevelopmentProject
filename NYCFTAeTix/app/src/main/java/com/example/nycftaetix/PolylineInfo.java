package com.example.nycftaetix;

import androidx.annotation.NonNull;

/**
 * This class keeps track of the polyLines
 * Whichever polyLine is clicked that one will turn blue
 * While the other stay gray
 *
 */
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.model.DirectionsLeg;

public class PolylineInfo {
    private Polyline line;
    private DirectionsLeg leg;
    // Directions Leg is what outlines the directions for the route itself

    public PolylineInfo(Polyline line, DirectionsLeg leg){
        this.line = line;
        this.leg = leg;
    }

    public DirectionsLeg getLeg() {
        return leg;
    }

    public Polyline getLine() {
        return line;
    }

    public void setLeg(DirectionsLeg leg) {
        this.leg = leg;
    }

    public void setLine(Polyline line) {
        this.line = line;
    }
    @NonNull
    @Override
    public  String toString(){
        return "Polyline info: " + "Polyline = " + line + ", leg = " + leg;
    }
}
