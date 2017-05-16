package com.lhrl.common.util.dubbo;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.*;
import com.lhrl.common.util.ThreadLocalUserUtils;


/**
 * dubbo拦截器，隐式传递当前登陆用户
 *
 * @author Mei Xianzhi
 */
public class RpcUserFilter implements Filter {

    private static final String RPC_USER_KEY = "user_rpc";
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcUserFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        try {
            RpcContext context = RpcContext.getContext();
            if (context.isConsumerSide()) {
                context.setAttachment(RPC_USER_KEY, ThreadLocalUserUtils.getUser());
            } else {
                ThreadLocalUserUtils.setUser(context.getAttachment(RPC_USER_KEY));
            }
            return invoker.invoke(invocation);
        } catch (Throwable t) {

            LOGGER.error(t);
            return invoker.invoke(invocation);
        }
    }

}
