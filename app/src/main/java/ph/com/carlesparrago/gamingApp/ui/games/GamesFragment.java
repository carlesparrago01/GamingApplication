package ph.com.carlesparrago.gamingApp.ui.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import ph.com.carlesparrago.gamingApp.adapter.games.GamesRecycleViewAdapter;
import ph.com.carlesparrago.gamingApp.databinding.FragmentGamesBinding;

public class GamesFragment extends Fragment {

    private FragmentGamesBinding binding;
    GamesRecycleViewAdapter gamesRecycleViewAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GamesViewModel gamesViewModel = new ViewModelProvider(this).get(GamesViewModel.class);

        binding = FragmentGamesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rvListOfGames = binding.rvListOfGames;
        final ShimmerFrameLayout shimmerFrameLayout = binding.shimmer;
        final SwipeRefreshLayout swipeRefresh = binding.swipeRefresh;

        getGames(gamesViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh);

        swipeRefresh.setOnRefreshListener(() -> getGames(gamesViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh));

        return root;
    }

    private void getGames(GamesViewModel gamesViewModel, RecyclerView rvGames, ShimmerFrameLayout shimmer, SwipeRefreshLayout swipeRefresh) {
        swipeRefresh.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);

        shimmer.startShimmer();
        gamesViewModel.getGames();
        gamesViewModel.getBanner().observe(getViewLifecycleOwner(), gameDetails -> {
            gamesRecycleViewAdapter = new GamesRecycleViewAdapter(requireActivity(), gameDetails);
            rvGames.setHasFixedSize(false);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rvGames.setLayoutManager(linearLayoutManager);
            rvGames.setNestedScrollingEnabled(false);
            rvGames.setAdapter(gamesRecycleViewAdapter);
            shimmer.setVisibility(View.GONE);
            swipeRefresh.setVisibility(View.VISIBLE);
            swipeRefresh.setRefreshing(false);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}