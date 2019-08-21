package com.xzl.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/8/21 15:47
 * @version: v0.0.1
 * @history:
 */
public class ClientHandler extends IoHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
    private final String values;

    public ClientHandler(String values) {
        this.values = values;
    }
    @Override
    public void sessionOpened(IoSession session) {
        session.write(values);
    }
}
