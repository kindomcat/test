package com.xzl.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @desc:
 * @author: XZL
 * @createTime: 2019/8/21 14:06
 * @version: v0.0.1
 * @history:
 */
public class MyServer {

    public static void main(String[] args) throws Exception{
        startIoService();
    }

    /**
     * IOService 在一个线程上负责套接字的建立，拥有自己的Selector，监听是否有连接被建立。
     * IoAcceptor 是IOService的一个实现
     *
     */
    private static void startIoService()
            throws IOException {
        IoAcceptor acceptor=new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        acceptor.setHandler(new MyIoHandler());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter. WINDOWS.getValue())));
        acceptor.bind(new InetSocketAddress(9900));

    }
}
