package com.by5388.sy95306v2.exception;

/**
 * @author Administrator  on 2019/11/26.
 */
public final class NetworkException extends Exception {
    private static final String ERROR_MESSAGE = "网络不可用，请检查数据";

    public NetworkException() {
        super(ERROR_MESSAGE);
    }

    @Override
    public String getMessage() {
        return ERROR_MESSAGE;
    }

    @Override
    public String getLocalizedMessage() {
        return ERROR_MESSAGE;
    }
}
