package dk.anderswind.notebook;


import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter noteAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        notes = new ArrayList();
        notes.add(new Note("Note title 1","Body of our note Personal", Note.Category.PERSONAL));
        notes.add(new Note("Note title 2","Body of our note Finance", Note.Category.FINANCE));
        notes.add(new Note("Note title 3","Body of our note Tech", Note.Category.TECHNICAL));
        notes.add(new Note("Note title 4","Body of our note Quote", Note.Category.QUOTE));
        notes.add(new Note("Note title 1","Body of our note Personal", Note.Category.PERSONAL));
        notes.add(new Note("Note title 2","Body of our note Finance", Note.Category.FINANCE));
        notes.add(new Note("Note title 3","Body of our note Tech", Note.Category.TECHNICAL));
        notes.add(new Note("Note title 4","Body of our note Quote", Note.Category.QUOTE));
        notes.add(new Note("Note title 1","Body of our note Personal", Note.Category.PERSONAL));
        notes.add(new Note("Note title 2","Body of our note Finance", Note.Category.FINANCE));
        notes.add(new Note("Note title 3","Body of our note Tech", Note.Category.TECHNICAL));
        notes.add(new Note("Note title 4","Body of our note Quote", Note.Category.QUOTE));

        noteAdapter = new NoteAdapter(getActivity(), notes);
        setListAdapter(noteAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
