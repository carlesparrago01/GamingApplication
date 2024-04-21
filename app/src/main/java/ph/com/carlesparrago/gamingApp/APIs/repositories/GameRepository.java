package ph.com.carlesparrago.gamingApp.APIs.repositories;


import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.apiServices.APIServices;
import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.instance.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {

    public void getGames(IGames iGames){
        APIServices apiServices = URL.baseAPI().create(APIServices.class);
        Call<GameResponse> gameResponseCall = apiServices.gameResponse(APIConstants.key, APIConstants.host);
        gameResponseCall.enqueue(new Callback<GameResponse>() {
            @Override
            public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                if(response.isSuccessful()){
                    iGames.onResponse(response.body());
                }else{
                    iGames.onFailure(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<GameResponse> call, Throwable t) {
                iGames.onThrowable(t.getMessage());
            }
        });

    }

    public interface IGames{
        void onResponse(GameResponse gameResponse);
        void onFailure(ResponseBody errorBody);
        void onThrowable(String message);
    }

}
