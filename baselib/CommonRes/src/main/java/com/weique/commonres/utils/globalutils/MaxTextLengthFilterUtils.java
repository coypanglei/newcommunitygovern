package com.weique.commonres.utils.globalutils;

import android.text.InputFilter;
import android.text.Spanned;

import com.blankj.utilcode.util.ToastUtils;

public class MaxTextLengthFilterUtils implements InputFilter {
    private int mMaxLength;
    private int mToast;

    public MaxTextLengthFilterUtils(int max, int toast) {
        mMaxLength = max - 1;
        mToast = toast;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if (keep < (end - start)) {
            ToastUtils.showShort(mToast);
        }
        if (keep <= 0) {
            return "";
        } else if (keep >= end - start) {
            return null;
        } else {
            return source.subSequence(start, start + keep);
        }
    }

}