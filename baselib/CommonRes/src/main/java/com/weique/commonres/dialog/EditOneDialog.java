package com.weique.commonres.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.google.gson.Gson;
import com.weique.commonres.base.commonbean.EditStyleBean;
import com.weique.commonres.base.commonbean.RecordsBean;
import com.weique.commonres.utils.commonutils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.jessyan.armscomponent.commonres.R;
import me.jessyan.armscomponent.commonres.R2;
import razerdp.basepopup.BasePopupWindow;


/**
 * 只有一个的编辑框
 *
 * @author Administrator
 */
public class EditOneDialog extends BasePopupWindow {


    @BindView(R2.id.title)
    TextView title;
    @BindView(R2.id.edit)
    EditText edit;
    @BindView(R2.id.cancel_btn)
    TextView cancelBtn;
    @BindView(R2.id.confirm_btn)
    TextView confirmBtn;
    private RecordsBean recordsBean;
    private Unbinder unbinder;
    Gson gson = new Gson();
    private RecordBeanInterface recordBeanInterface;


    public void setRecordBeanInterface(RecordBeanInterface recordBeanInterface) {
        this.recordBeanInterface = recordBeanInterface;
    }

    public EditOneDialog(Context context) {
        super(context);
        try {

            setPopupGravity(Gravity.CENTER);

            //自动打开输入框
            setAutoShowInputMethod(edit, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecordsBean getRecordsBean() {
        return recordsBean;
    }

    public void setRecordsBean(RecordsBean recordsBean) {
        try {
            this.recordsBean = recordsBean;
            title.setText(recordsBean.getTitile());
            edit.setHint(recordsBean.getDefaultValue());
            edit.setText(recordsBean.getValue());
            EditStyleBean styleBean = gson.fromJson(recordsBean.getStyle(), EditStyleBean.class);
            ViewUtils.setCommonStyle(styleBean, edit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateContentView() {
        View view = createPopupById(R.layout.public_common_dialog_edit);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick({R2.id.cancel_btn, R2.id.confirm_btn})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.cancel_btn) {
            dismiss();
        } else if (id == R.id.confirm_btn) {
            try {
                if (ObjectUtils.isNotEmpty(recordBeanInterface)) {
                    recordsBean.setValue(edit.getText().toString());
                    recordBeanInterface.getRecordBean(recordsBean);
                }
                dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
