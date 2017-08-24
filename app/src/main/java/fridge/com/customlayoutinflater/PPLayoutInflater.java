package fridge.com.customlayoutinflater;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * copied from PhoneLayoutInflater
 * Created by Fridge on 17/8/24.
 */

public class PPLayoutInflater extends LayoutInflater {
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app."
    };

    /**
     * Instead of instantiating directly, you should retrieve an instance
     * through {@link Context#getSystemService}
     *
     * @param context The Context in which in which to find resources and other
     *                application-specific things.
     *
     * @see Context#getSystemService
     */
    public PPLayoutInflater(Context context) {
        super(context);
    }

    protected PPLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        Log.e("shitshit", "【" + this.getClass().getSimpleName() + "】   onCreateView() called with: " + "name = [" + name + "], attrs = [" + attrs + "]");
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    //do custom here...
                    if(view instanceof ImageView) {
                        view.setBackgroundColor(Color.BLUE);

                        if (attrs != null) {

                            for (int i = 0; i < attrs.getAttributeCount(); i++) {
                                String attributeName = attrs.getAttributeName(i);
                                String attributeValue = attrs.getAttributeValue(i);
                                processAttrs(attributeName, attributeValue, view);
                            }
                        }

                    }
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        return super.onCreateView(name, attrs);
    }

    /**
     * attrName = [layout_width], attrValue = [100.0dip]
     * attrName = [layout_height], attrValue = [30.0dip]
     *
     */

    public void processAttrs(String attrName, String attrValue, View view){
        Log.e("shitshit", "【" + this.getClass().getSimpleName() + "】   processAttrs() called with: " + "attrName = [" + attrName + "], attrValue = [" + attrValue + "], view = [" + view + "]");

        if(attrName.equals("tag")){
            view.setBackgroundColor(Color.parseColor(attrValue));
        }
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return new PPLayoutInflater(this, newContext);
    }
}
