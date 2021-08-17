package com.exactpro.th2.sim.template.rule;

import com.exactpro.th2.common.grpc.Message;
import com.exactpro.th2.common.grpc.Value;
import com.exactpro.th2.sim.rule.IRuleContext;
import com.exactpro.th2.sim.rule.impl.MessageCompareRule;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.exactpro.th2.common.message.MessageUtils.message;

public class TemplateNewOrderSingleRule extends MessageCompareRule {

    public TemplateNewOrderSingleRule (Map<String, Value> field){}

    //Checks that MessageType = "NewOrderSingle" and Side == 1.
    @Override
    public boolean checkTriggered(@NotNull Message message) {
        return message.getMetadata().getMessageType().equals("NewOrderSingle") &&
                String.valueOf(message.getFieldsOrDefault("Side", null)).equals("1");
    }

    // Create and send ExecutionReport
    @Override
    public void handle(@NotNull IRuleContext ruleContext, @NotNull Message message) {
        Map tmp = new HashMap<String, Object>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd’T’HH:mm:ss.SSSSSS");

        //TODO - Create ExecutionReport

        ruleContext.send(
                message("ExecutionReport").putAllFields(tmp).build());
    }
}
