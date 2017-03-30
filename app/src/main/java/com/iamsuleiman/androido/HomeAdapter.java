package com.iamsuleiman.androido;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suleiman on 28/03/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.SampleViewHolder> {
    private static final String TAG = HomeAdapter.class.getSimpleName();

    private Context context;
    private List<Demo> demos;

    public HomeAdapter(Context context) {
        this.context = context;

        // populate demos
        String[] titlesArray = context.getResources().getStringArray(R.array.demo_titles);
        String[] descriptionsArray = context.getResources().getStringArray(R.array.demo_descriptions);

        demos = new ArrayList<>(titlesArray.length);

        for (int i = 0; i < titlesArray.length; i++) {
            Demo demo = new Demo(titlesArray[i], descriptionsArray[i]);
            demos.add(demo);
        }
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View regularView = inflater.inflate(R.layout.item_demo, parent, false);
        return new SampleViewHolder(regularView);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {

        Demo demo = demos.get(position);

        holder.mTextIndex.setText(String.valueOf(position + 1));
        holder.mTextTitle.setText(demo.getTitle());
        holder.mTextDescription.setText(demo.getDescription());
    }

    @Override
    public int getItemCount() {
        return demos != null ? demos.size() : 0;
    }

    protected class SampleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextIndex, mTextTitle, mTextDescription;

        public SampleViewHolder(View itemView) {
            super(itemView);

            mTextIndex = itemView.findViewById(R.id.demo_index);
            mTextTitle = itemView.findViewById(R.id.demo_title);
            mTextDescription = itemView.findViewById(R.id.demo_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = null;

                    switch (getAdapterPosition()) {
                        case 0:
                            intent = new Intent(context, PipActivity.class);
                            break;
                        default:
                            Log.w(TAG, "onClick: index not found");
                    }

                    if (intent != null) context.startActivity(intent);
                }
            });
        }
    }
}
