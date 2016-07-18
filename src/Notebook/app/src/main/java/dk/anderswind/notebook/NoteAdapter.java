package dk.anderswind.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ander on 18-07-2016.
 */
public class NoteAdapter extends ArrayAdapter<Note>{

    public static class ViewHolder {
        TextView noteTitle;
        TextView noteText;
        ImageView noteIcon;
    }

    public NoteAdapter(Context context, ArrayList<Note> notes)
    {
        super(context,0,notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for this position
        Note note = getItem(position);

        ViewHolder viewHolder;
        if(convertView== null)
        {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
            viewHolder.noteTitle = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.noteText = (TextView) convertView.findViewById(R.id.listItemNoteText);
            viewHolder.noteIcon = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.noteTitle.setText(note.getTitle());
        viewHolder.noteText.setText(note.getMessage());
        viewHolder.noteIcon.setImageResource(note.getAssociatedDrawable());

        return convertView;
    }
}
