package ph.com.carlesparrago.gamingApp.APIs.POJO.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ph.com.carlesparrago.gamingApp.APIs.models.Images;
import ph.com.carlesparrago.gamingApp.APIs.models.Platform;

public class GameResponse {

    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("percentRecommended")
    double percentRecommended;
    @SerializedName("numReviews")
    int numReviews;
    @SerializedName("topCriticScore")
    double topCriticScore;
    @SerializedName("tier")
    String tier;
    @SerializedName("images")
    Images images;
    @SerializedName("Platforms")
    List<Platform>platforms;

    public GameResponse(int id, String name, double percentRecommended, int numReviews, double topCriticScore, String tier, Images images, List<Platform> platforms) {
        this.id = id;
        this.name = name;
        this.percentRecommended = percentRecommended;
        this.numReviews = numReviews;
        this.topCriticScore = topCriticScore;
        this.tier = tier;
        this.images = images;
        this.platforms = platforms;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPercentRecommended() {
        return percentRecommended;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public double getTopCriticScore() {
        return topCriticScore;
    }

    public String getTier() {
        return tier;
    }

    public Images getImages() {
        return images;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }
}
