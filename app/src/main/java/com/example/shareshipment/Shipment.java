package com.example.shareshipment;

import android.os.Parcel;
import android.os.Parcelable;

public class Shipment implements Parcelable {
    private String shipmentType;
    private String size;
    private String weight;
    private int fee;
    private  String deadline;
    private String image;
    protected Shipment(Parcel in) {
        setShipmentType(in.readString());
        setSize(in.readString());
        setWeight(in.readString());
        setFee(in.readInt());
        setDeadline(in.readString());
        setImage(in.readString());
    }

    public static final Creator<Shipment> CREATOR = new Creator<Shipment>() {
        @Override
        public Shipment createFromParcel(Parcel in) {
            return new Shipment(in);
        }

        @Override
        public Shipment[] newArray(int size) {
            return new Shipment[size];
        }
    };

    public Shipment(String shipmentType, int fee, String weight, String size,String deadline) {
            this.shipmentType = shipmentType;
            this.fee = fee;
            this.weight = weight;
            this.size = size;
            this.deadline=deadline;

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getShipmentType());
        dest.writeString(getSize());
        dest.writeString(getWeight());
        dest.writeInt(getFee());
        dest.writeString(getDeadline());
        dest.writeString(getImage());
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
