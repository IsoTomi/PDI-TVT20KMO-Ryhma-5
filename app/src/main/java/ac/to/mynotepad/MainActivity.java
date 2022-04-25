package ac.to.mynotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//Firebase
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        Button buttonShow = findViewById(R.id.ShowBtn);
        final EditText editNotes = findViewById(R.id.editNotes);

        //Creating new note
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
    }

    public void OpenActivity() {
        Intent intent = new Intent(this, AllMyNotes.class);
        startActivity(intent);
    }
}

