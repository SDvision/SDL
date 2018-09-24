
package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.Processes.ProcessesMain;
import audioapk.com.example.android.farmertofarmer.R;

class WorldFarmsAdapter extends RecyclerView.Adapter<WorldFarmsAdapter.ViewHolder>  {

    private ArrayList<WorldFarmCard> mFarmsData;
    private Context mContext;

    WorldFarmsAdapter(Context context, ArrayList<WorldFarmCard> farmsData) {
        this.mFarmsData = farmsData;
        this.mContext = context;
    }


    @Override
    public WorldFarmsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.farm_world_card, parent, false));
    }

    @Override
    public void onBindViewHolder(WorldFarmsAdapter.ViewHolder holder,
                                 int position) {
        WorldFarmCard currentWorldFarmCard = mFarmsData.get(position);

        holder.bindTo(currentWorldFarmCard);
    }

    @Override
    public int getItemCount() {
        return mFarmsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.farm_card_title);
            mInfoText = itemView.findViewById(R.id.farm_card_no_process_text);
            mSportsImage = itemView.findViewById(R.id.farm_card_image);

            itemView.setOnClickListener(this);
        }

        void bindTo(WorldFarmCard currentWorldFarmCard){
            mTitleText.setText(currentWorldFarmCard.getTitle());
            mInfoText.setText(currentWorldFarmCard.getInfo());

            Glide.with(mContext).load(
                    currentWorldFarmCard.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            WorldFarmCard currentWorldFarmCard = mFarmsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ProcessesMain.class);
            detailIntent.putExtra("title", currentWorldFarmCard.getTitle());
            detailIntent.putExtra("image_resource",
                    currentWorldFarmCard.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }

}
