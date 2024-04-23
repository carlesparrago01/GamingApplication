package ph.com.carlesparrago.gamingApp.APIs.models;

import com.google.gson.annotations.SerializedName;

public class Banner {

    @SerializedName("og")
    String og;
    @SerializedName("sm")
    String sm;

    public Banner(String og, String sm) {
        this.og = og;
        this.sm = sm;
    }

    public String getOg() {
        return og;
    }

    public String getSm() {
        return sm;
    }

}
