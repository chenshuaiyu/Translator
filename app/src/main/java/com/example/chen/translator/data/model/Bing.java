package com.example.chen.translator.data.model;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/4 15:17
 */
public class Bing {

    /**
     * images : [{"startdate":"20190404","fullstartdate":"201904040700","enddate":"20190405","url":"/th?id=OHR.NelderPlot_EN-CN8813996472_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp","urlbase":"/th?id=OHR.NelderPlot_EN-CN8813996472","copyright":"For Earth Month, a symmetrical forest known as a Nelder Plot (© Rachid Dahnoun/Tandem Stills + Motion)","copyrightlink":"http://www.bing.com/search?q=Nelder+wheel+plots+for+tree+density+experiments&form=hpcapt&filters=HpDate:%2220190404_0700%22","title":"Branch out for Earth Month","quiz":"/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20190404_NelderPlot%22&FORM=HPQUIZ","wp":true,"hsh":"e2898068feb0489eb10ea790d03900ea","drk":1,"top":1,"bot":1,"hs":[]}]
     * tooltips : {"loading":"Loading...","previous":"Previous image","next":"Next image","walle":"This image is not available to download as wallpaper.","walls":"Download this image. Use of this image is restricted to wallpaper only."}
     */

    private TooltipsBean tooltips;
    private List<ImagesBean> images;

    public TooltipsBean getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsBean tooltips) {
        this.tooltips = tooltips;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class TooltipsBean {
        /**
         * loading : Loading...
         * previous : Previous image
         * next : Next image
         * walle : This image is not available to download as wallpaper.
         * walls : Download this image. Use of this image is restricted to wallpaper only.
         */

        private String loading;
        private String previous;
        private String next;
        private String walle;
        private String walls;

        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }
    }

    public static class ImagesBean {
        /**
         * startdate : 20190404
         * fullstartdate : 201904040700
         * enddate : 20190405
         * url : /th?id=OHR.NelderPlot_EN-CN8813996472_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp
         * urlbase : /th?id=OHR.NelderPlot_EN-CN8813996472
         * copyright : For Earth Month, a symmetrical forest known as a Nelder Plot (© Rachid Dahnoun/Tandem Stills + Motion)
         * copyrightlink : http://www.bing.com/search?q=Nelder+wheel+plots+for+tree+density+experiments&form=hpcapt&filters=HpDate:%2220190404_0700%22
         * title : Branch out for Earth Month
         * quiz : /search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20190404_NelderPlot%22&FORM=HPQUIZ
         * wp : true
         * hsh : e2898068feb0489eb10ea790d03900ea
         * drk : 1
         * top : 1
         * bot : 1
         * hs : []
         */

        private String startdate;
        private String fullstartdate;
        private String enddate;
        private String url;
        private String urlbase;
        private String copyright;
        private String copyrightlink;
        private String title;
        private String quiz;
        private boolean wp;
        private String hsh;
        private int drk;
        private int top;
        private int bot;
        private List<?> hs;

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public boolean isWp() {
            return wp;
        }

        public void setWp(boolean wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public int getDrk() {
            return drk;
        }

        public void setDrk(int drk) {
            this.drk = drk;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getBot() {
            return bot;
        }

        public void setBot(int bot) {
            this.bot = bot;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }
    }
}
