package shivamgupta.myfirstapp.com.worditout;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/7/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Adapter to bind a ToDoItem List to a view
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    /**
     * Adapter context
     */
    Context mContext;

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    public ItemAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    /**
     * Returns the view for a specific item on the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final Item currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);

        //final TextView tv = (TextView) row.findViewById(R.id.listitem);
        //tv.setText(currentItem.Text);
        //Toast.makeText(ItemAdapter.this,"success",Toast.LENGTH_LONG).show();
        return row;
    }

}