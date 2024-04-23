package ph.com.carlesparrago.gamingApp.ui.game;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ph.com.carlesparrago.gamingApp.APIs.constant.APIConstants;
import ph.com.carlesparrago.gamingApp.APIs.models.Platform;
import ph.com.carlesparrago.gamingApp.R;
import ph.com.carlesparrago.gamingApp.databinding.FragmentGameBinding;
import ph.com.carlesparrago.gamingApp.databinding.FragmentGamesBinding;
import ph.com.carlesparrago.gamingApp.models.GameDetails;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        binding = FragmentGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView ivBanner = binding.ivBanner;
        TextView tvTitle = binding.tvTitle;
        TextView tvReleaseDate = binding.tvReleaseDate;
        TextView tvCriticScore = binding.tvTopCriticScore;
        TextView tvPlatforms = binding.tvPlatforms;
        ShimmerFrameLayout shimmer = binding.shimmer;
        ConstraintLayout clGameDetails = binding.clGameDetails;
        TextView tvDescription = binding.tvDescription;

        Bundle bundle = this.getArguments();
        if(bundle != null){
            int id = bundle.getInt("id");
            getGame(id, ivBanner, tvTitle, tvReleaseDate, tvCriticScore, tvPlatforms, shimmer, clGameDetails, tvDescription);
        }

        return root;
    }

    private void getGame(int id, ImageView ivBanner, TextView tvTitle, TextView tvReleaseDate, TextView tvTopCriticScore, TextView tvPlatforms, ShimmerFrameLayout shimmer, ConstraintLayout clGameDetails, TextView tvDescription){
        shimmer.startShimmer();
        gameViewModel.getGame(id);
        gameViewModel.getGameDetails().observe(getViewLifecycleOwner(), gameDetails -> {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String releaseDate = gameDetails.getReleaseDate();
            String formattedReleaseDate;
            try {
                Date date = inputDateFormat.parse(releaseDate);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMMM d, yyyy");
                formattedReleaseDate = outputDateFormat.format(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            String topCriticScore = String.valueOf(Math.round(gameDetails.getTopCriticScore()));
            StringBuilder platforms = new StringBuilder();

            for(Platform platform : gameDetails.getPlatformList()){
                platforms.append(platform.getShortName()).append(",");
            }
            // Remove the trailing comma if needed
            if (platforms.length() > 0 && platforms.charAt(platforms.length() - 1) == ',') {
                platforms.deleteCharAt(platforms.length() - 1);
            }

            Picasso.get().load(gameDetails.getImage()).into(ivBanner);

            tvTitle.setText(gameDetails.getName());
            tvReleaseDate.setText(formattedReleaseDate);
            tvTopCriticScore.setText(topCriticScore);
            tvPlatforms.setText(platforms);
            tvDescription.setText(gameDetails.getDescription());

            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
            clGameDetails.setVisibility(View.VISIBLE);
        });
    }
}