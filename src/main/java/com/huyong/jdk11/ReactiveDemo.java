package com.huyong.jdk11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/reactive-streams/reactive-streams-jvm
 */
public class ReactiveDemo {

    private static Logger log = LoggerFactory.getLogger(ReactiveDemo.class);

    public static void main(String[] args) throws Exception {
        testProcessor();
    }

    public static void testReactive() throws InterruptedException {
        //1.创建 生产者Publisher JDK9自带的 实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("订阅成功。。");
                subscription.request(1);
                System.out.println("订阅方法里请求一个数据");
            }

            @Override
            public void onNext(Integer item) {
                log.info("【onNext 接受到数据 item : {}】 ", item);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("【onError 出现异常】");
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                log.info("【onComplete 所有数据接收完成】");
            }
        };
        //3。发布者和订阅者 建立订阅关系 就是回调订阅者的onSubscribe方法传入订阅合同
        publisher.subscribe(subscriber);
        //4.发布者 生成数据
        for (int i = 1; i <= 1000; i++) {
            log.info("【生产数据 {} 】", i );
            //submit是一个阻塞方法，此时会调用订阅者的onNext方法   默认容量为256  容量充足才会继续生产数据  实现背压机制
            publisher.submit(i);
        }
        //5.发布者 数据都已发布完成后，关闭发送，此时会回调订阅者的onComplete方法
        publisher.close();
        //主线程睡一会
        Thread.currentThread().join(100000);
    }

    public static void testProcessor() throws InterruptedException {
        //创建发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        //创建 Processor 即是发布者也是订阅者
        MyProcessor myProcessor = new MyProcessor();
        //创建最终订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1);
            }
            @Override
            public void onNext(Integer item) {
                log.info("【onNext 从Processor 接受到过滤后的 数据 item : {}】 ", item);
                this.subscription.request(1);
            }
            @Override
            public void onError(Throwable throwable) {
                log.info("【onError 出现异常】");
                subscription.cancel();
            }
            @Override
            public void onComplete() {
                log.info("【onComplete 所有数据接收完成】");
            }
        };
        //建立关系 发布者和处理器， 此时处理器扮演 订阅者
        publisher.subscribe(myProcessor);
        //建立关系 处理器和订阅者  此时处理器扮演
        myProcessor.subscribe(subscriber);

        publisher.submit(1);
        publisher.submit(2);
        publisher.submit(3);
        publisher.submit(4);

        publisher.close();
        TimeUnit.SECONDS.sleep(2);
    }
}

class MyProcessor extends SubmissionPublisher<Integer> implements Flow.Processor<Integer, Integer> {

    private static Logger log = LoggerFactory.getLogger(ReactiveDemo.class);

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        log.info("【Processor 收到订阅请求】");
        //保存订阅关系，需要用它来给发布者 相应
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        log.info("【onNext 收到发布者数据  : {} 】", item);
        //做业务处理。。
        if (item % 2 == 0) {
            //筛选偶数 发送给 订阅者
            this.submit(item);
        }
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        // 我们可以告诉发布者, 后面不接受数据了
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        log.info("【处理器处理完毕】");
        this.close();
    }
}
