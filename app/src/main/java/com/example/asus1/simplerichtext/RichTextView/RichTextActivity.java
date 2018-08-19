package com.example.asus1.simplerichtext.RichTextView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.asus1.simplerichtext.Base.BaseActivity;
import com.example.asus1.simplerichtext.R;

public class RichTextActivity extends BaseActivity implements
        ViewTreeObserver.OnGlobalLayoutListener,
        View.OnClickListener ,View.OnTouchListener{

    private RichText mEditCapture;
    private static String SUB = "\u3000\u3000";
    private static final String TAG = "RichTextActivity";
    private int mScreenHeight;
    private int mScreenWidth;
    private boolean mKeyboardOpened = false;
    private  View content ;
    private EditText mEditTitle;
    private InputMethodManager mInputManager;
    private LinearScrollView mScrollView;
    private LinearLayout mBottom_1;
    private LinearLayout mBottom_2;
    private LinearLayout mRichRoot;
    private int mEditType = 0;

    private ImageView mBack;
    private TextView mTextCount;
    private ImageView mUndo;
    private ImageView mRedo;
    private TextView mPulish;

    private ImageView mDustbin;
    private ImageView mSetting;
    private ImageView mWrite;
    private ImageView mHistory;
    private ImageView mRecordInput;
    private ImageView mComma;
    private ImageView mFullSport;
    private ImageView mColon;
    private ImageView mQuotaition;
    private ImageView mSoftDwon;

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
        mScreenWidth = metrics.widthPixels;
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
        mBack = findViewById(R.id.iv_back);
        mBack.setOnClickListener(this);
        mTextCount = findViewById(R.id.tv_count);
        mUndo = findViewById(R.id.iv_edit_undo);
        mUndo.setOnClickListener(this);
        mRedo = findViewById(R.id.iv_edit_redo);
        mRedo.setOnClickListener(this);
        mPulish = findViewById(R.id.tv_publish);
        mPulish.setOnClickListener(this);
        mDustbin = findViewById(R.id.iv_dustbin);
        mDustbin.setOnClickListener(this);
        mSetting = findViewById(R.id.iv_setting);
        mSetting.setOnClickListener(this);
        mWrite = findViewById(R.id.iv_write);
        mWrite.setOnClickListener(this);
        mHistory = findViewById(R.id.iv_history);
        mHistory.setOnClickListener(this);
        mRecordInput = findViewById(R.id.iv_record);
        mRecordInput.setOnClickListener(this);
        mComma = findViewById(R.id.iv_comma);
        mComma.setOnClickListener(this);
        mFullSport = findViewById(R.id.iv_period);
        mFullSport.setOnClickListener(this);
        mColon = findViewById(R.id.iv_colon);
        mColon.setOnClickListener(this);
        mQuotaition = findViewById(R.id.iv_quotation);
        mQuotaition.setOnClickListener(this);
        mSoftDwon = findViewById(R.id.iv_close_soft);
        mSoftDwon.setOnClickListener(this);
        mBottom_1 = findViewById(R.id.ll_bottom_1);
        mBottom_2 = findViewById(R.id.ll_bottom_2);
        mEditCapture.setTextChangeListenr(mTextChangeListener);
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
            case R.id.iv_write:
                mEditType = EIDT_CAPURE;
                mEditCapture.setFocusable(true);
                mEditCapture.setFocusableInTouchMode(true);
                mEditCapture.requestFocus();
                mEditCapture.findFocus();
                openKeyboard();
                break;
            case R.id.et_title:
                mEditType = EIDT_TITLE;
                mEditTitle.setFocusable(true);
                mEditTitle.setFocusableInTouchMode(true);
                mEditTitle.requestFocus();
                mEditTitle.findFocus();
                openKeyboard();
                break;
            case R.id.iv_back:
                back();
                break;
            case R.id.iv_dustbin:
                dusbtin();
                break;
            case R.id.iv_setting:
                setting();
                break;
            case R.id.iv_history:
                openHistory();
                break;
            case R.id.iv_record:
                break;
            case R.id.iv_comma:
                addPunctuation(getResources().getString(R.string.comma));
                break;
            case R.id.iv_period:
                addPunctuation(getResources().getString(R.string.fullSpot));
                break;
            case R.id.iv_colon:
                addPunctuation(getResources().getString(R.string.colon));
                break;
            case R.id.iv_quotation:
                addPunctuation(getResources().getString(R.string.quotation));
                break;
            case R.id.iv_close_soft:
                hindKeyboard();
                //keyboardClose();
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

    private void hindKeyboard(){

        mInputManager.hideSoftInputFromWindow(this.getWindow().getDecorView().getWindowToken(),
                0);
    }
    
    private void keyboardClose(){
        Log.d(TAG, "keyboardClose: ");
        mBottom_2.setVisibility(View.GONE);
        mBottom_1.setVisibility(View.VISIBLE);
        mEditCapture.setFocusable(false);
        mEditTitle.setFocusable(false);
        mEditType = 0;
        mScrollView.setFocusable(true);
        mScrollView.setFocusableInTouchMode(true);
    }

    private void openKeyboard(){
        if(!mKeyboardOpened){
            mInputManager.showSoftInput(mEditCapture,InputMethodManager.SHOW_FORCED);
        }

    }

    private RichText.textChangeListener mTextChangeListener = new RichText.textChangeListener() {
        @Override
        public void textChange(int count) {

            mTextCount.setText(count+getResources().getString(R.string.count));
            //mEditCapture.setSelection(mEditCapture.getText().length());
        }
    };

    private void back(){
        String s = mTextCount.getText().toString();
        int count = Integer.valueOf(s.split(getResources()
                .getString(R.string.count))[0]);
        if(count == 0){

        }else {

        }

    }

    private void dusbtin(){

    }

    private void setting(){
        View view = getLayoutInflater().inflate(R.layout.layout_richtext_setting,null,false);

        PopupWindow popupWindow = new PopupWindow(view);
        popupWindow.setWidth(mScreenWidth-20);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setAnimationStyle(R.style.setting_popu_anim);
        popupWindow.showAtLocation(content,Gravity.BOTTOM|Gravity.CENTER,
                0,0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        backgroundAlpha(0.5f);

    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;// 0.0-1.0
        getWindow().setAttributes(lp);
    }


    private void openHistory(){

    }

    private void publish(){

    }

    private void addPunctuation(String pun){
        mEditCapture.setText(mEditCapture.getText().toString()+pun);
        Log.d(TAG, "addPunctuation: "+pun);
        if(pun.equals(getResources().getString(R.string.quotation))){
            mEditCapture.setSelection(mEditCapture.length()-1);
        }else {
            mEditCapture.setSelection(mEditCapture.length());
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setWindow();
    }
}
