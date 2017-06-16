package com.wuwg.component.net.view;

import android.content.Context;

/**
 * Created by wuwengao on 2017/6/15.
 */
public class DialogHelper {

    private static DialogHelper sInstance;

    private DialogUtil.DialogDismissListener dialogDismissListener;

    private DialogHelper() {
    }

    public synchronized static DialogHelper getInstance() {
        if (sInstance == null) {
            sInstance = new DialogHelper();
        }
        return sInstance;
    }

    public void showLodingDialog(Context context) {
        if (dialogDismissListener != null) {
            dialogDismissListener.onDismiss();
        }
        dialogDismissListener = DialogUtil.createLoadingDialog(context, null,"");
    }

    public void dissLodingDialog() {
        if(dialogDismissListener != null) {
            dialogDismissListener.onDismiss();
        }
    }

}
