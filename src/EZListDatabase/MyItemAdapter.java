/*package EZListDatabase;

import java.util.ArrayList;

import com.example.EZList.EditListList;
import com.example.EZList.MainActivityList;
import com.example.EZList.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class MyItemAdapter extends ArrayAdapter<Item>
{
	private ArrayList<Item> items;
	private Context context;

	public MyItemAdapter(ArrayList<Item> itemList, Context context)
	{		
		super(context, R.id.itemListView, itemList);
		this.items = itemList;
		this.context = context;	
	}

	private static class ItemHolder
	{
		public TextView itemName;
		public CheckBox checkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View itemView = convertView;

		ItemHolder itemHolder = new ItemHolder();

		if(convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			itemView = inflater.inflate(R.layout.item_layout, null);

			itemHolder.itemName = (TextView) itemView.findViewById(R.id.itemTitle);
			itemHolder.checkBox = (CheckBox) itemView.findViewById(R.id.itemCheckBox);
			//itemHolder.checkBox.setButtonDrawable(R.drawable.custom_checkbox);

			itemHolder.checkBox.setOnCheckedChangeListener((EditListList) context);

			//itemView.setTag(itemHolder);
		}
		else
		{
			itemHolder = (ItemHolder) itemView.getTag();
		}

		Item currentItem = items.get(position);
		itemHolder.itemName.setText(currentItem.getItemName());
		if(currentItem.getChecked().equals("1"));
		{
			itemHolder.checkBox.setChecked(true);
		}
		itemHolder.checkBox.setTag(currentItem);

		return itemView;

	}	
}
*/