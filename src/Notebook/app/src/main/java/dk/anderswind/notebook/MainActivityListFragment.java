package dk.anderswind.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import dk.anderswind.notebook.MainActivity.FragmentToLaunch;

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

        // menu is for the listview.
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(position, FragmentToLaunch.VIEW);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // grab position of the note i pressed.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        switch (item.getItemId())
        {
            case R.id.edit:
                launchNoteDetailActivity(rowPosition, FragmentToLaunch.EDIT);
                Log.d("Menu Clicks", "We pressed edit");
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(int position, FragmentToLaunch fragmentToLaunch)
    {
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(MainActivity.NOTE_TITLE_EXTRA, note.getTitle());
        intent.putExtra(MainActivity.NOTE_MESSAGE_EXTRA, note.getMessage());
        intent.putExtra(MainActivity.NOTE_CATEGORY_EXTRA, note.getCategory());
        intent.putExtra(MainActivity.NOTE_ID_EXTRA, note.getNoteId());

        intent.putExtra(MainActivity.NOTE_VIEW_FRAGMENT_LAUNCH, fragmentToLaunch);
        
        startActivity(intent);
    }
}
