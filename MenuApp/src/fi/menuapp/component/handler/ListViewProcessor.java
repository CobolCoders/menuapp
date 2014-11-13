package fi.menuapp.component.handler;

import android.view.View;
import android.widget.ListView;
import android.widget.NumberPicker;

public class ListViewProcessor {

	private ListView listView;
	
	public ListViewProcessor(ListView listView) {
		this.listView = listView;
	}
	
	public void loopNumberPickers() {
		for (int i = 0; i < listView.getChildCount(); i++) {
    		View childView = listView.getChildAt(i);
    		NumberPicker np = ComponentUtils.getNumberPricker(childView);
    		handleNumberPicker(np, i);
    	}
	}
	
	protected void handleNumberPicker(NumberPicker np, int index) {
	
	}
}