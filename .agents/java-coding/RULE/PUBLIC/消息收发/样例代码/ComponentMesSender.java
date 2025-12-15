import com.ygsoft.jt.teng.fw.core.base.model.CommonResult;
import com.ygsoft.jt.teng.fw.core.service.dcispec.EcpGetMapping;
import com.ygsoft.jt.teng.cp.message.core.sdk.annotation.EcpMsgSender;
import com.ygsoft.jt.teng.cp.message.core.sdk.service.EcpMsgSenderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/mq")
public class MessageProducer {
/**
 * 消息生产者对象.
 */
@EcpMsgSender("myqueue")
private EcpMsgSenderService myQueueProducer;
@ResponseBody
@EcpGetMapping("/sendQueueMsg")
public CommonResult<String> sendQueueMsg(final String msg) {
    final String result = "发送队列消息：我发送的内容是[" + msg + "]。";
    myQueueProducer.sendMessage(msg);
    if (LOG.isInfoEnabled()) {
        LOG.info("我发送的内容是[" + msg + "]。");
    }
    return CommonResult.of(result);
}


@ResponseBody
@EcpGetMapping("/sendDelayTopicMsg")
public CommonResult<String> sendDelayTopicMsg(final String msg,int level) {
    final String result = "发送延时主题消息：我发送的内容是[" + msg + "]。";
    myTopicProducer.sendDelayMessage(msg,level);
    if (LOG.isInfoEnabled()) {
        LOG.info(result);
    }
    return CommonResult.of(result);
}

@ResponseBody
@EcpGetMapping("/sendDelayQueueMsg")
public CommonResult<String> sendDelayQueueMsg(final String msg,final int level) {
    final String result = "发送延时队列消息：我发送的内容是[" + msg + "]。";
    myQueueProducer.sendDelayMessage(msg,level);
    if (LOG.isInfoEnabled()) {
        LOG.info("我发送的延时队列内容是[" + msg + "]。");
    }
    return CommonResult.of(result);
}