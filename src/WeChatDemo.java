import java.util.List;
import java.util.ArrayList;

/**
 * 定义观察者抽象类，其中有一个更新方法
 */
interface Observer {
    void update(String message);
}

/**
 * 定义具体的观察者，实现抽象观察者接口
 */
class WeixinUser implements Observer {
    // 微信用户名
    private String name;
    public WeixinUser(String name) {
        this.name = name;
    }

    /**
     * 覆写接口方法
     * @param message 被观察者传入的参数message
     * 输出微信用户名 + 信息
     */
    @Override
    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}

/**
 * 定义抽象的被观察者
 */
interface Subject {
    /**
     * 增加订阅者
     * @param observer 观察对象
     */
    void attach(Observer observer);
    /**
     * 删除订阅者
     * @param observer 观察对象
     */
    void detach(Observer observer);
    /**
     * 通知订阅者更新消息
     */
    void notify(String message);
}

/**
 * 具体的被观察者
 */
class SubscriptionSubject implements Subject {
    //储存订阅公众号的微信用户
    private List<Observer> weixinUserlist = new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        weixinUserlist.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserlist.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserlist) {
            observer.update(message);
        }
    }
}

public class WeChatDemo {
    public static void main(String[] args) {
        Subject subject = new SubscriptionSubject();
        subject.attach(new WeixinUser("Mike"));
        subject.attach(new WeixinUser("Tom"));
        subject.attach(new WeixinUser("Jerry"));
        subject.notify("我的微信订阅号更新了，快来看吧");
    }
}
