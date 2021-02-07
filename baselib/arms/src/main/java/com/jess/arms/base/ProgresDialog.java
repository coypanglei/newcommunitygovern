/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jess.arms.base;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.jess.arms.R;


/**
 * todo ： 预期效果 微雀小鸟 煽动翅膀动画
 * ================================================
 * ================================================
 */
public class ProgresDialog extends Dialog {
    public ProgresDialog(@NonNull Context context) {
        super(context, R.style.arms_dialog_progress);
        setContentView(R.layout.arms_dialog_porgress);
        setCanceledOnTouchOutside(false);
    }
}
