package com.devopsbuddy.enums;

/**
 * Created by sadap on 3/20/2017.
 */
public enum PlansEnum {

    BASIC(1, "Basic"),
    PRO(2, "PRO") ;

    private final int id;
    private final String planName;

    PlansEnum(int id, String planName)
    {
        this.id = id;
        this.planName = planName ;
    }

    public int getId()
    {
        return id;

    }

    public String getPlanName() {
        return planName;
    }
}
