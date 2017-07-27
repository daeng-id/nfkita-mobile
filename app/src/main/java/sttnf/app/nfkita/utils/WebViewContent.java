package sttnf.app.nfkita.utils;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class WebViewContent {

    public static String clean(String bodyHTML) {
        String head = "<head>" +
                "<style>" +
                "img{" +
                "max-width: 100%; " +
                "width:auto; height: " +
                "auto;}" +
                "body{" +
                "font-size: 14px;" +
                "line-height:150%;" +
                "overflow: auto;" +
                "overflow-y: hidden;}" +
                "</style>" +
                "</head>";
        return "<html>" + head + "<body text=\"#595959\">" + bodyHTML + "</body></html>";
    }

}
