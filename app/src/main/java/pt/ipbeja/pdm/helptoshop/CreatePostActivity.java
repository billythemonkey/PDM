package pt.ipbeja.pdm.helptoshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pt.ipbeja.pdm.helptoshop.db.AppDatabase;
import pt.ipbeja.pdm.helptoshop.db.entities.Post;

public class CreatePostActivity extends AppCompatActivity implements OnMapReadyCallback {


    private EditText name, contact, title, shoppingList;
    private Post post;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        name = findViewById(R.id.edit_name);
        contact = findViewById(R.id.edit_contact);
        title = findViewById(R.id.edit_title);
        shoppingList = findViewById(R.id.edit_shopping_list);
        

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnMapClickListener(latLng -> {
            marker.remove();
            this.marker = googleMap.addMarker(new MarkerOptions().position(latLng));
            marker.isDraggable();
        });
        
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CreatePostActivity.class);
        context.startActivity(starter);
    }

    public void completeCreatePost(View view) {

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        this.post = new Post(title.getText().toString(),name.getText().toString(),
                shoppingList.getText().toString(),date,
                Long.parseLong(contact.getText().toString()),
                marker.getPosition().latitude, marker.getPosition().longitude);

        AppDatabase.getInstance(this).postDao().insert(post);
        MainActivity.start(this);

    }


}