package com.xzl.mina.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/8/21 15:21
 * @version: v0.0.1
 * @history:
 */
public class MyIoHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = message.toString();
        System.out.println(str);
        if (str.endsWith("quit")) {
            session.close(true);
        }
    }
}
