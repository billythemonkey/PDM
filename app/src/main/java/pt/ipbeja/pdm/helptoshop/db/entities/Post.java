package pt.ipbeja.pdm.helptoshop.db.entities;


import android.content.res.Resources;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;


@Entity(tableName = "posts")
public class Post {

    @PrimaryKey(autoGenerate = true)
    private final long id;

    @ColumnInfo(name = "title")
    private final String postTitle;

    @ColumnInfo(name = "author")
    private final String postCreator;

    @ColumnInfo(name = "description")
    private final String postShoppingList;

    @ColumnInfo(name = "date")
    private final String postCreateDate;

    @ColumnInfo(name = "state")
    private String postState;

    @ColumnInfo(name = "contact")
    private final long postContact;

    @ColumnInfo(name = "latitude")
    private final double lat;

    @ColumnInfo(name = "longitude")
    private final double lgtd;


    public Post(long id, String postTitle, String postCreator, String postShoppingList, String postCreateDate, String postState, long postContact, double lat, double lgtd) {
        this.id = id;
        this.postTitle = postTitle;
        this.postCreator = postCreator;
        this.postShoppingList = postShoppingList;
        this.postCreateDate = postCreateDate;
        this.postState = postState;
        this.postContact = postContact;
        this.lat = lat;
        this.lgtd = lgtd;
    }

    @Ignore
    public Post(String postTitle, String postCreator, String postShoppingList, String postCreateDate, long postContact, double lat, double lgtd) {
        this.id = 0;
        this.postTitle = postTitle;
        this.postCreator = postCreator;
        this.postShoppingList = postShoppingList;
        this.postCreateDate = postCreateDate;
        this.postContact = postContact;
        this.lat = lat;
        this.lgtd = lgtd;
    }


    public long getId() {
        return id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostCreator() {
        return postCreator;
    }

    public String getPostShoppingList() {
        return postShoppingList;
    }

    public String getPostCreateDate() {
        return postCreateDate;
    }

    public String getPostState() {
        return postState;
    }

    public long getPostContact() {
        return postContact;
    }

    public LatLng getPostLocation(long lat, long lgtd) {
        return new LatLng(lat, lgtd);
    }

    public void setPostState(String postState) {
        this.postState = postState;
    }

    public double getLat() {
        return lat;
    }

    public double getLgtd() {
        return lgtd;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postCreator='" + postCreator + '\'' +
                ", postShoppingList='" + postShoppingList + '\'' +
                ", postCreateDate='" + postCreateDate + '\'' +
                ", postState='" + postState + '\'' +
                ", postContact=" + postContact +
                ", lat=" + lat +
                ", lgtd=" + lgtd +
                '}';
    }
}
