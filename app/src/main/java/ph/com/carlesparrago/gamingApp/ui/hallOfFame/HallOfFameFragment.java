package ph.com.carlesparrago.gamingApp.ui.hallOfFame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;

import ph.com.carlesparrago.gamingApp.adapter.games.GamesRecycleViewAdapter;
import ph.com.carlesparrago.gamingApp.databinding.FragmentHallOfFameBinding;

public class HallOfFameFragment extends Fragment {

    private FragmentHallOfFameBinding binding;
    private GamesRecycleViewAdapter gamesRecycleViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HallOfFameViewModel hallOfFameViewModel =
                new ViewModelProvider(this).get(HallOfFameViewModel.class);

        binding = FragmentHallOfFameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final RecyclerView rvListOfGames = binding.rvListOfGames;
        final ShimmerFrameLayout shimmerFrameLayout = binding.shimmer;
        final SwipeRefreshLayout swipeRefresh = binding.swipeRefresh;

        getGames(hallOfFameViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh);

        swipeRefresh.setOnRefreshListener(() -> getGames(hallOfFameViewModel, rvListOfGames, shimmerFrameLayout, swipeRefresh));


        return root;
    }

    private void getGames(HallOfFameViewModel hallOfFameViewModel, RecyclerView rvGames, ShimmerFrameLayout shimmer, SwipeRefreshLayout swipeRefresh) {
        swipeRefresh.setVisibility(View.GONE);
        shimmer.setVisibility(View.VISIBLE);
        shimmer.startShimmer();
        hallOfFameViewModel.getHallOfFame();
        hallOfFameViewModel.getBanner().observe(getViewLifecycleOwner(), gameDetails -> {
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