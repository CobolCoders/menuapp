package fi.menuapp.component.handler;

import fi.menuapp.R;
import android.view.View;
import android.widget.NumberPicker;

public class ComponentUtils {

	public static NumberPicker getNumberPricker(View view) {
		return (NumberPicker)view.findViewById(R.id.count);
	}
}
