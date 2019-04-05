package com.example.chen.translator.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Coder : chenshuaiyu
 * Time : 2019/4/4 9:30
 */
public class Result {

    /**
     * errorCode : 0
     * query : good
     * translation : ["好"]
     * basic : {"phonetic":"gʊd","uk-phonetic":"gʊd","us-phonetic":"ɡʊd","uk-speech":"XXXX","us-speech":"XXXX","explains":["好处","好的","好"]}
     * web : [{"key":"good","value":["良好","善","美好"]}]
     * dict : {"url":"yddict://m.youdao.com/dict?le=eng&q=good"}
     * webdict : {"url":"http://m.youdao.com/dict?le=eng&q=good"}
     * l : EN2zh-CHS
     * tSpeakUrl : XXX
     * speakUrl : XXX
     */

    private String errorCode;
    private String query;
    private BasicBean basic;
    private DictBean dict;
    private WebdictBean webdict;
    private String l;
    private String tSpeakUrl;
    private String speakUrl;
    private List<String> translation;
    private List<WebBean> web;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public DictBean getDict() {
        return dict;
    }

    public void setDict(DictBean dict) {
        this.dict = dict;
    }

    public WebdictBean getWebdict() {
        return webdict;
    }

    public void setWebdict(WebdictBean webdict) {
        this.webdict = webdict;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public List<WebBean> getWeb() {
        return web;
    }

    public void setWeb(List<WebBean> web) {
        this.web = web;
    }

    public static class BasicBean {
        /**
         * phonetic : gʊd
         * uk-phonetic : gʊd
         * us-phonetic : ɡʊd
         * uk-speech : XXXX
         * us-speech : XXXX
         * explains : ["好处","好的","好"]
         */

        private String phonetic;
        @SerializedName("uk-phonetic")
        private String ukphonetic;
        @SerializedName("us-phonetic")
        private String usphonetic;
        @SerializedName("uk-speech")
        private String ukspeech;
        @SerializedName("us-speech")
        private String usspeech;
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkphonetic() {
            return ukphonetic;
        }

        public void setUkphonetic(String ukphonetic) {
            this.ukphonetic = ukphonetic;
        }

        public String getUsphonetic() {
            return usphonetic;
        }

        public void setUsphonetic(String usphonetic) {
            this.usphonetic = usphonetic;
        }

        public String getUkspeech() {
            return ukspeech;
        }

        public void setUkspeech(String ukspeech) {
            this.ukspeech = ukspeech;
        }

        public String getUsspeech() {
            return usspeech;
        }

        public void setUsspeech(String usspeech) {
            this.usspeech = usspeech;
        }

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class DictBean {
        /**
         * url : yddict://m.youdao.com/dict?le=eng&q=good
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebdictBean {
        /**
         * url : http://m.youdao.com/dict?le=eng&q=good
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebBean {
        /**
         * key : good
         * value : ["良好","善","美好"]
         */

        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }
}
