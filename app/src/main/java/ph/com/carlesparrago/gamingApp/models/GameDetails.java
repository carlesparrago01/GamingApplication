package ph.com.carlesparrago.gamingApp.models;

import java.util.List;

import ph.com.carlesparrago.gamingApp.APIs.models.Platform;

public class GameDetails {

    String name;
    String image;
    String releaseDate;
    String topCriticScore;
    List<Platform> platformList;

    public GameDetails(String name, String image, String releaseDate, String topCriticScore, List<Platform> platformList) {
        this.name = name;
        this.image = image;
        this.releaseDate = releaseDate;
        this.topCriticScore = topCriticScore;
        this.platformList = platformList;
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

    public String getTopCriticScore() {
        return topCriticScore;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }
}
