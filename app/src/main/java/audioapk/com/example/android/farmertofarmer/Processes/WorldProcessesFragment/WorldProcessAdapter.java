package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment.ProcessYouList;
import audioapk.com.example.android.farmertofarmer.R;

import static audioapk.com.example.android.farmertofarmer.Processes.FarmWorldDatabase.NOT_FOUND;

class WorldProcessAdapter extends RecyclerView.Adapter<WorldProcessAdapter.WordViewHolder> {

        private final ArrayList<WorldProcessCard> cardList;
        private final Context context;

        private String title;
        private int img;

        class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final TextView profitText;
            private final TextView dateText;

            WordViewHolder(View itemView) {
                super(itemView);
                profitText = itemView.findViewById(R.id.process_card_title);
                dateText = itemView.findViewById(R.id.process_card_date);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {

                WorldProcessCard element = cardList.get(getLayoutPosition());

                ProcessYouList processYouList = new ProcessYouList();
                processYouList.setValues(title,element.getLand(),element.getDate(),img,NOT_FOUND);
                processYouList.setNotMyFarm(element.getProcessDB());
                final FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.world_root_frame, processYouList);
                ft.addToBackStack(null);
                ft.commit();


            }

            void bind(WorldProcessCard worldProcessCard) {
                double profitPerLand = worldProcessCard.getProfit()/Double.parseDouble(worldProcessCard.getLand());
                String ppl = new DecimalFormat("0.00##").format(profitPerLand);
                profitText.setText(ppl);
                dateText.setText(worldProcessCard.getDate());
            }
        }

        WorldProcessAdapter(Context context, ArrayList<WorldProcessCard> wordList, String title, int img) {
            this.context = context;
            this.cardList = wordList;
            this.title = title;
            this.img = img;
        }

        @Override
        public WorldProcessAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                                              int viewType) {
            View mItemView = LayoutInflater.from(context).inflate(R.layout.process_you_processes_card, parent, false);
            return new WordViewHolder(mItemView);
        }

        @Override
        public void onBindViewHolder(WordViewHolder holder, int position) {


            holder.bind(cardList.get(position));
        }

        @Override
        public int getItemCount() {
            return cardList.size();
        }


    }