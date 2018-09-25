/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package audioapk.com.example.android.farmertofarmer.Processes.YourProcessesFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import audioapk.com.example.android.farmertofarmer.DetailProcess;
import audioapk.com.example.android.farmertofarmer.R;

class ProcessesAdapter extends RecyclerView.Adapter<ProcessesAdapter.WordViewHolder> {

    private final ArrayList<ProcessCard> cardList;
    private final LayoutInflater mInflater;
    private Context context;

    public ProcessesAdapter(Context context, ArrayList<ProcessCard> cardList) {
        mInflater = LayoutInflater.from(context);
        this.cardList = cardList;
        this.context = context;
    }


    @Override
    public ProcessesAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.process_you_processes_card, parent, false);
        return new WordViewHolder(mItemView, this);
    }


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView dateText,titleText;
        private ProcessesAdapter processesAdapter;

        WordViewHolder(View itemView, ProcessesAdapter processesAdapter) {
            super(itemView);
            dateText = itemView.findViewById(R.id.process_card_date);
            titleText = itemView.findViewById(R.id.process_card_title);
            this.processesAdapter = processesAdapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();

            ProcessCard element = cardList.get(mPosition);
            Intent detailIntent = new Intent(context, DetailProcess.class);
            detailIntent.putExtra("title", element.getTitle());
            detailIntent.putExtra("description",element.getDescription());
            detailIntent.putExtra("date",element.getDate());


            context.startActivity(detailIntent);

        }

        private void bind(ProcessCard currentCard) {
            titleText.setText(currentCard.getTitle());
            dateText.setText(currentCard.getDate());
        }
    }


    @Override
    public void onBindViewHolder(ProcessesAdapter.WordViewHolder holder, int position) {

        ProcessCard currentCard = cardList.get(position);
        holder.bind(currentCard);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


}
