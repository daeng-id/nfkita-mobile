package sttnf.app.nfkita.models;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class ToolsModel {
    private String name;
    private int resId;

    public ToolsModel(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }
}
