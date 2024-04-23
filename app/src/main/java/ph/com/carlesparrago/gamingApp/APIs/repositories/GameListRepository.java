package ph.com.carlesparrago.gamingApp.APIs.repositories;


import java.util.List;

import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.apiServices.APIServices;
import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.instance.URL;
import ph.com.carlesparrago.gamingApp.Module;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameListRepository {

    public void getGames(Module module, IGames iGames){
        APIServices apiServices = URL.baseAPI().create(APIServices.class);
        Call<List<GameResponse>> gameResponseCall = null;
        switch (module){
            case GAME:
                gameResponseCall = apiServices.gameListResponse(APIConstants.key, APIConstants.host);
                break;
            case HALL_OF_FAME:
                gameResponseCall = apiServices.hallOfFameList(APIConstants.key, APIConstants.host);
                break;
            case UPCOMING:
                gameResponseCall = apiServices.upcomingList(APIConstants.key, APIConstants.host);
                break;
        }
        gameResponseCall.enqueue(new Callback<List<GameResponse>>() {
            @Override
            public void onResponse(Call<List<GameResponse>> call, Response<List<GameResponse>> response) {
                if(response.isSuccessful()){
                    iGames.onResponse(response.body());
                }else{
                    iGames.onFailure(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<GameResponse>> call, Throwable t) {
                iGames.onThrowable(t.getMessage());
            }
        });
    }


    public interface IGames{
        void onResponse(List<GameResponse> gameResponseList);
        void onFailure(ResponseBody errorBody);
        void onThrowable(String message);
    }

}
