package com.galactic_ninja.nota.nota.UserInterface;

public class User {
    private String mTitle;
    private String mCategory;
    private String mNote;
    private String pushId;

    public User(String mTitle, String mCategory, String mNote){
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.mNote = mNote;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmNote() {
        return mNote;
    }

    public void setmNote(String mNote) {
        this.mNote = mNote;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
