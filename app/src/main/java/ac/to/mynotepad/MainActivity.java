package ac.to.mynotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Button buttonSend = findViewById(R.id.SendBtn);
        Button button3 = findViewById(R.id.CreateNew);
        final EditText editNotes = findViewById(R.id.editNotes);

        //Creating new note
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = editNotes.getText().toString();
                Log.d("TOMI", text);

                if (!text.matches("")) {
                    Information inf = new Information(text);
                    database.add(inf);
                }

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

