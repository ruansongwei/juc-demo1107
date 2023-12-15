package com.tuling.bingfa1617.disruptor.event;

import com.lmax.disruptor.EventFactory;

/**
 * @author Fox
 * 事件工厂
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
