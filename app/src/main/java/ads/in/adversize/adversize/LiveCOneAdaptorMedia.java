package ads.in.adversize.adversize;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ads.in.adversize.adversize.model.MediaObject;

/**
 * Created by suryamurugan on 4/2/18.
 */

public class LiveCOneAdaptorMedia extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<MediaObject> data= Collections.emptyList();
    MediaObject current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public LiveCOneAdaptorMedia(Context context, List<MediaObject> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.livec_one_card, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final MediaObject media= data.get(position);

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;

        ////////////////////////
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Recycle Click" + position, Toast.LENGTH_SHORT).show();

               // Toast.makeText(context,"yo"+myHolder.getItemId(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(context, ""+media.getMediaID()
                  //       , Toast.LENGTH_SHORT).show();

//#                Intent intent = new Intent(context,LiveTwoActivity.class);
               /* intent.putExtra("class", (Serializable) data);
                //context.startActivity(intent);
*/
//#                Gson gson = new Gson();
//#                String myJson = gson.toJson(data.get(position
//#                ));
               //Toast.makeText(context, ""+media.getMediaID(), Toast.LENGTH_SHORT).show();
//#               intent.putExtra("myjson", myJson);
//#                context.startActivity(intent);




            }
        });
        /*##################       /////////////////////////////
        MediaObject current=data.get(position);
        myHolder.mediaName.setText(current.getMediaName());
 #       myHolder.size.setText(current.getMediaWidth()+" * "+current.getMediaHeight());
  */      //myHolder.mediaPrice.setText(current.mediaTotalPrice);

        //myHolder.mediaRating.setRating(Float.parseFloat(current.mediaRating));
        MediaObject current=data.get(position);
        myHolder.mediaName.setText(current.getMediaName());

        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());



        myHolder.size.setText(""+current.getMediaWidth()+" * "+current.getMediaHeight()+" FT");
     //   myHolder.avSwitch.setChecked(true);



        //myHolder.textType.setText("Category: " + current.catName);
        //myHolder.textPrice.setText("Rs. " + current.price + "\\Kg");
        //myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        // load image into imageview using glide
        //Toast.makeText(context, ""+media.getMediaImgLocation(), Toast.LENGTH_SHORT).show();
       // Glide.with(context).load("https://adversize.in/" +current.getMediaImgLocation()).into(myHolder.img);
 //#       Glide.with(context).load("http://suryamurugan.co.nf/" +current.getMediaImgLocation()).into(myHolder.img);
        //        .placeholder(R.drawable.ic_img_error)
          //      .error(R.drawable.ic_img_error)
            //    .into(myHolder.ivFish);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView mediaName,size;
        ImageView img;
        ProgressBar progressBar;
        TextView mediaPrice;
        RatingBar mediaRating;
        public View view;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);

           // progressBar= itemView.findViewById(R.id.pro);
            //progressBar.setProgress(60);

            context= itemView.getContext();
            mediaName= itemView.findViewById(R.id.landmark);
            img= itemView.findViewById(R.id.thumbnail);
            size = itemView.findViewById(R.id.size);

         //   mediaPrice = (TextView) itemView.findViewById(R.id.count);
           // mediaRating = itemView.findViewById(R.id.ratingBar);

            //img.setImageResource(R.drawable.l);

        }

    }
}
