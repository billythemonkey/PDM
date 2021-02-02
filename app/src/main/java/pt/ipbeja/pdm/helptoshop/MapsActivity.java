package pt.ipbeja.pdm.helptoshop;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import pt.ipbeja.pdm.helptoshop.db.AppDatabase;
import pt.ipbeja.pdm.helptoshop.db.dao.PostDao;
import pt.ipbeja.pdm.helptoshop.db.entities.Post;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String POST_ID = "postId";
    private static final String ACTIVITY_CONTEXT = "activityContext";
    private Post post;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        this.post = AppDatabase.getInstance(getApplicationContext()).postDao().getPost(getIntent().getExtras().getLong(POST_ID));

        LatLng location = new LatLng(post.getLat(), post.getLgtd());
        mMap.addMarker(new MarkerOptions().position(location).title(post.getPostTitle()).snippet(post.getPostCreateDate() + " " + post.getPostCreator()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f));



    }

    public static void start(Context context, long id, int activityContext) {
        Intent starter = new Intent(context, MapsActivity.class);
        starter.putExtra(POST_ID, id);
        starter.putExtra(ACTIVITY_CONTEXT, activityContext);
        context.startActivity(starter);
    }

    public static void start(Context context, int activityContext) {
        Intent starter = new Intent(context, MapsActivity.class);
        starter.putExtra(ACTIVITY_CONTEXT, activityContext);
        context.startActivity(starter);
    }
}