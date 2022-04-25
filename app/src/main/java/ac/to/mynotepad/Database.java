package ac.to.mynotepad;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Database
{
    private DatabaseReference databaseReference;
    public Database(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Information.class.getSimpleName());
    }
    public Task<Void> add(Information inf){
        return databaseReference.push().setValue(inf);
    }
}
