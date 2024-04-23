package ph.com.carlesparrago.gamingApp.adapter.games;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.service.controls.actions.ControlAction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ph.com.carlesparrago.gamingApp.R;
import ph.com.carlesparrago.gamingApp.models.GameDetails;

public class GamesRecycleViewAdapter extends RecyclerView.Adapter<GamesViewHolder> {

    Activity activity;
    List<GameDetails> gameDetailsList;

    public GamesRecycleViewAdapter(Activity activity, List<GameDetails> gameDetailsList) {
        this.gameDetailsList = gameDetailsList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_games, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {

        GameDetails gameDetails = gameDetailsList.get(position);
        Picasso.get().load(gameDetails.getImage()).into(holder.ivBanner);
        int id = gameDetails.getId();
        holder.ivBanner.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_game, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return gameDetailsList.size();
    }
}
