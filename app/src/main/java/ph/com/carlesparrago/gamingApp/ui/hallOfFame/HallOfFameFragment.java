package ph.com.carlesparrago.gamingApp.ui.hallOfFame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ph.com.carlesparrago.gamingApp.databinding.FragmentHallOfFameBinding;

public class HallOfFameFragment extends Fragment {

    private FragmentHallOfFameBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HallOfFameViewModel galleryViewModel =
                new ViewModelProvider(this).get(HallOfFameViewModel.class);

        binding = FragmentHallOfFameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}