package ac.to.mynotepad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapter extends FirebaseRecyclerAdapter<Model, MyAdapter.myViewholder>
{
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull Model model) {
    holder.tv.setText(model.getNotesText());
    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent, false);
        return  new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{
        TextView tv;
        public myViewholder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.SetTextHere);
        }
    }
}