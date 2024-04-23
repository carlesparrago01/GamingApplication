package ph.com.carlesparrago.gamingApp.adapter.games;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ph.com.carlesparrago.gamingApp.R;

public class GamesViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivBanner;
    public GamesViewHolder(@NonNull View itemView) {
        super(itemView);
        ivBanner = itemView.findViewById(R.id.iv_banner);
    }
}
