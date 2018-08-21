package com.example.asus1.simplerichtext.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.asus1.simplerichtext.R;

public class DialogUtil {

    public static AlertDialog CreateNomalDialog(Context context,
                                         String title,
                                         String meassage,
                                         boolean cancle,DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new
                AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(meassage)
                .setPositiveButton(context.getResources()
                        .getText(R.string.confirm),listener);
        if(cancle){
            builder.setNegativeButton(context.getResources()
                    .getText(R.string.cancel),listener);
        }

        AlertDialog alertDialog = builder.create();
        return alertDialog;

    }
}
