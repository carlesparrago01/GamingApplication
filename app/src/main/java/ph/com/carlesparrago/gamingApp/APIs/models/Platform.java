package ph.com.carlesparrago.gamingApp.APIs.models;

import com.google.gson.annotations.SerializedName;

public class Platform {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("shortName")
    String shortName;
    @SerializedName("releaseDate")
    String releaseDate;

    public Platform(int id, String name, String shortName, String releaseDate) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
