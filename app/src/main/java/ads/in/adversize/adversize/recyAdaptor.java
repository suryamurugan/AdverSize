package ads.in.adversize.adversize;

/**
 * Created by suryamurugan on 29/3/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ads.in.adversize.adversize.model.MediaObject;

public class recyAdaptor extends RecyclerView.Adapter<recyAdaptor.MyViewHolder> {

    private List<MediaObject> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            //genre = (TextView) view.findViewById(R.id.gen);
            //year = (TextView) view.findViewById(R.id.y);
        }
    }


    public recyAdaptor(List<MediaObject> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlecard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MediaObject movie = moviesList.get(position);
        holder.title.setText(movie.getMediaName());
       // holder.genre.setText(movie.getMediaRating());
       // holder.year.setText(movie.getMediaTotalPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
