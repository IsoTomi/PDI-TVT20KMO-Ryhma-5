package ac.to.mynotepad;

public class Model {
    public Model() {
    }

    public Model(String notesText) {
        NotesText = notesText;
    }

    public String getNotesText() {
        return NotesText;
    }

    public void setNotesText(String notesText) {
        NotesText = notesText;
    }

    String NotesText;
}
