package design.behavioral.interpreter;

public class MainTest {

    public static void main(String[] args) {

        Area area = new Area();
        /**
         *  上海市：张文宏-医生
         *  武汉市:雷丰阳-程序员
         *  北京市：宋宋-老人
         */
        String sr = "北京市:雷丰阳-程序员";
        String sr1 = "北京市：宋宋-老人";

        area.getTicket(sr);
        area.getTicket(sr1);

    }
}
