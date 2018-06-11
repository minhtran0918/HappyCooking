package calebzone.hcmute.edu.vn.happycooking.MyUtility;

import android.text.InputFilter;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.widget.TextView;

public class WrapTextViewFilter implements InputFilter {
    private final TextView view;

    public WrapTextViewFilter(TextView view) {
        this.view = view;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int w = view.getWidth();
        if (w <= 0) {
            return source;
        } else {
            TextPaint paint = view.getPaint();
            int wpl = view.getCompoundPaddingLeft();
            int wpr = view.getCompoundPaddingRight();
            int width = w - wpl - wpr;
            SpannableStringBuilder result = new SpannableStringBuilder(source);
            int num_appends = 0;
            for (int index = start; index < end; index++) {
                if (Layout.getDesiredWidth(source, start, index + 1, paint) > width) {
                    result.insert(index + num_appends, "\n");
                    num_appends++;
                    start = index;
                } else if (source.charAt(index) == '\n') {
                    start = index;
                }
            }
            return null;
        }
    }
}
