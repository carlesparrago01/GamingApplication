package ph.com.carlesparrago.gamingApp.APIs.apiServices;

import java.util.List;

import kotlin.ParameterName;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.constant.EndPoints;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServices {

    @GET(EndPoints.game)
    Call<List<GameResponse>> gameListResponse(@Header("X-RapidAPI-Key") String key, @Header("X-RapidAPI-Host") String host);
    @GET(EndPoints.gameId)
    Call<GameResponse> gameResponse(@Header("X-RapidAPI-Key") String key, @Header("X-RapidAPI-Host") String host, @Path("id") int id);
    @GET(EndPoints.hallOfFame)
    Call<List<GameResponse>> hallOfFameList(@Header("X-RapidAPI-Key") String key, @Header("X-RapidAPI-Host") String host);
    @GET(EndPoints.upcoming)
    Call<List<GameResponse>> upcomingList(@Header("X-RapidAPI-Key") String key, @Header("X-RapidAPI-Host") String host);
}
