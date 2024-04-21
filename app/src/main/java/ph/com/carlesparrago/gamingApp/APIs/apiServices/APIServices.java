package ph.com.carlesparrago.gamingApp.APIs.apiServices;

import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.constant.EndPoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface APIServices {

    @GET(EndPoints.game)
    Call<GameResponse> gameResponse(@Header("X-RapidAPI-Key") String key, @Header("X-RapidAPI-Host") String host);

}
