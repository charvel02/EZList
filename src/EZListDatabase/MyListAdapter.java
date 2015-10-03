package EZListDatabase;

import java.util.ArrayList;
import com.example.EZList.R;

import EZListDatabase.MyListContent.MyList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<MyList>
{
	private final Context context;
	private ArrayList<MyList> lists; 

	/**
	 * @param context
	 * @param resource
	 * @param listItemLayout 
	 * @param textViewResourceId
	 */
    public MyListAdapter(Context context, int resource, ArrayList<MyList> al)
    {
	    super(context, resource);
	    this.context = context;
	    this.lists = al;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listItemView = convertView;
		
		if(listItemView == null)
		{
			listItemView = inflater.inflate(R.layout.list_item_layout, parent, false);
		}
		
		//Current list
		MyList currentList = lists.get(position);
		
		//Add info to view
		TextView title = (TextView) listItemView.findViewById(R.id.listItemTitle);
		title.setText(currentList.getListName());
		
		TextView date = (TextView) listItemView.findViewById(R.id.lastUpdated);
		//date.setText("Test");
		
	    return listItemView;
    }

}
