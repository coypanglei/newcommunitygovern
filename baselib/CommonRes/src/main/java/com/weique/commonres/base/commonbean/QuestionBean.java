package com.weique.commonres.base.commonbean;


import com.weique.commonres.base.commonbean.interfaces.CommonBean;

import java.util.List;

public class QuestionBean  implements CommonBean {


    /**
     * imgs : ["http://192.168.20.149/resource/086f0c3a6ba549c0ace0733bfb8f863a.jpg"]
     * content : 2333333
     * id : c58811523b404e9587a0ad66278b0ae8
     */

    private String content;
    private String id;
    private List<String> attachments;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    @Override
    public void setItemType(int itemType) {

    }
}
