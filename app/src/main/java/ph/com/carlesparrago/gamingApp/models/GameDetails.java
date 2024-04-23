package ph.com.carlesparrago.gamingApp.models;

import java.util.List;

import ph.com.carlesparrago.gamingApp.APIs.models.Platform;

public class GameDetails {

    int id;
    String name;
    String image;
    String releaseDate;
    double topCriticScore;
    List<Platform> platformList;
    String description;

    public GameDetails(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public GameDetails(String name, String image, String releaseDate, double topCriticScore, List<Platform> platformList, String description) {
        this.name = name;
        this.image = image;
        this.releaseDate = releaseDate;
        this.topCriticScore = topCriticScore;
        this.platformList = platformList;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getTopCriticScore() {
        return topCriticScore;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
