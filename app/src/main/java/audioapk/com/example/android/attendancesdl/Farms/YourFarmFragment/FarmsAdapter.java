

package audioapk.com.example.android.attendancesdl.Farms.YourFarmFragment;

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

import audioapk.com.example.android.attendancesdl.Processes.ProcessesMain;
import audioapk.com.example.android.attendancesdl.R;

class FarmsAdapter extends RecyclerView.Adapter<FarmsAdapter.ViewHolder>  {

    private ArrayList<FarmCard> mFarmsData;
    private Context mContext;

    FarmsAdapter(Context context, ArrayList<FarmCard> farmsData) {
        this.mFarmsData = farmsData;
        this.mContext = context;
    }


    @Override
    public FarmsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.farm_you_farms_card, parent, false));
    }

    @Override
    public void onBindViewHolder(FarmsAdapter.ViewHolder holder,
                                 int position) {
        FarmCard currentFarmCard = mFarmsData.get(position);

        holder.bindTo(currentFarmCard);
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

            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            itemView.setOnClickListener(this);
        }

        void bindTo(FarmCard currentFarmCard){
            mTitleText.setText(currentFarmCard.getTitle());
            mInfoText.setText(currentFarmCard.getInfo());

            Glide.with(mContext).load(
                    currentFarmCard.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            FarmCard currentFarmCard = mFarmsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ProcessesMain.class);
            detailIntent.putExtra("title", currentFarmCard.getTitle());
            detailIntent.putExtra("image_resource",
                    currentFarmCard.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }

}
