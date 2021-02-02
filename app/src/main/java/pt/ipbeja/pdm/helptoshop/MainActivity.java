package pt.ipbeja.pdm.helptoshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pt.ipbeja.pdm.helptoshop.db.AppDatabase;
import pt.ipbeja.pdm.helptoshop.db.entities.Post;

public class MainActivity extends AppCompatActivity {

    private List<Post> posts;


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.posts = AppDatabase.getInstance(getApplicationContext()).postDao().getAll();

        PostAdapter adapter = new PostAdapter(posts);
        RecyclerView view = findViewById(R.id.main_recycler_view);
        view.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        view.setLayoutManager(llm);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void createPost(View view) {
        CreatePostActivity.start(this);
    }

    public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> implements Filterable {

        List<Post> postList = new ArrayList<>();
        List<Post> postListFull;


        public PostAdapter(List<Post> list) {
            this.postList.addAll(list);
            this.postListFull = new ArrayList<>(postList);
        }

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            Post post = postList.get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }

        @Override
        public Filter getFilter() {
            return searchFilter;
        }

        private final Filter searchFilter = new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Post> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(postListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Post post : postListFull) {
                        if (post.getPostTitle().toLowerCase().contains(filterPattern) || post.getPostCreator().toLowerCase().contains(filterPattern)) {
                            filteredList.add(post);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                postList.clear();
                postList.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    private class PostViewHolder extends RecyclerView.ViewHolder{

        private Post post;
        private TextView title, creator, date, state;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                MapsActivity.start(MainActivity.this, post.getId(), 0);
            });
            title = itemView.findViewById(R.id.post_title);
            creator = itemView.findViewById(R.id.post_author);
            date = itemView.findViewById(R.id.post_date);
            state = itemView.findViewById(R.id.post_state);
        }

        public void bind(Post post){
            this.post = post;
            title.setText(post.getPostTitle());
            creator.setText(post.getPostCreator());
            date.setText(post.getPostCreateDate());
            state.setText(post.getPostState());
        }
    }
}