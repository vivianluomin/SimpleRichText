package com.example.asus1.simplerichtext.RichTextView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.asus1.simplerichtext.Base.BaseActivity;
import com.example.asus1.simplerichtext.R;

public class RichTextActivity extends BaseActivity implements ViewTreeObserver.OnGlobalLayoutListener,View.OnClickListener ,View.OnTouchListener{

    private EditText mEditCapture;
    private static String SUB = "\u3000\u3000";
    private static final String TAG = "RichTextActivity";
    private int mScreenHeight;
    private boolean mKeyboardOpened = false;
    private  View content ;
    private EditText mEditTitle;
    private InputMethodManager mInputManager;
    private LinearScrollView mScrollView;
    private LinearLayout mBottom_1;
    private LinearLayout mBottom_2;
    private LinearLayout mRichRoot;
    private int mEditType = 0;

    private static final int EIDT_CAPURE = 1;
    private static final int EIDT_TITLE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richtext);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        mScreenHeight = metrics.heightPixels;
        content = this.findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnGlobalLayoutListener(this);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        init();

    }

    private void init(){
        mEditCapture = findViewById(R.id.et_capture);
        mEditCapture.setText(SUB);
        mEditCapture.setSelection(mEditCapture.length());
        mEditTitle = findViewById(R.id.et_title);
        mEditTitle.setOnClickListener(this);
        mScrollView = findViewById(R.id.ll_scrollView);
        mRichRoot = findViewById(R.id.ll_richroot);
        mEditCapture.setOnTouchListener(this);
        mEditCapture.setOnClickListener(this);
        mScrollView.setOnClickListener(this);
        mBottom_1 = findViewById(R.id.ll_bottom_1);
        mBottom_2 = findViewById(R.id.ll_bottom_2);
        mEditCapture.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditCapture.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()
                        == KeyEvent.ACTION_DOWN){
                    int selection = mEditCapture.getSelectionEnd();
                    int prelength = mEditCapture.getText().length();
                    mEditCapture.setText(mEditCapture.getText().insert(selection,
                             "\r\n"+
                            SUB));
                    if(selection!=prelength){
                        mEditCapture.setSelection(selection+"\r\n".length()+4);
                    }else {
                        mEditCapture.setSelection(mEditCapture.length());
                    }

                    return  true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        content.getWindowVisibleDisplayFrame(rect);
        int heightOff = content.getRootView().getHeight()-(rect.bottom- rect.top);
        int height;
        if (mScreenHeight>=1920){
            height = 200;
        }else {
            height = 100;
        }
        
        if(!mKeyboardOpened && height<heightOff){
            mKeyboardOpened = true;
            content.setPadding(0,0,0,mScreenHeight-rect.bottom);
            keyboardOpen();
        }else if(mKeyboardOpened && heightOff<height){
            mKeyboardOpened = false;
            content.setPadding(0,0,0,0);
            keyboardClose();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_capture :
            case R.id.ll_richroot:
                mEditType = EIDT_CAPURE;
                mEditCapture.setFocusable(true);
                mEditCapture.setFocusableInTouchMode(true);
                mEditCapture.requestFocus();
                mEditCapture.findFocus();
                mInputManager.showSoftInput(mEditCapture,InputMethodManager.SHOW_FORCED);
                break;
            case R.id.et_title:
                mEditType = EIDT_TITLE;
                mEditTitle.setFocusable(true);
                mEditTitle.setFocusableInTouchMode(true);
                mEditTitle.requestFocus();
                mEditTitle.findFocus();
                mInputManager.showSoftInput(mEditCapture,InputMethodManager.SHOW_FORCED);
                break;


        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId() == R.id.et_capture && mEditCapture.canScrollVertically(0)){
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if(event.getAction() == MotionEvent.ACTION_UP){
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    private void keyboardOpen(){
        Log.d(TAG, "keyboardOpen: ");
        mBottom_1.setVisibility(View.GONE);
        mBottom_2.setVisibility(View.VISIBLE);
        if(mEditType == EIDT_CAPURE){
            mEditCapture.requestFocus();
        }else if (mEditType == EIDT_TITLE){
            mEditTitle.requestFocus();
        }

    }
    
    private void keyboardClose(){
        Log.d(TAG, "keyboardClose: ");
        mBottom_1.setVisibility(View.VISIBLE);
        mBottom_2.setVisibility(View.GONE);
        mEditCapture.setFocusable(false);
        mEditTitle.setFocusable(false);
        mEditType = 0;
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setWindow();
    }
}
