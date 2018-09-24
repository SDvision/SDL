

package audioapk.com.example.android.farmertofarmer.Farms.YourFarmFragment;

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

class FarmsAdapter extends RecyclerView.Adapter<FarmsAdapter.ViewHolder>  {

    private ArrayList<FarmCard> mFarmsData;
    private Context mContext;

    FarmsAdapter(Context context, ArrayList<FarmCard> farmsData) {
        this.mFarmsData = farmsData;
        this.mContext = context;
    }


    @Override
    public FarmsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.farm_you_farms_card, parent, false));
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

        private TextView farmTitle;
        private ImageView farmImage;
        private TextView landEdit,noProcess,dateEdit;

        ViewHolder(View itemView) {
            super(itemView);

            farmTitle = itemView.findViewById(R.id.farm_card_title);
            farmImage = itemView.findViewById(R.id.farm_card_image);
            landEdit = itemView.findViewById(R.id.farm_card_land);
            noProcess = itemView.findViewById(R.id.farm_card_no_process);
            dateEdit = itemView.findViewById(R.id.farm_card_date);


            itemView.setOnClickListener(this);
        }

        void bindTo(FarmCard currentFarmCard){
            farmTitle.setText(currentFarmCard.getTitle());
            landEdit.setText(String.valueOf(currentFarmCard.getLandArea()));
            noProcess.setText("0");

            String day_string = Integer.toString(currentFarmCard.getDay()[2]);              //day
            String month_string = Integer.toString(currentFarmCard.getDay()[1] + 1);     //month
            String year_string = Integer.toString(currentFarmCard.getDay()[0]);             //year
            String dateMessage = (month_string +
                    "/" + day_string +
                    "/" + year_string);

            dateEdit.setText(dateMessage);

            Glide.with(mContext).load(currentFarmCard.getImageResource()).into(farmImage);

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
