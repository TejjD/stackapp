package jhb.dvt.co.za.stackapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jhb.dvt.co.za.stackapp.R;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mTags;

    TagsAdapter(Context context, List<String> tags) {
        mContext = context;
        mTags = tags;
    }

    @Override
    public TagsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tags_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TagsAdapter.ViewHolder holder, int position) {
        holder.tagText.setText(mTags.get(position));
    }


    @Override
    public int getItemCount() {
        return mTags.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tagText;

        ViewHolder(View itemView) {
            super(itemView);

            tagText = itemView.findViewById(R.id.tagText);
        }
    }
}
