package com.xzl.mina.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/8/21 15:44
 * @version: v0.0.1
 * @history:
 */
public class MyClient {

    public static void main(String[] args) {
        IoConnector connector=new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(
                                Charset.forName("UTF-8"),
                                LineDelimiter.WINDOWS.getValue(),
                                LineDelimiter.WINDOWS.getValue()
                        )
                )
        );
        connector.setHandler(new ClientHandler("你好！\r\n 大家好！"));
        ConnectFuture connect = connector.connect(new InetSocketAddress("192.168.0.88", 10002));
        IoSession session = connect.getSession();
        session.write("11111111");
    }
}
