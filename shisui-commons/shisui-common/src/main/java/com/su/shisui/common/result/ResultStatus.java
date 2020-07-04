package com.su.shisui.common.result;

/**
 * <p>
 *
 * </p>
 *
 * @author xuyong
 * @since 2019-07-14
 */

public interface ResultStatus {
    /**
     * 错误信息
     * @return
     */
    String getMessage();

    /**
     * 错误码
     * @return
     */
    int getCode();
}
