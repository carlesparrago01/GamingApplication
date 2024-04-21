package ph.com.carlesparrago.gamingApp.adapter.games;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ph.com.carlesparrago.gamingApp.R;

public class GamesRecycleViewAdapter extends RecyclerView.Adapter<GamesViewHolder> {



    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_games, parent, false);
        return new GamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
