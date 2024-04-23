package ph.com.carlesparrago.gamingApp.ui.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import ph.com.carlesparrago.gamingApp.adapter.games.GamesRecycleViewAdapter;
import ph.com.carlesparrago.gamingApp.databinding.FragmentUpcomingBinding;
import ph.com.carlesparrago.gamingApp.ui.hallOfFame.HallOfFameViewModel;

public class UpcomingFragment extends Fragment {

    private FragmentUpcomingBinding binding;
    private GamesRecycleViewAdapter gamesRecycleViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UpcomingViewModel upcomingViewModel =
                new ViewModelProvider(this).get(UpcomingViewModel.class);

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView rvListOfGames = binding.rvListOfGames;
        final ShimmerFrameLayout shimmerFrameLayout = binding.shimmer;
        final SwipeRefreshLayout swipeRefresh = binding.swipeRefresh;

        getGames(upcomingViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh);

        swipeRefresh.setOnRefreshListener(() -> getGames(upcomingViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh));

        return root;
    }

    private void getGames(UpcomingViewModel upcomingViewModel, RecyclerView rvGames, ShimmerFrameLayout shimmer, SwipeRefreshLayout swipeRefresh) {
        swipeRefresh.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        upcomingViewModel.gameUpcoming();
        upcomingViewModel.getBanner().observe(getViewLifecycleOwner(), gameDetails -> {
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