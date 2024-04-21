package ph.com.carlesparrago.gamingApp.ui.games;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import okhttp3.ResponseBody;
import ph.com.carlesparrago.gamingApp.APIs.POJO.response.GameResponse;
import ph.com.carlesparrago.gamingApp.APIs.repositories.GameRepository;

public class GamesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private GameRepository gameRepository;

    public GamesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Games fragment");
        gameRepository = new GameRepository();
    }

    public void getGames(){
        gameRepository.getGames(new GameRepository.IGames() {
            @Override
            public void onResponse(GameResponse gameResponse) {

            }

            @Override
            public void onFailure(ResponseBody errorBody) {

            }

            @Override
            public void onThrowable(String message) {

            }
        });
    }
    public LiveData<String> getText() {
        return mText;
    }
}