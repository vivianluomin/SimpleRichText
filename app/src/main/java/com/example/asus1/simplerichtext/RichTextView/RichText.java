package com.example.asus1.simplerichtext.RichTextView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.example.asus1.simplerichtext.R;

public class RichText extends AppCompatEditText {

    private Context mContext;
    private static final String TAG = "RichText";
    private static String SUB = "\u3000\u3000";

    public RichText(Context context) {
        this(context,null);
    }

    public RichText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public RichText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        //addTextWatch();
    }

    private void addTextWatch(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged: "+s);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged: "+s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthBefore != lengthAfter) {
            int startSelection = getSelectionStart();
            setText(text);
            setSelection(startSelection);
        }

    }
}
