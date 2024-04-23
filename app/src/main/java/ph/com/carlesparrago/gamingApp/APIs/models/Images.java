package ph.com.carlesparrago.gamingApp.APIs.models;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("banner")
    Banner banner;
    @SerializedName("box")
    Box box;

    public Images(Banner banner) {
        this.banner = banner;
    }

    public Images(Box box){
        this.box = box;
    }

    public Banner getBanner() {
        return banner;
    }

    public Box getBox() {
        return box;
    }
}
