package ph.com.carlesparrago.gamingApp.ui.upcoming;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpcomingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UpcomingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Upcoming fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}