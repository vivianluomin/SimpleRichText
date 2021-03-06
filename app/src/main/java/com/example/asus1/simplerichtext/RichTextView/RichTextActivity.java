package com.example.asus1.simplerichtext.RichTextView;


import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus1.simplerichtext.Base.BaseActivity;
import com.example.asus1.simplerichtext.R;
import com.example.asus1.simplerichtext.Util.ConstantUtil;
import com.example.asus1.simplerichtext.Util.DialogUtil;

public class RichTextActivity extends AppCompatActivity implements
        ViewTreeObserver.OnGlobalLayoutListener,
        View.OnClickListener ,View.OnTouchListener,SettingPopuWindow.callBack{

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

    private RelativeLayout mRelaTitle;
    private boolean mDrak = false;

    private static final int EIDT_CAPURE = 1;
    private static final int EIDT_TITLE = 2;
    public static int mIndex = 3;

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
        setWindow();
        init();

    }


    protected void setWindow(){
        Window window  = getWindow();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){

            View decorView = window.getDecorView();
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

                );
            }else {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                |View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                );
            }
        }else {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

        window.setStatusBarColor(Color.TRANSPARENT);
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
        mRelaTitle = findViewById(R.id.rela_title);
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
            case R.id.tv_publish:
                publish();
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
        AlertDialog alertDialog = DialogUtil.CreateNomalDialog(this,
                getResources().getString(R.string.dustin_title),
                getResources().getString(R.string.dustin_meassage),
                true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == DialogInterface.BUTTON_POSITIVE){

                        }else if(which == DialogInterface.BUTTON_NEGATIVE){

                        }
                    }
                });
        alertDialog.show();
    }

    private void setting(){

        final SettingPopuWindow popupWindow = new SettingPopuWindow(this,
                mScreenWidth-20,
                WindowManager.LayoutParams.WRAP_CONTENT );
        Log.d(TAG, "setting: "+mIndex);
        popupWindow.showAtLocation(content,Gravity.BOTTOM|Gravity.CENTER,
                0,20);

        popupWindow.setCallback(this);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ConstantUtil.mColorSelect = popupWindow.getNowPick();
                ConstantUtil.mFontIndex = popupWindow.getFontIndex();
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
        if(pun.equals(getResources().getString(R.string.quotation))){
            mEditCapture.setSelection(mEditCapture.length()-1);
        }else {
            mEditCapture.setSelection(mEditCapture.length());
        }

    }

    public void setScrennManualMode() {
        ContentResolver contentResolver = getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLight(float light) {

         Window window = getWindow();
         WindowManager.LayoutParams lp = window.getAttributes();
         lp.screenBrightness = light;
         window.setAttributes(lp);

    }

    @Override
    public void setFontSize(float font,int index) {
        //mIndex = index;
        mEditCapture.setTextSize(font);
    }

    @Override
    public void changeBackgound(int id) {
        switch (id){
            case R.id.iv_white:
                setBackgroudColor(getResources().getColor(R.color.white)
                        ,getResources().getColor(R.color.white),false);
                break;
            case R.id.iv_pink:
                setBackgroudColor(getResources().getColor(R.color.pink)
                        ,getResources().getColor(R.color.pink_less),false);
                break;
            case R.id.iv_yellow:
                setBackgroudColor(getResources().getColor(R.color.orange)
                        ,getResources().getColor(R.color.orage_less),false);
                break;
            case R.id.iv_green:
                setBackgroudColor(getResources().getColor(R.color.green)
                        ,getResources().getColor(R.color.green_less),false);
                break;
            case R.id.iv_bule:
                setBackgroudColor(getResources().getColor(R.color.blue)
                        ,getResources().getColor(R.color.blue_less),false);
                break;
            case R.id.iv_black:
                setBackgroudColor(getResources().getColor(R.color.balck)
                        ,getResources().getColor(R.color.black_less),true);
                break;
        }
    }

    private void setBackgroudColor(int mianColor,int titleColor,boolean black){
        if(!black){
            mRelaTitle.setBackgroundColor(titleColor);
            mScrollView.setBackgroundColor(mianColor);
            mBottom_1.setBackgroundColor(titleColor);
            if(mDrak){
                mBack.setImageResource(R.mipmap.ic_back);
                mTextCount.setTextColor(getResources().getColor(R.color.text_color));
                mPulish.setTextColor(getResources().getColor(R.color.text_color));
                mEditCapture.setTextColor(getResources().getColor(R.color.text_color));
                mEditTitle.setTextColor(getResources().getColor(R.color.text_color));
               // mEditTitle.setHintTextColor(getResources().getColor(R.color.text_color));
                mDustbin.setImageResource(R.mipmap.ic_bar_trash);
                mSetting.setImageResource(R.mipmap.ic_setting);
                mWrite.setImageResource(R.mipmap.ic_write);
                mHistory.setImageResource(R.mipmap.ic_history);
                mDrak = false;
            }
        }else {
            mRelaTitle.setBackgroundColor(titleColor);
            mScrollView.setBackgroundColor(mianColor);
            mBottom_1.setBackgroundColor(titleColor);
            mBack.setImageResource(R.mipmap.ic_back_dark);
            mTextCount.setTextColor(getResources().getColor(R.color.white));
            mPulish.setTextColor(getResources().getColor(R.color.white));
            mEditCapture.setTextColor(getResources().getColor(R.color.white));
            mEditTitle.setTextColor(getResources().getColor(R.color.white));
            //mEditTitle.setHintTextColor(getResources().getColor(R.color.white));
            mDustbin.setImageResource(R.mipmap.ic_bar_trash_dark);
            mSetting.setImageResource(R.mipmap.ic_settings_dark);
            mWrite.setImageResource(R.mipmap.ic_write_dark);
            mHistory.setImageResource(R.mipmap.ic_history_dark);
            mDrak = true;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setWindow();
    }
}
