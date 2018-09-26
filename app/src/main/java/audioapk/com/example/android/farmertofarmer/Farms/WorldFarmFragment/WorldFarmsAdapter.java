
package audioapk.com.example.android.farmertofarmer.Farms.WorldFarmFragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment.WorldProcess;
import audioapk.com.example.android.farmertofarmer.R;

class WorldFarmsAdapter extends RecyclerView.Adapter<WorldFarmsAdapter.ViewHolder>  {

    private ArrayList<WorldFarmCard> mFarmsData;
    private Context mContext;

    WorldFarmsAdapter(Context context, ArrayList<WorldFarmCard> farmsData) {
        this.mFarmsData = farmsData;
        this.mContext = context;
    }


    @Override
    public WorldFarmsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.farm_world_card, parent, false));
    }

    @Override
    public void onBindViewHolder(WorldFarmsAdapter.ViewHolder holder, int position) {
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
        private ImageView mSportsImage;

        ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.farm_card_title);
            mSportsImage = itemView.findViewById(R.id.farm_card_image);

            itemView.setOnClickListener(this);
        }

        void bindTo(WorldFarmCard currentWorldFarmCard){
            mTitleText.setText(currentWorldFarmCard.getTitle());

            Glide.with(mContext).load(
                    currentWorldFarmCard.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            WorldFarmCard currentWorldFarmCard = mFarmsData.get(getAdapterPosition());
//            Intent detailIntent = new Intent(mContext, ProcessesMain.class);
//            detailIntent.putExtra("title", currentWorldFarmCard.getTitle());
//            detailIntent.putExtra("image_resource",
//                    currentWorldFarmCard.getImageResource());
//            mContext.startActivity(detailIntent);

//            WorldProcess nextFrag= new WorldProcess();
//            ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.pager, nextFrag,"world farms")
//                    .addToBackStack(null)
//                    .commit();

            WorldProcess worldProcess = new WorldProcess();
            worldProcess.setCardValues(currentWorldFarmCard.getTitle(),currentWorldFarmCard.getImageResource());
            final FragmentTransaction ft = ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.world_root_frame, worldProcess);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

}
