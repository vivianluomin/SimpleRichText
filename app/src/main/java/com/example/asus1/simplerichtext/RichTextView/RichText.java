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

import java.util.ArrayList;
import java.util.List;

public class RichText extends AppCompatEditText {

    private Context mContext;
    private static final String TAG = "RichText";
    private static String SUB = "\u3000\u3000";
    private textChangeListener mListener;
    private final  int MAX_ADD = 6;
    private final  int MAX_DE = 6;

    private List<String> mAddStrings = new ArrayList<>();
    private List<String> mDeStrings = new ArrayList<>();
    int mAddSize = 0;
    int mDeSize = 0;

    public RichText(Context context) {
        this(context,null);
    }

    public RichText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public RichText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        addTextWatch();
    }

    private void addTextWatch(){
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(mListener!=null){

                    mListener.textChange( getABCCount(s.toString())+getChCount(s.toString()));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setTextChangeListenr(textChangeListener listenr){
        mListener = listenr;
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

    public interface textChangeListener{
        void textChange(int count);
    }

    private int getABCCount(String s){
        int count = 0;
        for(int i=0;i<s.length();i++){
            char cs =s.charAt(i);
            if((cs>='a'&& cs<='z') || ((cs>='A'&& cs<='Z')) || ((cs>='0'&& cs<='9')) ){
                count++;
            }
        }
        return count;
    }

    private int getChCount(String s){

        int count =0;
        String Reg="^[\u4e00-\u9fa5]{1}$";  //汉字的正规表达式
        for(int i=0;i<s.length();i++){
            String b=Character.toString(s.charAt(i));
            if(b.matches(Reg))
                count++;
        }

        return count;
    }
}
