package ph.com.carlesparrago.gamingApp.ui.game;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.models.Banner;
import ph.com.carlesparrago.gamingApp.APIs.models.Box;
import ph.com.carlesparrago.gamingApp.APIs.models.Images;
import ph.com.carlesparrago.gamingApp.APIs.repositories.GameRepository;
import ph.com.carlesparrago.gamingApp.models.GameDetails;

public class GameViewModel extends ViewModel {

    GameRepository gameRepository;
    MutableLiveData<GameDetails> mGameDetail;
    public GameViewModel() {
        gameRepository = new GameRepository();
        mGameDetail = new MutableLiveData<>();
    }

    public void getGame(int id){
       gameRepository.getGame(id, new GameRepository.IGame() {
           @Override
           public void onResponse(GameResponse gameResponse) {
               Banner banner = gameResponse.getImages().getBanner();
               Box box = gameResponse.getImages().getBox();
               String imageURL;
               Images images;
               if(banner == null){
                   images = new Images(box);
                   imageURL = APIConstants.imageUrl + images.getBox().getOg();
                   Log.d("Box", imageURL);
               }else{
                   images = new Images(banner);
                   imageURL = APIConstants.imageUrl + images.getBanner().getOg();
                   Log.d("Banner", imageURL);
               }
               GameDetails gameDetails = new GameDetails(gameResponse.getName(), imageURL, gameResponse.getFirstReleaseDate(), gameResponse.getTopCriticScore(), gameResponse.getPlatforms(), gameResponse.getDescription());
               mGameDetail.postValue(gameDetails);
           }

           @Override
           public void onFailure(ResponseBody errorBody) {

           }

           @Override
           public void onThrowable(String message) {

           }
       });
    }

    public LiveData<GameDetails> getGameDetails(){
        return mGameDetail;
    }
}