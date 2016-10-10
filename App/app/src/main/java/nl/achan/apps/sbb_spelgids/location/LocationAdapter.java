package nl.achan.apps.sbb_spelgids.location;

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
import nl.achan.apps.sbb_spelgids.models.Level;

/**
 * Created by fhict on 10/10/16.
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>{

    Context context;
    List<Level> levels;

    public LocationAdapter(Context context, List<Level> levels) {
        this.context = context;
        this.levels = levels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_location, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.location.setText(levels.get(holder.getAdapterPosition()).toString());
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public @BindView(R.id.tv_locationstr) TextView location;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
