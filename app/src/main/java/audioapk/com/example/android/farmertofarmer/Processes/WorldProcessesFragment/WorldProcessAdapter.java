package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.R;

class WorldProcessAdapter extends RecyclerView.Adapter<WorldProcessAdapter.WordViewHolder> {

        private final ArrayList<WorldProcessCard> mWordList;
        private final LayoutInflater mInflater;

        class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private final TextView profitText;
            private final TextView dateText;
            private final WorldProcessAdapter mAdapter;

            WordViewHolder(View itemView, WorldProcessAdapter adapter) {
                super(itemView);
                profitText = itemView.findViewById(R.id.process_card_title);
                dateText = itemView.findViewById(R.id.process_card_date);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int mPosition = getLayoutPosition();

//                String element = mWordList.get(mPosition);
//
//                mWordList.set(mPosition, "Clicked! " + element);
//                mAdapter.notifyDataSetChanged();
            }

            void bind(WorldProcessCard worldProcessCard) {
                double profitPerLand = worldProcessCard.getProfit()/Double.parseDouble(worldProcessCard.getLand());
                String ppl = new DecimalFormat("0.00##").format(profitPerLand);
                profitText.setText(ppl);
                dateText.setText(worldProcessCard.getDate());
            }
        }

        public WorldProcessAdapter(Context context, ArrayList<WorldProcessCard> wordList) {
            mInflater = LayoutInflater.from(context);
            this.mWordList = wordList;
        }

        @Override
        public WorldProcessAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                                                              int viewType) {
            View mItemView = mInflater.inflate(R.layout.process_you_processes_card, parent, false);
            return new WordViewHolder(mItemView, this);
        }

        @Override
        public void onBindViewHolder(WordViewHolder holder, int position) {


            holder.bind(mWordList.get(position));
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }


    }