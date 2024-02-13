package com.coin.web.utils;

import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

/**
 * 处理富文本
 */
public class OwaspHtmlSanitizerUtil {

   /* private static final Pattern whiteUrl = Pattern.compile("^(http|https)://([\\w-@:]+\\.)*(stayfoolish\\.cc|baidu\\.com)(/.*)?$");

    private static final String[] safeAttributes = {"align", "alink", "alt", "bgcolor", "border", "cellpadding", "cellspacing", "class", "color", "cols", "colspan", "coords", "dir", "face", "height", "hspace", "ismap", "lang", "marginheight", "marginwidth", "multiple", "nohref", "noresize", "noshade", "nowrap", "ref", "rel", "rev", "rows", "rowspan", "scrolling", "shape", "span", "summary", "tabindex", "title", "usemap", "valign", "value", "vlink", "vspace", "width"};

    private static final String[] allowTags = {"b", "i", "font", "s", "u", "o", "sup", "sub", "ins", "del", "strong", "strike", "tt", "code", "big", "small", "br", "span", "em", "p", "div", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "li", "blockquote", "a", "img"};

    private static final PolicyFactory policy = new HtmlPolicyBuilder().allowElements(allowTags).allowAttributes(safeAttributes).globally()
            .allowUrlProtocols("http", "https").allowAttributes("src").onElements("img")
            .allowAttributes("href").matching(whiteUrl).onElements("a").requireRelNofollowOnLinks().toFactory();

    public static String sanitizeByMyPolicy(String htmlInput) {
        return policy.sanitize(htmlInput);
    }*/

    private static final PolicyFactory POLICY_FACTORY = Sanitizers.FORMATTING.and(Sanitizers.LINKS).and(Sanitizers.BLOCKS).and(Sanitizers.IMAGES).and(Sanitizers.STYLES).and(Sanitizers.TABLES);

    public static String sanitizeByAllDefaultPolicy(String htmlInput) {
        return POLICY_FACTORY.sanitize(htmlInput);
    }
}