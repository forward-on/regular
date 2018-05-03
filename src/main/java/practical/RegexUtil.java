package practical;

/**
 * @author : ly.
 * @Date : 2018/5/2.
 */
public class RegexUtil {

    /**
     * 将富文本转为纯文本
     * @param rich
     * @return
     */
    public static String dealRich2Txt(String rich) {
        String content = rich.replaceAll("<(/p|br/?)>", "\n")
                .replaceAll("<[^<>]+>", "")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
        return content.trim();
    }

}
