package com.techteamuk.app.android_boxes.fragments;

import com.techteamuk.app.android_boxes.R;

/**
 * Created by jim on 16/01/2017.
 */

public class Planet {
    String name;
    int eqCircumference;
    int moons;
    int orbitPeriod;
    int maxTemp;
    public static String[] names = {"Jupiter", "Mercury", "Venus", "Earth", "Saturn", "Neptune", "Mars"};

    public static Planet jupiter = new Planet(names[0],439264,67,4338,-108);
    public static Planet mercury = new Planet(names[1],15329,0,87,427);
    public static Planet venus = new Planet(names[2],38025,0,224,462);
    public static Planet earth = new Planet(names[3],40030,1,365,58);
    public static Planet saturn = new Planet(names[4],365882,62,10755,-139);
    public static Planet neptune = new Planet(names[5],155600,14,60190,-201);
    public static Planet mars = new Planet(names[6],21297,2,686,-5);

    public static Planet planetFacts[] = {jupiter,mercury,venus,earth,saturn,neptune,mars};

    // Resource IDs of drawable images placed in res/drawable folder
    public static int[] planetResource = new int[]{R.drawable.jupiter, R.drawable.mercury, R.drawable.venus, R.drawable.earth, R.drawable.saturn, R.drawable.neptune, R.drawable.mars};

    // Constructor
    public Planet(String name, int eqC, int moons, int orbitPeriod, int maxTemp) {
        this.name = name;
        this.eqCircumference = eqC;
        this.moons = moons;
        this.orbitPeriod = orbitPeriod;
        this.maxTemp = maxTemp;
    }

    // Accessors
    public String getName() { return name; }
    public int getEqCircumference() { return eqCircumference; }
    public int getMoons() { return moons; }
    public int getOrbitPeriod() { return orbitPeriod; }
    public int getMaxTemp() { return maxTemp; };
    public int calcEarthMonth() { return orbitPeriod / 12; }

}
