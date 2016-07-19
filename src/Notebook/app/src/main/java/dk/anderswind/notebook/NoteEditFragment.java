package dk.anderswind.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private static final String MODIFIED_CATEGORY = "Modified Category";
    private EditText title;
    private EditText message;
    private ImageButton icon;
    private Note.Category savedButtonCategory;
    private AlertDialog categoryDialogObject;
    private AlertDialog confirmDialogObject;
    private boolean isNewNote = false;
    private long noteId;

    public NoteEditFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            isNewNote = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA);
        }

        if(savedInstanceState != null)
        {
            savedButtonCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
            //isNewNote = (boolean) savedInstanceState.get(NoteDetailActivity.NEW_NOTE_EXTRA);
        }

        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        //get widgets
        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        icon = (ImageButton) fragmentLayout.findViewById(R.id.editNoteIcon);
        Button saveButton = (Button) fragmentLayout.findViewById(R.id.saveNote);

        // fill data in
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA, ""));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA, ""));
        noteId = intent.getExtras().getLong(MainActivity.NOTE_ID_EXTRA, 0);

        if(!isNewNote)
        {
            if(savedButtonCategory == null)
            {
                savedButtonCategory = (Note.Category) intent.getSerializableExtra(MainActivity.NOTE_CATEGORY_EXTRA);
            }
            icon.setImageResource(Note.categoryToDrawable(savedButtonCategory));
        }


        // build dialogs
        buildCategoryDialog();
        buildConfirmDialog();

        // set clicks to open dialogs.
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryDialogObject.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialogObject.show();
            }
        });

        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(MODIFIED_CATEGORY, savedButtonCategory);
    }

    private void buildCategoryDialog()
    {
        final String[] categories = new String[] { "Personal", "Technical", "Quote", "Finance" };
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle(R.string.category_icon_dialog_title);

        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                categoryDialogObject.cancel();
                switch (i)
                {
                    case 0:
                        savedButtonCategory = Note.Category.PERSONAL;
                        break;
                    case 1:
                        savedButtonCategory = Note.Category.TECHNICAL;
                        break;
                    case 2:
                        savedButtonCategory = Note.Category.QUOTE;
                        break;
                    case 3:
                        savedButtonCategory = Note.Category.FINANCE;
                        break;
                }
                icon.setImageResource(Note.categoryToDrawable(savedButtonCategory));
            }
        });

        categoryDialogObject = categoryBuilder.create();
    }

    private void buildConfirmDialog()
    {
        AlertDialog.Builder confirmBuilder = new AlertDialog.Builder(getActivity());
        confirmBuilder.setTitle(R.string.confirm_dialog_title);
        confirmBuilder.setMessage(R.string.confirm_dialog_text);

        confirmBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("Save Note", "Title:" + title.getText() + " Message:" + message.getText() + " Category:" + savedButtonCategory);

                NotebookDbAdapter notebookDbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
                notebookDbAdapter.open();
                if(isNewNote)
                {
                    notebookDbAdapter.createNote(title.getText() + "", message.getText() + "",
                            (savedButtonCategory == null)? Note.Category.PERSONAL : savedButtonCategory);
                }
                else
                {
                    notebookDbAdapter.updateNote(noteId, title.getText() + "", message.getText() + "", savedButtonCategory);
                }
                notebookDbAdapter.close();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        confirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        confirmDialogObject = confirmBuilder.create();
    }

}
