package com.vladsch.flexmark.ext.gfm.strikethrough;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughJiraRenderer;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughNodeRenderer;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughSubscriptDelimiterProcessor;
import com.vladsch.flexmark.ext.gfm.strikethrough.internal.StrikethroughYouTrackRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.DataKey;
import com.vladsch.flexmark.util.options.MutableDataHolder;

/**
 * Extension for GFM strikethrough using ~~ (GitHub Flavored Markdown).
 * <p>
 * Create it with {@link #create()} and then configure it on the builders
 * ({@link Parser.Builder#extensions(Iterable)},
 * {@link HtmlRenderer.Builder#extensions(Iterable)}).
 * </p>
 * <p>
 * The parsed strikethrough text regions are turned into {@link Strikethrough} nodes.
 * </p>
 */
public class StrikethroughSubscriptExtension implements Parser.ParserExtension, HtmlRenderer.HtmlRendererExtension {
    public static final DataKey<String> STRIKETHROUGH_STYLE_HTML_OPEN = new DataKey<String>("STRIKETHROUGH_STYLE_HTML_OPEN", (String)null);
    public static final DataKey<String> STRIKETHROUGH_STYLE_HTML_CLOSE = new DataKey<String>("STRIKETHROUGH_STYLE_HTML_CLOSE", (String)null);
    public static final DataKey<String> SUBSCRIPT_STYLE_HTML_OPEN = new DataKey<String>("SUBSCRIPT_STYLE_HTML_OPEN", (String)null);
    public static final DataKey<String> SUBSCRIPT_STYLE_HTML_CLOSE = new DataKey<String>("SUBSCRIPT_STYLE_HTML_CLOSE", (String)null);

    private StrikethroughSubscriptExtension() {
    }

    public static Extension create() {
        return new StrikethroughSubscriptExtension();
    }

    @Override
    public void rendererOptions(final MutableDataHolder options) {

    }

    @Override
    public void parserOptions(final MutableDataHolder options) {

    }

    @Override
    public void extend(Parser.Builder parserBuilder) {
        parserBuilder.customDelimiterProcessor(new StrikethroughSubscriptDelimiterProcessor());
    }

    @Override
    public void extend(HtmlRenderer.Builder rendererBuilder, String rendererType) {
        if (rendererType.equals("HTML")) {
            rendererBuilder.nodeRendererFactory(new StrikethroughNodeRenderer.Factory());
        } else if (rendererType.equals("JIRA")) {
            rendererBuilder.nodeRendererFactory(new StrikethroughJiraRenderer.Factory());
        } else if (rendererType.equals("YOUTRACK")) {
            rendererBuilder.nodeRendererFactory(new StrikethroughYouTrackRenderer.Factory());
        }
    }
}
