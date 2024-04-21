package ph.com.carlesparrago.gamingApp.ui.hallOfFame;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HallOfFameViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HallOfFameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Hall of Fame fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}