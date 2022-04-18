package ac.to.mynotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//Firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String uid = "-N-mnJWFEX7V0ojMBaqY";

    private FirebaseDatabase rootnote;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Database conf
        Database database = new Database();
        reference = FirebaseDatabase.getInstance().getReference("Notes");
        //Buttons and widgets
        Button button1 = findViewById(R.id.SendBtn);
        Button button2 = findViewById(R.id.ShowBtn);
        Button button3 = findViewById(R.id.CreateNew);
        final TextView textView = findViewById(R.id.show);
        final EditText editNotes = findViewById(R.id.editNotes);

        //Timestamp
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        //Creating new note!
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Information inf = new Information(editNotes.getText().toString());
                database.add(inf);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference("Information");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        String note = datasnapshot.child(uid).getValue().toString();
                        //String noteid = datasnapshot.child("noteId").getValue().toString();
                        textView.setText(note);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            OpenActivity();
            }
        });
    }
    public void OpenActivity() {
        Intent intent = new Intent(this, AllMyNotes.class);
        startActivity(intent);
    }
}

