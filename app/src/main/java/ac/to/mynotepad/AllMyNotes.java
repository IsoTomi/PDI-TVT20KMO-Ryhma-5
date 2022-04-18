package ac.to.mynotepad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllMyNotes extends AppCompatActivity {
RecyclerView recyclerView;
MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_my_notes);

        recyclerView = (RecyclerView)findViewById(R.id.RecyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(FirebaseDatabase.getInstance().getReference().child("Information"), Model.class).build();
        myAdapter=new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);
    }
    @Override
    protected void onStart(){
        super.onStart();
        myAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        myAdapter.stopListening();
    }
}
