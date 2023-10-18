package ca.mohawk.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.mohawk.foodrecipeapp.Listeners.RecipeClickListener;
import ca.mohawk.foodrecipeapp.Models.Recipe;
import ca.mohawk.foodrecipeapp.R;

public class RandomReciepeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;


    public RandomReciepeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
           holder.textView_title.setText(list.get(position).title);
           holder.textView_title.setSelected(true);
           holder.textView_Likes.setText(list.get(position).aggregateLikes+" Likes");
           holder.textView_servings.setText(list.get(position).servings+" servings");
           holder.textView_time.setText(list.get(position).readyInMinutes+" Minutes");
           picasso.get().load(list.get(position).image).into(holder.image);

           holder.random_list_container.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
               }
           });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class  RandomRecipeViewHolder extends RecyclerView.ViewHolder{
    CardView random_list_container;
    TextView textView_title,textView_servings,textView_Likes,textView_time;
    ImageView image;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_Likes = itemView.findViewById(R.id.textView_Likes);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_time = itemView.findViewById(R.id.textView_time);
        image = itemView.findViewById(R.id. image);
    }
}
