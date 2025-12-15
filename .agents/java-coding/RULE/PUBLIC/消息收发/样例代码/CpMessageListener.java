package com.ygsoft.jt.mapp.sample.impl.component.message.listener;

import com.ygsoft.jt.teng.cp.message.core.sdk.annotation.EcpMsgListener;
import com.ygsoft.jt.teng.cp.message.core.sdk.bo.EcpMsg;
import com.ygsoft.jt.teng.cp.message.core.sdk.iface.IEcpMsgListener;
import com.ygsoft.jt.teng.fw.core.log.IJtLog;
import com.ygsoft.jt.teng.fw.core.log.JtLogFactory;


@EcpMsgListener(topics = { "cp-topic" }, isPub = true)
public class CpMessageListener implements IEcpMsgListener {
    /**
     * 日志对象.
     */
    private static final IJtLog LOG = JtLogFactory.getLog(CpMessageListener.class);


    @Override
    public void onMessage(EcpMsg ecpMsg) {
        if (LOG.isInfoEnabled()) {
            LOG.info("teng.cp 消费主题消息，消费内容[" + ecpMsg.getMessagePayload() + "]");
        }
    }
}
