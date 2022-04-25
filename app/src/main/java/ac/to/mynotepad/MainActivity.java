package ac.to.mynotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database conf
        Database database = new Database();
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Information");

        //DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Information");

        // Buttons and widgets
        Button buttonSend = findViewById(R.id.SendBtn);
        Button buttonShow = findViewById(R.id.ShowBtn);
        EditText editNotes = findViewById(R.id.editNotes);

        RecyclerView noteRecycler = findViewById(R.id.NoteRecycler);;
        noteRecycler.setHasFixedSize(true);
        noteRecycler.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Note> list = new ArrayList();
        MyAdapter myAdapter = new MyAdapter(this, list);
        noteRecycler.setAdapter(myAdapter);

        // Creating a new note
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
                    list.add(note);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void OpenActivity() {
        Intent intent = new Intent(this, AllMyNotes.class);
        startActivity(intent);
    }
}

