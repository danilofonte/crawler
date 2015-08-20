package com.java.crawler.jobs.impl;

import java.util.List;

import com.java.crawler.contants.CrawlerConstants;
import com.java.crawler.contants.CrawlerSession;
import com.java.crawler.contants.EstadosEnum;
import com.java.crawler.jobs.process.WebMotorsCrawlerJobProcess;
import com.java.crawler.models.MarcaAtiva;
import com.java.crawler.models.ModeloAtivo;
import com.java.crawler.webmotors.rules.WebMotorsCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import play.Logger;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;

@On("0 15 10 ? * SUN")
public class WebMotorsCrawlerJob extends Job {

	public void doJob() {

		//WebMotorsCrawlerJobProcess.performCralwerProcess();
			
	}

	

}
