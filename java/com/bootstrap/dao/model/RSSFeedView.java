package com.bootstrap.dao.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;

public class RSSFeedView extends AbstractRssFeedView {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest req) {
		feed.setEncoding("UTF-8");
		feed.setLanguage(LocaleContextHolder.getLocale().getLanguage().equals("rs") ? "Serbian" : "English");
		feed.setTitle(messageSource.getMessage("rssfeed.title", null, LocaleContextHolder.getLocale()));
		feed.setDescription(messageSource.getMessage("rssfeed.description", null, LocaleContextHolder.getLocale()));
		feed.setLink("https://humankaryotype.com/chromosomes");
		feed.setLastBuildDate(new Date());
	}

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		List<Chromosome> chroms = (List<Chromosome>) model.get("feedcontent");
		return chroms.stream().map(this::toItem).collect(Collectors.toList());
	}

	private Item toItem(Chromosome chromosome) {
		Item item = new Item();
		item.setTitle(chromosome.getName());
		Description desc = new Description();
		desc.setValue(chromosome.getDescription());
		item.setDescription(desc);
		item.setLink("https://humankaryotype.com/chromosomes/" + chromosome.getId());
		return item;
	}

}
