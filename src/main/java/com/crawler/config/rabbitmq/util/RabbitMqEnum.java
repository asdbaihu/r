package com.crawler.config.rabbitmq.util;

public class RabbitMqEnum {

    /**
     * @param
     * @Description:定义数据交换方式
     * @return
     */
    public enum Exchange {
        CRAWER_E("CRAWER_E", "消息分发");

        private String code;
        private String name;

        Exchange(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * describe: 定义队列名称
     **/
    public enum QueueName {
        CRAWER("CRAWER", "CRAWER队列");

        private String code;
        private String name;

        QueueName(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * describe: 定义routing_key
     **/
    public enum QueueEnum {
        Routing_key("crawer","爬虫队列");
        private String code;
        private String name;

        QueueEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}
