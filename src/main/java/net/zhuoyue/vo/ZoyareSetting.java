package net.zhuoyue.vo;

public class ZoyareSetting {
    
    private String setid;
    private String note;
    private String content;
    
    public ZoyareSetting(String setid, String note, String content) {
        this.setid = setid;
        this.note = note;
        this.content = content;
    }

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

}
