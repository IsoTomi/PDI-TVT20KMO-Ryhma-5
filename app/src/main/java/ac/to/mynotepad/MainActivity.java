package ac.to.mynotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Firebase
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Firebase related member objects
    private DatabaseReference databaseRef;
    private Database database;

    // Buttons and widgets
    private Button buttonSend;
    private Button buttonShow;
    private EditText editNotes;
    private RecyclerView noteRecycler;

    // Array list for storing note-data
    private ArrayList<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure firebase
        database = new Database();
        databaseRef = FirebaseDatabase.getInstance().getReference("Information");

        // Button Views
        buttonSend = findViewById(R.id.SendBtn);
        buttonShow = findViewById(R.id.ShowBtn);
        editNotes = findViewById(R.id.EditNotes);

        // RecyclerView
        noteRecycler = findViewById(R.id.NoteRecycler);
        noteRecycler.setHasFixedSize(true);
        noteRecycler.setLayoutManager(new LinearLayoutManager(this));

        // ArrayList
        noteList = new ArrayList();

        // Adapter for recycler
        MyAdapter myAdapter = new MyAdapter(this, noteList);
        noteRecycler.setAdapter(myAdapter);

        // Create a new note
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = editNotes.getText().toString();

                if (!text.matches("")) {
                    Information inf = new Information(text);
                    database.add(inf);
                }

            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            OpenActivity();
            }
        });

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Note note = dataSnapshot.getValue(Note.class);
                    noteList.add(note);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemLogOut:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenActivity() {
        Intent intent = new Intent(this, AllMyNotes.class);
        startActivity(intent);
    }
}

