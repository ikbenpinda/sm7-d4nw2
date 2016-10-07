package nl.achan.apps.sbb_spelgids.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.achan.apps.sbb_spelgids.R;

/**
 * Created by fhict on 07/10/16.
 */
public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder> {

    List<Team> teams;
    Context context;

    public LeaderboardAdapter(List<Team> teams, Context context) {
        this.teams = teams;
        this.context = context;
    }

    @Override
    public LeaderboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leaderboard, parent, false);
        LeaderboardViewHolder holder = new LeaderboardViewHolder(view, context);
        return holder;
    }

    @Override
    public void onBindViewHolder(LeaderboardViewHolder holder, int position) {
        Team team = teams.get(position);
        holder.position.setText("" + (position + 1));
        holder.teamname.setText(team.name);
        holder.teamscore.setText("" + team.score + " / 12");
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder{

        public @BindView(R.id.tv_leaderboard_position) TextView position;
        public @BindView(R.id.tv_team_name) TextView teamname;
        public @BindView(R.id.tv_score) TextView teamscore;

        Context context;

        public LeaderboardViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            ButterKnife.bind(this, itemView);
        }
    }
}
