package ac.to.mynotepad;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoteModel extends ViewModel {
    MutableLiveData<Note> mutableLiveData = new MutableLiveData<>();

    public void setNote(Note note) {
        mutableLiveData.setValue(note);
    }

    public MutableLiveData<Note> getNote() {
        return mutableLiveData;
    }
}
