package ru.itis.utils.helpers.impl;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;
import ru.itis.utils.helpers.HtmlCleaner;


@Component
public class HtmlCleanerImpl implements HtmlCleaner {

    @Override
    public String keepOnlySafeTags(String html) {
        if(html != null) {
            return Jsoup.clean(html, Safelist.relaxed());
        }
         return null;
    }
}
