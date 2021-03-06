package com.ote.builder.sample.builder.mapper.payment;

import com.ote.builder.sample.builder.EventBuilder;
import com.ote.builder.sample.input.Payment;
import com.ote.builder.sample.builder.mapper.IMessageMapper;
import com.ote.builder.util.IMapper;
import com.ote.builder.util.Mapper;
import com.ote.builder.sample.output.Event;

/**
 * Created by Olivier on 23/10/2015.
 */
public class PaymentMessageMapper extends Mapper<PaymentMessageMapper.Context> implements IMessageMapper {

    public int getId() {
        return getContext().count();
    }

    private EventBuilder eventBuilder = null;

    public Event getEvent() {

        if (eventBuilder == null) {
            eventBuilder = new EventBuilder(new PaymentEventMapper());
        }

        PaymentEventMapper.Context context = new PaymentEventMapper.Context();
        context.setPayment(getContext().getPayment());
        return eventBuilder.build(context);
    }

    /**
     * Context for PaymentMessageMapper
     */
    public static class Context implements IMapper.Context{

        private Payment payment;

        public Payment getPayment() {
            return payment;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }

        public int count() {
            return Payment.getCount();
        }
    }
}
