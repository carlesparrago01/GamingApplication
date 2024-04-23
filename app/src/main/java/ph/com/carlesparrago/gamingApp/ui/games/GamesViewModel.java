package ph.com.carlesparrago.gamingApp.ui.games;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.models.Banner;
import ph.com.carlesparrago.gamingApp.APIs.models.Box;
import ph.com.carlesparrago.gamingApp.APIs.models.Images;
import ph.com.carlesparrago.gamingApp.APIs.repositories.GameListRepository;
import ph.com.carlesparrago.gamingApp.Module;
import ph.com.carlesparrago.gamingApp.models.GameDetails;

public class GamesViewModel extends ViewModel {

    private GameListRepository gameListRepository;

    MutableLiveData<List<GameDetails>> mBannerGames;

    public GamesViewModel() {
        mBannerGames = new MutableLiveData<>();
        gameListRepository = new GameListRepository();
    }

    public void getGames(){
        gameListRepository.getGames(Module.GAME, new GameListRepository.IGames() {
            @Override
            public void onResponse(List<GameResponse> gameResponseList) {
                List<GameDetails> imageEndPoint = new ArrayList<>();
                for(GameResponse gameResponse : gameResponseList){
                    int id = gameResponse.getId();
                    String name = gameResponse.getName();

                    Banner banner = gameResponse.getImages().getBanner();
                    Box box = gameResponse.getImages().getBox();
                    String imageURL;
                    Images images;
                    if(banner == null){
                        images = new Images(box);
                        imageURL = APIConstants.imageUrl + images.getBox().getOg();
                    }else{
                        images = new Images(banner);
                        imageURL = APIConstants.imageUrl + images.getBanner().getOg();
                    }


                    imageEndPoint.add(new GameDetails(id, name, imageURL));
                }
                mBannerGames.postValue(imageEndPoint);
            }

            @Override
            public void onFailure(ResponseBody errorBody) {

            }

            @Override
            public void onThrowable(String message) {

            }
        });
    }
    public LiveData<List<GameDetails>> getBanner() {
        return mBannerGames;
    }
}