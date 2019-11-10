package com.basant.yesicbap.financialmath.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "notes")
public class Note implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private  String description;

    @ColumnInfo(name = "cost")
    private  float cost;

    @ColumnInfo(name = "status")
    private boolean status;

    @ColumnInfo(name = "timestamp")
    private String timestamp;


    //constructor
    public Note(String title, String description, float cost, boolean status, String timestamp) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.status = status;
        this.timestamp = timestamp;
    }
    @Ignore
    public Note() {

    }


    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        cost = in.readFloat();
        status = in.readByte() != 0;
        timestamp = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    //getter and setter method
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isStatus() {
        return status;
    }

    public static Creator<Note> getCREATOR() {
        return CREATOR;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



    //string method


    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeFloat(cost);
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeString(timestamp);
    }
}
