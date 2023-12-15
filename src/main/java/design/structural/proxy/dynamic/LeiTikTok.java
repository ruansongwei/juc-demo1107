package design.structural.proxy.dynamic;

//
public class LeiTikTok  implements ManTikTok,SellTikTok {
//    @Override
    public void tiktok() {
        System.out.println("雷丰阳，tiktok.... ");
    }

    @Override
    public String tiktok1() {
        System.out.println("代理逻辑");
        return "1";
    }

    @Override
    public void sell() {
        System.out.println("雷丰阳，只要666，赶紧来包...");
    }

    public void haha(){
        System.out.println("hahaha ,......");
    }
}
