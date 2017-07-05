package com.jerey.httplib;

import com.jerey.httplib.builder.GetBuilder;

/**
 * SherlockHttp开放接口
 * @author xiamin
 */

public class SherlockHttp {

    public static GetBuilder get() {
        return new GetBuilder();
    }

}
