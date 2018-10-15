package university.u.businesscardcopy;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsRecyclersAdapter extends RecyclerView.Adapter<NewsRecyclersAdapter.ViewHolder> {

    private final List<NewsItem> items;
    private final Context context;
    private final LayoutInflater inflater;

    private static final String KEY_TITLE = "TITLE_KEY";
    private static final String KEY_TEXT = "TEXT_KEY";
    private static final String KEY_DATE = "DATE_KEY";
    private static final String KEY_IMGURL = "IMGURL_KEY";
    private static final String KEY_CATEGORY = "CATEGORY_KEY";

    public NewsRecyclersAdapter(Context context, List<NewsItem> items) {
        this.items = items;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView categoryView;
        public final TextView headerView;
        public final TextView textView;
        public final TextView dateView;
        public final ImageView pictureView;
        Intent intent = new Intent(context, NewsDetailsActivity.class);

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoryView = itemView.findViewById(R.id.news_category);
            headerView = itemView.findViewById(R.id.news_header);
            textView = itemView.findViewById(R.id.preview_text);
            dateView = itemView.findViewById(R.id.published_date);
            pictureView = itemView.findViewById(R.id.news_image);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                intent.putExtra(KEY_TITLE, items.get(position).getTitle());
                intent.putExtra(KEY_DATE, items.get(position).getPublishDate().toString());
                intent.putExtra(KEY_TEXT, items.get(position).getFullText());
                intent.putExtra(KEY_IMGURL, items.get(position).getImageUrl());
                intent.putExtra(KEY_CATEGORY, items.get(position).getCategory().getName());
                context.startActivity(intent);
            }
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new ViewHolder(inflater.inflate(R.layout.news_item, parent, false));
            case 1:
                return new ViewHolder(inflater.inflate(R.layout.news_item_second, parent, false));
        }
        return new ViewHolder(inflater.inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsItem newsItem = items.get(position);

        holder.categoryView.setText(newsItem.getCategory().getName());
        holder.headerView.setText(newsItem.getTitle());
        holder.textView.setText(newsItem.getPreviewText());
        holder.dateView.setText(newsItem.getPublishDate().toString());
        Glide.with(context).load(newsItem.getImageUrl()).into(holder.pictureView);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
       if (items.get(position).getCategory().getId() == 1 || items.get(position).getCategory().getId() == 2) {
           return 0;
        } else {
           return 1;
       }
    }


}
