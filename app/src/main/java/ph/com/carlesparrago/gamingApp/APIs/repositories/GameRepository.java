package ph.com.carlesparrago.gamingApp.APIs.repositories;

import java.util.List;

import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.apiServices.APIServices;
import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.instance.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameRepository {

    public void getGame(int id, IGame iGame){
        APIServices apiServices = URL.baseAPI().create(APIServices.class);
        Call<GameResponse> gameResponseCall = apiServices.gameResponse(APIConstants.key, APIConstants.host, id);
        gameResponseCall.enqueue(new Callback<GameResponse>() {
            @Override
            public void onResponse(Call<GameResponse> call, Response<GameResponse> response) {
                if(response.isSuccessful()){
                    iGame.onResponse(response.body());
                }else{
                    iGame.onFailure(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<GameResponse> call, Throwable t) {
                iGame.onThrowable(t.getMessage());
            }
        });

    }

    public  interface IGame{
        void onResponse(GameResponse gameResponse);
        void onFailure(ResponseBody errorBody);
        void onThrowable(String message);
    }
}
