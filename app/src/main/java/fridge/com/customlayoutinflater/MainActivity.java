package fridge.com.customlayoutinflater;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

public class MainActivity extends Activity {
    LayoutInflater layoutInflater;

    @Override
    public Object getSystemService(String name) {
        if (name.equals(LAYOUT_INFLATER_SERVICE)) {
            if (layoutInflater == null) {
                LayoutInflater origin = (LayoutInflater) super.getSystemService(name);
                layoutInflater = new PPLayoutInflater(origin, this);
            }
            return layoutInflater;
        }
        return super.getSystemService(name);
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
