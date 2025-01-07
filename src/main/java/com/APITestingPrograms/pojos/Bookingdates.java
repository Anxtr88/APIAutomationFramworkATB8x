package com.APITestingPrograms.PayLoadMangement.Pojo_gson;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//@Generated("jsonschema2pojo")
public class Bookingdates {

    @SerializedName("checkin")
    @Expose
    private String checkin;
    @SerializedName("checkout")
    @Expose
    private String checkout;

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

}