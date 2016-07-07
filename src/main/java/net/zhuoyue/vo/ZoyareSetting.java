package net.zhuoyue.vo;

public class ZoyareSetting {
    
    private String setid;
    private String pageid;
    private String note;
    private String content;
    private String updatetime;
    
    public ZoyareSetting(String setid, String pageid, String note,
            String content, String updatetime) {
        this.setid = setid;
        this.pageid = pageid;
        this.note = note;
        this.content = content;
        this.updatetime = updatetime;
    }

    public String getSetid() {
        return setid;
    }

    public void setSetid(String setid) {
        this.setid = setid;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
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

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
    
    
}
