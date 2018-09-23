package audioapk.com.example.android.farmertofarmer.Processes.WorldProcessesFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import audioapk.com.example.android.farmertofarmer.R;

class WorldProcessAdapter extends RecyclerView.Adapter<WorldProcessAdapter.WordViewHolder> {

        private final LinkedList<String> mWordList;
        private final LayoutInflater mInflater;

        class WordViewHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
            final TextView wordItemView;
            final WorldProcessAdapter mAdapter;

            WordViewHolder(View itemView, WorldProcessAdapter adapter) {
                super(itemView);
                wordItemView = itemView.findViewById(R.id.word);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int mPosition = getLayoutPosition();

                String element = mWordList.get(mPosition);

                mWordList.set(mPosition, "Clicked! " + element);
                mAdapter.notifyDataSetChanged();
            }
        }

        public WorldProcessAdapter(Context context, LinkedList<String> wordList) {
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

            String mCurrent = mWordList.get(position);
            holder.wordItemView.setText(mCurrent);
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }


    }